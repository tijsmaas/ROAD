package road.movementservice;

import road.movemententityaccess.dao.LaneDAO;
import road.movemententityaccess.dao.LaneDAOImpl;
import road.movementservice.servers.DriverServer;

import javax.annotation.PostConstruct;
import javax.ejb.Singleton;
import javax.ejb.Startup;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.PersistenceContext;

/**
 * Created by geh on 8-5-14.
 */
public class Server
{
    private LaneDAO laneDAO;

    private DriverServer driverServer;

    public void init()
    {
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("MovementPU");

        this.laneDAO = new LaneDAOImpl().init(emf);

        this.driverServer = new DriverServer();
        this.driverServer.init(this.laneDAO);
    }
}
