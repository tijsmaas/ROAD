package connections;

import aidas.userservice.dto.UserDto;

import javax.annotation.PostConstruct;
import javax.ejb.LocalBean;
import javax.ejb.Singleton;
import javax.ejb.Startup;
import javax.enterprise.context.ApplicationScoped;
import javax.faces.bean.ManagedBean;

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

    @Override
    public Long getLaneCount()
    {
        return this.remoteCall("getLaneCount", Long.class);
    }

    @Override
    public Long getEdgeCount()
    {
        return this.remoteCall("getEdgeCount", Long.class);
    }
}
