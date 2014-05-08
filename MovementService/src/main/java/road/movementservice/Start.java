package road.movementservice;

import javax.annotation.PostConstruct;
import javax.ejb.Singleton;
import javax.ejb.Startup;

/**
 * Created by geh on 8-5-14.
 */
@Singleton @Startup
public class Start
{
    private static Server server;

    @PostConstruct
    private void init()
    {
        if(Start.server == null)
        {
            Start.server = new Server();
            Start.server.init();
        }
    }
}
