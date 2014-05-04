package road.movementservice.servers;

//import aidas.usersystem.IUserManager;
import aidas.userservice.dto.UserDto;
import road.billdts.connections.IBillQuery;
import road.movementdts.connections.MovementConnection;
import road.movementservice.connections.ServerConnection;

import javax.annotation.PostConstruct;
import javax.ejb.Singleton;
import javax.ejb.Startup;

/**
 * Created by geh on 11-4-14.
 */
@Singleton
@Startup
public class BillServer extends ServerConnection implements IBillQuery
{
    //@Inject
    //private IUserManager manager;

    public BillServer()
    {
        super(MovementConnection.FactoryName, MovementConnection.BillSystemQueue);
    }

    @PostConstruct
    public void init()
    {
        super.initRpc(IBillQuery.class, this);
        this.start();
    }

    @Override
    public UserDto authenticate(String user, String password)
    {
        return new UserDto(1, user + " @ bill system");
    }
}
