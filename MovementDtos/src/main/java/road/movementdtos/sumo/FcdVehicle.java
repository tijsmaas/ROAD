package road.movementdtos.sumo;

import com.thoughtworks.xstream.annotations.XStreamAlias;
import com.thoughtworks.xstream.annotations.XStreamAsAttribute;
import road.movementdtos.dtos.LaneDto;

/**
 * Created by geh on 25-5-14.
 */
@XStreamAlias("vehicle")
public class FcdVehicle
{
    @XStreamAsAttribute
    private String id;
    @XStreamAsAttribute
    private double latitude;
    @XStreamAsAttribute
    private double longitude;
    @XStreamAsAttribute
    private double angle;
    @XStreamAsAttribute
    private double speed;
    @XStreamAsAttribute
    private double pos;
    @XStreamAsAttribute
    private String laneId;

    public FcdVehicle()
    {

    }

    public FcdVehicle(String id, double latitude, double longitude, double angle, double speed, double pos, String laneId)
    {
        this.id = id;
        this.latitude = latitude;
        this.longitude = longitude;
        this.angle = angle;
        this.speed = speed;
        this.pos = pos;
        this.laneId = laneId;
    }

    public String getId()
    {
        return id;
    }

    public void setId(String id)
    {
        this.id = id;
    }

    public double getLatitude()
    {
        return latitude;
    }

    public void setLatitude(double latitude)
    {
        this.latitude = latitude;
    }

    public double getLongitude()
    {
        return longitude;
    }

    public void setLongitude(double longitude)
    {
        this.longitude = longitude;
    }

    public double getAngle()
    {
        return angle;
    }

    public void setAngle(double angle)
    {
        this.angle = angle;
    }

    public double getSpeed()
    {
        return speed;
    }

    public void setSpeed(double speed)
    {
        this.speed = speed;
    }

    public double getPos()
    {
        return pos;
    }

    public void setPos(double pos)
    {
        this.pos = pos;
    }

    public String getLaneId()
    {
        return laneId;
    }

    public void setLane(String laneId)
    {
        this.laneId = laneId;
    }
}
