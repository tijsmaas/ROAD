package connections;

import aidas.usersystem.dto.UserDto;

/**
 * Created by geh on 23-4-14.
 */
public class PoliceClientConnection extends ClientConnection implements IPoliceQuery
{
    public PoliceClientConnection()
    {
        super("localhost:1099", MovementConnection.FactoryName, MovementConnection.PoliceSystemQueue);
    }

    @Override
    public UserDto authenticate(String userId, String password)
    {
        return this.remoteCall("authenticate", UserDto.class, userId, password);
    }
}
