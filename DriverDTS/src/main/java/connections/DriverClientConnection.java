package connections;

import aidas.usersystem.dto.UserDto;

/**
 * Created by geh on 22-4-14.
 */
public class DriverClientConnection extends ClientConnection implements IDriverQuery
{
    public DriverClientConnection()
    {
        super("localhost:1099", MovementConnection.FactoryName, MovementConnection.DriverSystemQueue);
    }

    @Override
    public UserDto authenticate(String userId, String password)
    {
        return this.remoteCall("authenticate", UserDto.class, userId, password);
    }
}
