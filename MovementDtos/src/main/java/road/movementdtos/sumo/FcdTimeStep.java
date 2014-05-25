package road.movementdtos.sumo;

import com.thoughtworks.xstream.annotations.XStreamAlias;
import com.thoughtworks.xstream.annotations.XStreamAsAttribute;

import java.util.List;

/**
 * Created by geh on 25-5-14.
 */
@XStreamAlias("timestep")
public class FcdTimeStep
{
    private List<FcdVehicle> vehicles;
    @XStreamAsAttribute
    private double time;

    public FcdTimeStep()
    {

    }

    public List<FcdVehicle> getVehicles()
    {
        return vehicles;
    }

    public void setVehicles(List<FcdVehicle> vehicles)
    {
        this.vehicles = vehicles;
    }

    public double getTime()
    {
        return time;
    }

    public void setTime(double time)
    {
        this.time = time;
    }
}
