package servers;

import aidas.usersystem.dto.UserDto;
import connections.IDriverQuery;
import connections.MovementConnection;
import connections.ServerConnection;

import javax.annotation.PostConstruct;
import javax.ejb.Startup;
import javax.ejb.Singleton;

/**
 * Created by geh on 22-4-14.
 */
@Singleton
@Startup
public class DriverServer extends ServerConnection implements IDriverQuery
{
    //@Inject
    //private IUserManager userManager;
    //@Inject
    //private EdgeDAO edgeDAO;
    //@Inject
    //private LaneDAO laneDAO;
    //@Inject
    //private ConnectionDAO connectionDAO;

    public DriverServer()
    {
        super(MovementConnection.FactoryName, MovementConnection.DriverSystemQueue);
    }

    @PostConstruct
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
}
