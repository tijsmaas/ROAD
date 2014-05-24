package road.jamsystem.services.internal;

import road.jamdts.connections.IJamListener;
import road.jamdts.connections.JamClient;
import road.movementdtos.dtos.MovementDto;
import road.movementdtos.dtos.VehicleMovementDto;

import javax.annotation.PostConstruct;
import javax.ejb.Singleton;
import javax.ejb.Startup;
import java.util.List;
import java.util.Map;

/**
 * Created by Geert on 21/05/2014.
 */
@Singleton
@Startup
public class JamService implements IJamListener
{
    private JamClient jamClient;

    /**
     * Create a new instance of the {@link JamService}.
     */
    public JamService()
    {
    }

    @PostConstruct
    public void init()
    {
        this.jamClient = new JamClient(this);
        this.jamClient.start();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void movementsReceived(Map<String, MovementDto> laneMap)
    {
        // TODO: Calculate traffic jams. Put them into a variable and create getter.
        for(Map.Entry<String, MovementDto> entry : laneMap.entrySet())
        {
            for(VehicleMovementDto vMovement : entry.getValue().getVehicleMovements())
            {
                //vMovement
            }
        }
    }
}
