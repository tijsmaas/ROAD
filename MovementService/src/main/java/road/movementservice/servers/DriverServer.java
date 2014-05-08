package road.movementservice.servers;

import aidas.userservice.dto.UserDto;
import road.driverdts.connections.IDriverQuery;
import road.movementdts.connections.MovementConnection;
import road.movemententityaccess.dao.LaneDAOImpl;
import road.movementservice.connections.ServerConnection;
import road.movemententityaccess.dao.ConnectionDAO;
import road.movemententityaccess.dao.EdgeDAO;
import road.movemententityaccess.dao.LaneDAO;

import javax.annotation.PostConstruct;
import javax.ejb.Singleton;
import javax.ejb.Startup;
import javax.inject.Inject;

/**
 * Created by geh on 22-4-14.
 */
public class DriverServer extends ServerConnection implements IDriverQuery
{
    //@Inject
    //private IUserManager userManager;
    //@Inject
    //private EdgeDAO edgeDAO;
    private LaneDAO laneDAO;
    //@Inject
    //private ConnectionDAO connectionDAO;

    public DriverServer()
    {
        super(MovementConnection.FactoryName, MovementConnection.DriverSystemQueue);
    }

    //@PostConstruct
    public void init(LaneDAO laneDAO)
    {
        this.laneDAO = laneDAO;
        super.initRpc(IDriverQuery.class, this);
        this.start();
    }

    @Override
    public UserDto authenticate(String user, String password)
    {
        return new UserDto(1, user + " @ driver system");
    }

    @Override
    public Long getLaneCount()
    {
        return this.laneDAO.count();
    }

    @Override
    public Long getEdgeCount()
    {
        return 5L;
        //return this.edgeDAO.count();
    }
}
