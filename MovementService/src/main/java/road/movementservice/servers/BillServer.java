package road.movementservice.servers;

//import road.usersystem.UserDAO;

import road.billdts.connections.IBillQuery;
import road.billdts.dto.InvoiceSearchQuery;
import road.movementdtos.dtos.*;
import road.movementdtos.dtos.enumerations.PaymentStatus;
import road.movementdts.connections.MovementConnection;
import road.movementdts.helpers.DateHelper;
import road.movementdts.helpers.Pair;
import road.movemententities.entities.*;
import road.movemententityaccess.dao.*;
import road.movementservice.connections.QueueServer;
import road.movementservice.helpers.DAOHelper;
import road.movementservice.mapper.DtoMapper;
import road.userservice.UserDAO;

import javax.mail.Message;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

/**
 * Created by geh on 11-4-14.
 */
public class BillServer extends QueueServer implements IBillQuery
{
    private InvoiceDAO invoiceDAO;
    private MovementDAO movementDAO;
    private CityDAO cityDAO;
    private UserDAO userDAO;
    private LoginDAO loginDAO;
    private DtoMapper dtoMapper;
    private Session mailSession;
    private VehicleDAO vehicleDAO;

    public BillServer(InvoiceDAO invoiceDAO, LoginDAO loginDAO, UserDAO userDAO, MovementDAO movementDAO, CityDAO cityDAO, DtoMapper dtoMapper, VehicleDAO vehicleDAO, Session mailSession)
    {
        super(MovementConnection.FactoryName, MovementConnection.BillSystemQueue);

        this.invoiceDAO = invoiceDAO;
        this.loginDAO = loginDAO;
        this.userDAO = userDAO;
        this.movementDAO = movementDAO;
        this.cityDAO = cityDAO;
        this.dtoMapper = dtoMapper;
        this.vehicleDAO = vehicleDAO;
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

        for (Invoice invoice : invoices)
        {
            this.sendMail(invoice);
        }

        return invoices.size();
    }

    /**
     * {@inheritDoc}
     *
     * @param searchDetails searchDetails to search for
     * @return List of invoiceDTOs that match the query result
     */
    @Override
    public List<InvoiceDto> getInvoicesForSearch(InvoiceSearchQuery searchDetails)
    {
        List<Invoice> foundInvoices = invoiceDAO.findInvoiceFromQuery(searchDetails.getUsername(), searchDetails.getCartrackerID(), searchDetails.getMinDate(), searchDetails.getMaxDate());

        List<InvoiceDto> invoiceDtos = new ArrayList<>();
        for (Invoice invoice : foundInvoices)
        {
            invoiceDtos.add(this.dtoMapper.mapSimple(invoice));
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

    @Override
    public List<VehicleDto> getAllVehicles()
    {
        List<Vehicle> foundVehicles = vehicleDAO.getAllVehicles();

        List<VehicleDto> dtoVehicles = new ArrayList<>();
        for (Vehicle vehicle : foundVehicles)
        {
            dtoVehicles.add(dtoMapper.map(vehicle));
        }

        return dtoVehicles;
    }

    @Override
    public List<MovementUserDto> getAllUsers()
    {
        List<MovementUser> resultList = vehicleDAO.getAllVehicleUsers();

        List<MovementUserDto> movementUsers = new ArrayList<>();
        for (MovementUser movementUser : resultList)
        {
            movementUsers.add(dtoMapper.toMovementUserDto(movementUser));
        }

        return movementUsers;
    }

    @Override
    public VehicleDto addNewVehicle(String carTrackerID, String licensePlate, Integer movementUserID)
    {
        MovementUser foundUser = vehicleDAO.findMovementUser(movementUserID);

        if (foundUser == null)
        {
            return null;
        }

        Vehicle addedVehicle = vehicleDAO.addNewVehicle(carTrackerID, licensePlate, foundUser);

        return dtoMapper.map(addedVehicle);
    }

    @Override
    public VehicleDto getVehicleDetails(Integer vehicleID)
    {
        Vehicle vehicle = vehicleDAO.findByID(vehicleID);

        VehicleDto vehicleDto = dtoMapper.map(vehicle);
        vehicleDto.setVehicleOwner(dtoMapper.map(vehicle.getCurrentOwner()));

        return vehicleDto;
    }

    /**
     * {@inheritDoc}
     *
     * @param vehicleID the Vehicle ID of the vehicle to change the owner of
     * @param userID    the ID of the new owner
     * @return the new VehicleOwnerDTO object
     */
    @Override
    public VehicleOwnerDto changeVehicleOwner(Integer vehicleID, Integer userID)
    {
        MovementUser newOwner = vehicleDAO.findMovementUser(userID);
        if (newOwner == null)
        {
            return null;
        }



        Vehicle vehicleToChange = vehicleDAO.findByID(vehicleID);
        if (vehicleToChange == null)
        {
            return null;
        }

        if(vehicleToChange.getCurrentOwner().getUser() == newOwner)
        {
            return dtoMapper.map(vehicleToChange.getCurrentOwner());
        }

        VehicleOwnership ownership= vehicleDAO.changeVehicleOwner(vehicleToChange, newOwner);

        return dtoMapper.map(ownership);
    }

    /**
     * Sends an email to the customer to inform them that a new invoice is ready to be made.
     * Only done if the user has opted in
     *
     * @param invoice the invoice that has been newly generated
     */
    private void sendMail(Invoice invoice)
    {
        try
        {
            // Grab the user so that we can send an email.
            MovementUser user = this.loginDAO.getUser(invoice.getUser().getId());

            if (user.isInvoiceMail())
            {
                // Set up the message
                Message msg = new MimeMessage(this.mailSession);
                msg.setSubject("New invoice from your trustworthy and friendly government!");
                msg.setRecipient(Message.RecipientType.TO, new InternetAddress((user.getEmail()), user.getUsername()));
                msg.setFrom(new InternetAddress(mailSession.getProperty("mail.from")));
                msg.setText("Hey, " + user.getUsername() + ". There is a new invoice available for you. Go to the Driver System to pay now! Greetings from your friendly neighbourhood government.");

                // Send the message
                Transport.send(msg);
            }
        } catch (Exception ex)
        {
            ex.printStackTrace();
        }
    }
}
