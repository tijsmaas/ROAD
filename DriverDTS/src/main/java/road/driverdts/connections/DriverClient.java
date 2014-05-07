package road.driverdts.connections;

import aidas.userservice.dto.UserDto;
import road.movementdts.connections.ClientConnection;
import road.movementdts.connections.MovementConnection;

/**
 * Created by geh on 22-4-14.
 */
public class DriverClient extends ClientConnection implements IDriverQuery
{
    public DriverClient()
    {
        super("localhost:1099", MovementConnection.FactoryName, MovementConnection.DriverSystemQueue);
    }

    @Override
    public UserDto authenticate(String userId, String password)
    {
        return this.remoteCall("authenticate", UserDto.class, userId, password);
    }

    @Override
    public Long getLaneCount()
    {
        return this.remoteCall("getLaneCount", Long.class);
    }

    @Override
    public Long getEdgeCount()
    {
        return this.remoteCall("getEdgeCount", Long.class);
    }
}
