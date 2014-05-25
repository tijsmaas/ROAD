package road.carsystem.domain;

import road.carsystem.domain.netstate.TimeStep;
import road.movementdtos.sumo.FcdTimeStep;

/**
 * Created by geh on 15-5-14.
 */
public class SocketResponse
{
    public String message;
    public FcdTimeStep timeStep;
}
