package road.movementservice;

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
     * this method is used to initialize all the different services.
     */
    public void init()
    {
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("MovementPU");

        this.laneDAO = new LaneDAOImpl(emf);
        this.connectionDAO = new ConnectionDAOImpl(emf);
        this.edgeDAO = new EdgeDAOImpl(emf);

        this.driverServer = new DriverServer(this.laneDAO, this.connectionDAO, this.edgeDAO);
        this.driverServer.init();

        this.billServer = new BillServer();
        this.billServer.init();

        this.policeServer = new PoliceServer();
        this.policeServer.init();
    }
}
