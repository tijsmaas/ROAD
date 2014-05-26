package road.movementdtos.sumo;

import com.thoughtworks.xstream.annotations.XStreamAlias;
import com.thoughtworks.xstream.annotations.XStreamAsAttribute;

/**
 * Created by geh on 25-5-14.
 */
@XStreamAlias("vehicle")
public class FcdVehicle
{
    @XStreamAsAttribute @XStreamAlias("id")
    private String id;
    @XStreamAsAttribute @XStreamAlias("y")
    private double latitude;
    @XStreamAsAttribute @XStreamAlias("x")
    private double longitude;
    @XStreamAsAttribute @XStreamAlias("angle")
    private double angle;
    @XStreamAsAttribute @XStreamAlias("speed")
    private double speed;
    @XStreamAsAttribute @XStreamAlias("pos")
    private double pos;
    @XStreamAsAttribute @XStreamAlias("lane")
    private String laneId;

    public FcdVehicle()
    {

    }

    public FcdVehicle(String id, double longitude, double latitude, double angle, double speed, double pos, String laneId)
    {
        this.id = id;
        this.longitude = longitude;
        this.latitude = latitude;
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

    public double getLongitude()
    {
        return longitude;
    }

    public void setLongitude(double longitude)
    {
        this.longitude = longitude;
    }

    public double getLatitude()
    {
        return latitude;
    }

    public void setLatitude(double latitude)
    {
        this.latitude = latitude;
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
