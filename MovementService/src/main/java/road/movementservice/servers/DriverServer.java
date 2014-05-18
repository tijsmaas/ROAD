package road.movementservice.servers;

import aidas.userservice.IUserManager;
import aidas.userservice.dto.UserDto;
import road.driverdts.connections.IDriverQuery;
import road.movementdtos.dtos.InvoiceDto;
import road.movementdtos.dtos.VehicleDto;
import road.movementdtos.dtos.enumerations.PaymentStatus;
import road.movementdts.connections.MovementConnection;
import road.movemententities.entities.Invoice;
import road.movemententityaccess.dao.*;
import road.movementservice.connections.ServerConnection;
import road.movementservice.mapper.DtoMapper;

import java.util.ArrayList;
import java.util.List;

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
    public List<VehicleDto> getVehicles(Integer userId) { return this.vehicleDAO.getVehiclesFromUser(userId); }

    /**
     * {@inheritDoc}
     */
    @Override
    public Boolean updateVehicle(VehicleDto vehicleDto) { return this.vehicleDAO.updateVehicle(vehicleDto); }

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
        road.movemententities.entities.enumerations.PaymentStatus.valueOf(paymentStatus.toString());

        return invoiceDAO.updateInvoicePaymentstatus(invoiceID, paymentStatus);
    }

    @Override
    public InvoiceDto viewInvoiceDetails(Integer invoiceID)
    {
        Invoice foundInvoice = invoiceDAO.getInvoice(invoiceID);

        return dtoMapper.map(foundInvoice);
    }
}
