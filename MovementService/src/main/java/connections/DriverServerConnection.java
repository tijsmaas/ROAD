package connections;

import aidas.usersystem.IUserManager;
import aidas.usersystem.dto.UserDto;

import javax.inject.Inject;
import javax.inject.Named;

/**
 * Created by geh on 22-4-14.
 */
@Named
public class DriverServerConnection extends ServerConnection implements IDriverQuery
{
    @Inject
    private IUserManager userManager;

    public DriverServerConnection()
    {
        super(MovementConnection.FactoryName, MovementConnection.DriverSystemQueue);
    }

    @Override
    public UserDto authenticate(String userId, String password)
    {
        return null;
    }
}
