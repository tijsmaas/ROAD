package connections;

//import aidas.usersystem.IUserManager;
import aidas.usersystem.dto.UserDto;

//import javax.inject.Inject;

/**
 * Created by geh on 23-4-14.
 */
public class PoliceServerConnection extends ServerConnection implements IPoliceQuery
{
    //@Inject
    //private IUserManager userManager;

    public PoliceServerConnection()
    {
        super(MovementConnection.FactoryName, MovementConnection.PoliceSystemQueue, IPoliceQuery.class);
    }

    @Override
    public UserDto authenticate(String userId, String password)
    {
        return null;
    }
}
