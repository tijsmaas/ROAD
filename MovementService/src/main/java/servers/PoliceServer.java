package servers;

//import aidas.usersystem.IUserManager;
import aidas.usersystem.dto.UserDto;
import connections.IPoliceQuery;
import connections.MovementConnection;
import connections.ServerConnection;

import javax.annotation.PostConstruct;
import javax.ejb.Startup;
import javax.ejb.Singleton;

//import javax.inject.Inject;

/**
 * Created by geh on 23-4-14.
 */
@Singleton
@Startup
public class PoliceServer extends ServerConnection implements IPoliceQuery
{
    //@Inject
    //private IUserManager userManager;

    public PoliceServer()
    {
        super(MovementConnection.FactoryName, MovementConnection.PoliceSystemQueue);
    }

    @PostConstruct
    public void init()
    {
        super.initRpc(IPoliceQuery.class, this);
        this.start();
    }

    @Override
    public UserDto authenticate(String user, String password)
    {
        return new UserDto(1, user + " @ police system");
    }
}
