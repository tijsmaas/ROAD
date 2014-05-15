package road.movementservice;

import javax.annotation.PostConstruct;
import javax.ejb.Singleton;
import javax.ejb.Startup;
import java.util.logging.Level;
import java.util.logging.Logger;

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
            try
            {
                Start.server = new Server();
                Start.server.init();
            }
            catch (Exception ex)
            {
                Logger.getLogger(Start.class.getName()).log(Level.SEVERE, "Failed to start the MovementService", ex);
                throw new RuntimeException("Stopping MovementService due to an error");
            }
        }
    }
}
