package road.movementservice.servers;

import aidas.userservice.dto.UserDto;
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

    public DriverServer(LaneDAO laneDAO, ConnectionDAO connectionDAO, EdgeDAO edgeDAO)
    {
        super(MovementConnection.FactoryName, MovementConnection.DriverSystemQueue);
        this.laneDAO = laneDAO;
    }

    public void init()
    {
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
        return this.edgeDAO.count();
    }
}
