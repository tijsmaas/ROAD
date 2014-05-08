package road.movementservice.servers;

//import aidas.usersystem.IUserManager;
import aidas.userservice.dto.UserDto;
import road.policedts.connections.IPoliceQuery;
import road.movementdts.connections.MovementConnection;
import road.movementservice.connections.ServerConnection;

import javax.annotation.PostConstruct;
import javax.ejb.Startup;
import javax.ejb.Singleton;

//import javax.inject.Inject;

/**
 * Created by geh on 23-4-14.
 */
public class PoliceServer extends ServerConnection implements IPoliceQuery
{
    public PoliceServer()
    {
        super(MovementConnection.FactoryName, MovementConnection.PoliceSystemQueue);
    }

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
