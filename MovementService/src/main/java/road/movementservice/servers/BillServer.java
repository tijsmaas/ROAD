package road.movementservice.servers;

//import road.usersystem.UserDAO;

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

/**
 * Created by geh on 11-4-14.
 */
public class BillServer extends ServerConnection implements IBillQuery
{
    private InvoiceDAO invoiceDAO;
    private MovementDAO movementDAO;
    private CityDAO cityDAO;
    private UserDAO userManager;
    private DtoMapper dtoMapper;

    public BillServer(InvoiceDAO invoiceDAO, UserDAO userManager, MovementDAO movementDAO, CityDAO cityDAO, DtoMapper dtoMapper)
    {
        super(MovementConnection.FactoryName, MovementConnection.BillSystemQueue);

        this.invoiceDAO = invoiceDAO;
        this.userManager = userManager;
        this.movementDAO = movementDAO;
        this.cityDAO = cityDAO;
        this.dtoMapper = dtoMapper;
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
    public UserDto authenticate(String user, String password)
    {
        return new UserDto(1, user + " @ bill system");
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
        int amountCreated = invoiceDAO.generate(vehicleMovements, invoiceDateRange.getFirst().getTime(), invoiceDateRange.getSecond().getTime());

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


}
