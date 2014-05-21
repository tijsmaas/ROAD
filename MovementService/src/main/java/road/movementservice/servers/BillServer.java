package road.movementservice.servers;

//import road.usersystem.UserDAO;

import road.movementdtos.dtos.MovementUserDto;
import road.movemententities.entities.Invoice;
import road.movemententities.entities.MovementUser;
import road.movemententityaccess.dao.LoginDAO;
import road.movementservice.helpers.DAOHelper;
import road.userservice.UserDAO;
import road.userservice.dto.UserDto;
import road.billdts.connections.IBillQuery;
import road.movementdtos.dtos.CityDistanceDto;
import road.movementdtos.dtos.CityDto;
import road.movementdtos.dtos.InvoiceDto;
import road.movementdtos.dtos.enumerations.PaymentStatus;
import road.movementdts.connections.MovementConnection;
import road.movementdts.helpers.DateHelper;
import road.movementdts.helpers.Pair;
import road.movemententities.entities.CityDistance;
import road.movemententities.entities.Invoice;
import road.movemententities.entities.VehicleMovement;
import road.movemententityaccess.dao.CityDAO;
import road.movemententityaccess.dao.InvoiceDAO;
import road.movemententityaccess.dao.MovementDAO;
import road.movementservice.connections.ServerConnection;
import road.movementservice.mapper.DtoMapper;
import road.userservice.UserDAO;
import road.userservice.dto.UserDto;
import road.userservice.entities.UserEntity;

import java.util.*;
import javax.mail.Message;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

/**
 * Created by geh on 11-4-14.
 */
public class BillServer extends ServerConnection implements IBillQuery
{
    private InvoiceDAO invoiceDAO;
    private MovementDAO movementDAO;
    private CityDAO cityDAO;
    private UserDAO userDAO;
    private LoginDAO loginDAO;
    private DtoMapper dtoMapper;
    private Session mailSession;

    public BillServer(InvoiceDAO invoiceDAO, LoginDAO loginDAO, UserDAO userDAO, MovementDAO movementDAO, CityDAO cityDAO, DtoMapper dtoMapper, Session mailSession)
    {
        super(MovementConnection.FactoryName, MovementConnection.BillSystemQueue);

        this.invoiceDAO = invoiceDAO;
        this.loginDAO = loginDAO;
        this.userDAO = userDAO;
        this.movementDAO = movementDAO;
        this.cityDAO = cityDAO;
        this.dtoMapper = dtoMapper;
        this.mailSession = mailSession;
    }

    /**
     * Called when RPC is initialized.
     */
    public void init()
    {
        super.initRpc(IBillQuery.class, this);
        this.start();
    }

    @Override
    public MovementUserDto authenticate(String user, String password)
    {
        return DAOHelper.authenticate(this.userDAO, this.loginDAO, this.dtoMapper, user, password);
    }

    @Override
    public boolean adjustKilometerRate(CityDto city, Date addDate, String price)
    {
        return cityDAO.adjustKilometerRate(dtoMapper.toCity(city), addDate, price);
    }

    @Override
    public List<CityDto> getCities()
    {
        return dtoMapper.toCityDtoList(cityDAO.findAll());
    }

    @Override
    public Integer generateMonthlyInvoices(Integer month, Integer year)
    {
        Pair<Calendar, Calendar> invoiceDateRange = DateHelper.getDateRange(month, year);

        List<VehicleMovement> vehicleMovements = movementDAO.getMovementsForVehicleInRange(invoiceDateRange.getFirst(), invoiceDateRange.getSecond());
        List<Invoice> invoices = invoiceDAO.generate(vehicleMovements, invoiceDateRange.getFirst().getTime(), invoiceDateRange.getSecond().getTime());

        for(Invoice invoice : invoices)
        {
            this.sendMail(invoice);
        }

        return invoices.size();
        return amountCreated;
    }

