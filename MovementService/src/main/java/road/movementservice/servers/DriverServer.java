package road.movementservice.servers;

import aidas.userservice.IUserManager;
import aidas.userservice.dto.UserDto;
import aidas.userservice.exceptions.UserSystemException;
import road.driverdts.connections.IDriverQuery;
import road.movementdts.connections.MovementConnection;
import road.movementservice.connections.ServerConnection;
import road.movemententityaccess.dao.ConnectionDAO;
import road.movemententityaccess.dao.EdgeDAO;
import road.movemententityaccess.dao.LaneDAO;

/**
 * Created by geh on 22-4-14.
 */
public class DriverServer extends ServerConnection implements IDriverQuery
{
    private EdgeDAO edgeDAO;
    private LaneDAO laneDAO;
    private ConnectionDAO connectionDAO;

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
     */
    public DriverServer(IUserManager userManager, LaneDAO laneDAO, ConnectionDAO connectionDAO, EdgeDAO edgeDAO)
    {
        super(MovementConnection.FactoryName, MovementConnection.DriverSystemQueue);

        this.userManager = userManager;
        this.laneDAO = laneDAO;
        this.connectionDAO = connectionDAO;
        this.edgeDAO = edgeDAO;
    }

    public void init()
    {
        super.initRpc(IDriverQuery.class, this);
        this.start();
    }

    @Override
    public UserDto authenticate(String user, String password)
    {
        UserDto userDto = null;

        try {
            userDto = this.userManager.login(user, password);
        } catch (UserSystemException ex) {
            ex.printStackTrace();
        }

        return userDto;
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
}
