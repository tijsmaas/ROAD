package road.policedts.connections;

import road.userservice.dto.UserDto;
import road.movementdts.connections.ClientConnection;
import road.movementdts.connections.MovementConnection;

/**
 * Created by geh on 23-4-14.
 */
public class PoliceClient extends ClientConnection implements IPoliceQuery
{
    public PoliceClient()
    {
        super("localhost:1099", MovementConnection.FactoryName, MovementConnection.PoliceSystemQueue);
    }

    @Override
    public UserDto authenticate(String userId, String password)
    {
        return this.remoteCall("authenticate", UserDto.class, userId, password);
    }
}
