package road.movementservice;

import aidas.userservice.IUserManager;
import aidas.userservice.UserManager;
import aidas.userservice.exceptions.UserSystemException;
import road.movemententityaccess.dao.*;
import road.movementservice.servers.BillServer;
import road.movementservice.servers.DriverServer;
import road.movementservice.servers.PoliceServer;

import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

/**
 * Created by geh on 8-5-14.
 */
public class Server
{
    private LaneDAO laneDAO;
    private EdgeDAO edgeDAO;
    private ConnectionDAO connectionDAO;

    private DriverServer driverServer;
    private BillServer billServer;
    private PoliceServer policeServer;

    /**
     * The user manager which is used to process all authentication requests.
     */
    private IUserManager userManager;

    public void init()
    {
        EntityManagerFactory emfUserService = Persistence.createEntityManagerFactory("UserServicePU");
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("MovementPU");

        this.userManager = new UserManager(emfUserService);
        try {
            this.userManager.register("admin", "admin123");
        } catch (UserSystemException e) {
            e.printStackTrace();
        }

        this.laneDAO = new LaneDAOImpl(emf);
        this.connectionDAO = new ConnectionDAOImpl(emf);
        this.edgeDAO = new EdgeDAOImpl(emf);

        this.driverServer = new DriverServer(this.userManager, this.laneDAO, this.connectionDAO, this.edgeDAO);
        this.driverServer.init();

        this.billServer = new BillServer();
        this.billServer.init();

        this.policeServer = new PoliceServer();
        this.policeServer.init();
    }
}
