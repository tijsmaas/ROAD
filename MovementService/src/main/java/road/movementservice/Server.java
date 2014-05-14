package road.movementservice;

import aidas.userservice.IUserManager;
import aidas.userservice.UserManager;
import aidas.userservice.exceptions.UserSystemException;

import road.movemententityaccess.dao.*;
import road.movementservice.servers.BillServer;
import road.movementservice.servers.CarServer;
import road.movementservice.servers.DriverServer;
import road.movementservice.servers.PoliceServer;

import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import java.net.URL;

/**
 * Created by geh on 8-5-14.
 */
public class Server
{
<<<<<<< HEAD
    private LaneDAO laneDAO;
    private EdgeDAO edgeDAO;
    private VehicleDAO vehicleDAO;
    private ConnectionDAO connectionDAO;
    private InvoiceDAO invoiceDAO;
    private MovementDAO movementDAO;

=======
>>>>>>> nog een commit, deze keer met de spullen van de vorige keer, das wel beter.
    private DriverServer driverServer;
    private BillServer billServer;
    private PoliceServer policeServer;
    private CarServer carServer;

    /**
     * The user manager which is used to process all authentication requests.
     */
    private IUserManager userManager;

    /**
     * this method is used to initialize all the different services.
     */
    public void init()
    {
        EntityManagerFactory emfUserService = Persistence.createEntityManagerFactory("UserServicePU");
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("MovementPU");

        this.userManager = new UserManager(emfUserService);
        
        // Create a user for debugging.
<<<<<<< HEAD
        try {
            this.userManager.register("admin", "aidas123");
        } catch (UserSystemException e) {
            e.printStackTrace();
        }

        this.laneDAO = new LaneDAOImpl(emf);
        this.edgeDAO = new EdgeDAOImpl(emf);
        this.vehicleDAO = new VehicleDAOImpl(emf);
        this.connectionDAO = new ConnectionDAOImpl(emf);
        this.invoiceDAO = new InvoiceDAOImpl(emf);
        this.movementDAO  = new MovementDAOImpl(emf);

        this.driverServer = new DriverServer(this.userManager, this.laneDAO, this.connectionDAO, this.edgeDAO, this.vehicleDAO);
        this.driverServer.init();

        this.billServer = new BillServer(this.invoiceDAO, this.userManager, this.movementDAO);
        this.billServer.init();

        this.policeServer = new PoliceServer();
        this.policeServer.init();

        this.carServer = new CarServer(new EntityDAOImpl(emf));
        this.carServer.init();
    }
}