    /**
     * {@inheritDoc}
     *
     * @param searchQuery username to search for
     * @return List of invoiceDTOs that match the query result
     */
    @Override
    public List<InvoiceDto> getInvoicesForUserSearch(String searchQuery)
    {
        List<UserEntity> foundUsers = userManager.findUsersByName(searchQuery);

        //If no users are found, no Invoices will be found. so we just return here already.
        if (foundUsers.size() < 1)
        {
            return new ArrayList<>();
        }

        List<UserDto> eligibleUsers = new ArrayList<>();
        for (UserEntity userEntity : foundUsers)
        {
            eligibleUsers.add(new UserDto(userEntity.getId(), userEntity.getUsername()));
        }


        //Create a map with the userID as key, so we can later easily find the userName belonging to it.
        Map<Integer, UserDto> userIdMap = new HashMap<>();
        for (UserDto userDto : eligibleUsers)
        {
            userIdMap.put(userDto.getId(), userDto);
        }

        List<Invoice> foundInvoices = this.invoiceDAO.findInvoicesForUserIDs(new ArrayList<>(userIdMap.keySet()));

        List<InvoiceDto> invoiceDtos = new ArrayList<>();
        //Map the Invoices to DTOs
        for (Invoice foundInvoice : foundInvoices)
        {
            invoiceDtos.add(dtoMapper.mapSimple(foundInvoice, userIdMap));
        }

        return invoiceDtos;

    }


    /**
     * {@inheritDoc}
     *
     * @param invoiceID     the invoiceID you want to update
     * @param paymentStatus the new paymentstatus
     * @return
     */
    @Override
    public Boolean updateInvoicePaymentStatus(Integer invoiceID, PaymentStatus paymentStatus)
    {
        road.movemententities.entities.enumerations.PaymentStatus entityPaymentStatus = road.movemententities.entities.enumerations.PaymentStatus.values()[paymentStatus.ordinal()];
        Boolean result = invoiceDAO.updateInvoicePaymentstatus(invoiceID, entityPaymentStatus);
        return result;
    }

    /**
     * {@inheritDoc}
     *
     * @param invoiceID the ID of the invoice you want to get details of
     * @return
     */
    @Override
    public InvoiceDto getInvoiceDetails(Integer invoiceID)
    {
        Invoice foundInvoice = invoiceDAO.getInvoice(invoiceID);

        return dtoMapper.map(foundInvoice);
    }

    /**
     * #{@inheritDoc}
     *
     * @param vehicleInvoiceID the ID of the vehicleInvoice
     * @return
     */
    @Override
    public List<CityDistanceDto> getCityDistances(Integer vehicleInvoiceID)
    {
        List<CityDistance> cityDistances = this.invoiceDAO.getCityDistancesForVehicleInvoice(vehicleInvoiceID);

        List<CityDistanceDto> cityDistanceDtos = new ArrayList<>();
        for (CityDistance cityDistance : cityDistances)
        {
            cityDistanceDtos.add(this.dtoMapper.map(cityDistance));
        }

        return cityDistanceDtos;
    }

    //TODO
    /**
     * Sends an email to the customer to inform them that a new invoice is ready to be made.
     * Only done if the user has opted in
     * @param invoice the invoice that has been newly generated
     */
    private void sendMail(Invoice invoice)
    {
        try
        {
            // Grab the user so that we can send an email.
            MovementUser user = this.loginDAO.getUser(invoice.getUserID());

            if(user.isInvoiceMail())
            {
                // Set up the message
                Message msg = new MimeMessage(this.mailSession);
                msg.setSubject("New invoice from your trustworthy and friendly government!");
                msg.setRecipient(Message.RecipientType.TO, new InternetAddress((user.getEmail()), user.getUsername()));
                msg.setFrom(new InternetAddress(mailSession.getProperty("mail.from")));
                msg.setText("Hey, " + user.getUsername() + ". There is a new invoice available for you. Go to the Driver System to pay now! Greetings from your friendly neighbourhood government." );

                // Send the message
                Transport.send(msg);
            }
        }
        catch (Exception ex)
        {
            ex.printStackTrace();
        }

    }
}
