package road.movementdtos.sumo;

import com.thoughtworks.xstream.annotations.XStreamAlias;
import com.thoughtworks.xstream.annotations.XStreamAsAttribute;
import road.movementdtos.sumo.FcdVehicle;

/**
 * Created by geh on 14-5-14.
 */
@XStreamAlias("response")
public class Response
{
    @XStreamAsAttribute @XStreamAlias("status")
    public String status;
    @XStreamAlias("VEHICLE_ID")
    public Integer VEHICLE_ID;
    @XStreamAlias("cause")
    public String cause;
}
