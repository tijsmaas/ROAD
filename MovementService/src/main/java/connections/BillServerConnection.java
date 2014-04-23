package connections;

import aidas.usersystem.IUserManager;
import aidas.usersystem.dto.UserDto;

import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;
import javax.inject.Named;

/**
 * Created by geh on 11-4-14.
 */
public class BillServerConnection extends ServerConnection implements IBillQuery
{
    @Inject
    private IUserManager manager;

    public BillServerConnection()
    {
        super(MovementConnection.FactoryName, MovementConnection.BillSystemQueue);
    }

    @Override
    public UserDto authenticate(String userId, String password)
    {
        return new UserDto(1, "test");
    }
}
