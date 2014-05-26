package road.carsystem.domain;

import com.thoughtworks.xstream.annotations.XStreamAlias;
import road.carsystem.domain.netstate.TimeStep;
import road.movementdtos.sumo.FcdTimeStep;

/**
 * Created by geh on 15-5-14.
 * This class is supposed to be parsed into XML and be sent to the client over the websocket.
 */
@XStreamAlias("socketresponse")
public class SocketResponse
{
    @XStreamAlias("message")
    public String message;
    @XStreamAlias("timestep")
    public FcdTimeStep timeStep;
}
