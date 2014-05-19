package road.movementservice.servers;

import aidas.userservice.IUserManager;
import aidas.userservice.dto.UserDto;
import road.driverdts.connections.IDriverQuery;
import road.movementdtos.dtos.CityDistanceDto;
import road.movementdtos.dtos.InvoiceDto;
import road.movementdtos.dtos.VehicleDto;
import road.movementdtos.dtos.enumerations.PaymentStatus;
import road.movementdts.connections.MovementConnection;
import road.movemententities.entities.CityDistance;
import road.movemententities.entities.Invoice;
import road.movemententityaccess.dao.*;
import road.movementservice.connections.ServerConnection;
import road.movementservice.mapper.DtoMapper;

import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * Created by geh on 22-4-14.
 * This is the Server side of the connection with the DriverSystem application (
 */
public class DriverServer extends ServerConnection implements IDriverQuery
{
    private EdgeDAO edgeDAO;
    private LaneDAO laneDAO;
    private VehicleDAO vehicleDAO;
    private ConnectionDAO connectionDAO;
    private InvoiceDAO invoiceDAO;
    private DtoMapper dtoMapper;

    /**
     * The user manager which is used to process all authentication requests.
     */
    private IUserManager userManager;

    /**
     * Create a new instance of the {@link DriverServer} class.
     * @param userManager the user manager.
     * @param laneDAO the lane dao.
     * @param connectionDAO the connection dao.
     * @param edgeDAO the edge dao.
     * @param vehicleDAO the vehicle dao.
     * @param mapper the instance of the DTO mapper
     */
    public DriverServer(IUserManager userManager, LaneDAO laneDAO, ConnectionDAO connectionDAO, EdgeDAO edgeDAO, VehicleDAO vehicleDAO, InvoiceDAO invoiceDAO, DtoMapper mapper)
    {
        super(MovementConnection.FactoryName, MovementConnection.DriverSystemQueue);

        this.userManager = userManager;
        this.laneDAO = laneDAO;
        this.connectionDAO = connectionDAO;
        this.edgeDAO = edgeDAO;
        this.vehicleDAO = vehicleDAO;
        this.dtoMapper = mapper;
        this.invoiceDAO = invoiceDAO;
    }

    /**
     * This starts the server by calling initRpc() and start().
     */
    public void init()
    {
        super.initRpc(IDriverQuery.class, this);
        this.start();
    }

    @Override
    public UserDto authenticate(String user, String password)
    {
        return this.userManager.login(user, password);
    }

    @Override
    public Long getLaneCount()
    {
        return this.laneDAO.count();
    }

    @Override
    public Long getEdgeCount()
    {
        return this.edgeDAO.count();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public List<VehicleDto> getVehicles(Integer userId)
    {
        return this.dtoMapper.map(this.vehicleDAO.getVehiclesFromUser(userId));
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Boolean updateVehicle(String licencePlate, Boolean contributeGPSData)
    {
        return this.vehicleDAO.updateVehicle(licencePlate, contributeGPSData);
    }

    @Override
    public List<InvoiceDto> getUserInvoices(Integer userID)
    {
        List<Invoice> userInvoices = invoiceDAO.getInvoicesForUser(userID);


        List<InvoiceDto> simpleInvoiceDtos = new ArrayList<>();
        for (Invoice invoice :userInvoices)
        {
            simpleInvoiceDtos.add(dtoMapper.mapSimple(invoice));
        }

        return simpleInvoiceDtos;
    }

    @Override
    public Boolean updateInvoicePaymentStatus(Integer invoiceID, PaymentStatus paymentStatus)
    {
        road.movemententities.entities.enumerations.PaymentStatus entityPaymentStatus = road.movemententities.entities.enumerations.PaymentStatus.values()[paymentStatus.ordinal()];
        Boolean result =  invoiceDAO.updateInvoicePaymentstatus(invoiceID, entityPaymentStatus);
        Logger.getLogger(this.getClass().getName()).log(Level.INFO, "Returning update sttus result as " + result);
        return result;
    }

    @Override
    public InvoiceDto getInvoiceDetails(Integer invoiceID)
    {
        Invoice foundInvoice = invoiceDAO.getInvoice(invoiceID);

        return dtoMapper.map(foundInvoice);
    }

    /**
     * #{@inheritDoc}
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

    /**
     * {@inheritDoc}
     */
    @Override
    public String changePassword(Integer id, String oldPassword, String newPassword, String newPasswordValidate) {
        if (id == null) {
            throw new IllegalArgumentException("DriverServer.changePassword: id cannot be null.");
        }

        return this.userManager.changePassword(id.intValue(), oldPassword, newPassword, newPasswordValidate);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Boolean changeDetails(Integer id, String name, String street, String houseNumber, String postalCode, String city) {
        if (id == null) {
            throw new IllegalArgumentException("DriverServer.changeDetails: id cannot be null.");
        }

        return this.userManager.changeDetails(id.intValue(), name, street, houseNumber, postalCode, city);
    }
}
