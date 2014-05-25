package road.movemententities.entities;

import javax.persistence.*;

/**
 * Created by geh on 25-5-14.
 */
@Entity
public class ShapeCoordinate
{
    @Id @GeneratedValue @Column(name="id")
    private int id;
    @ManyToOne
    private Lane lane;
    @Column(name="sequence")
    private int sequence;
    @Column(name="latitude")
    private double latitude;
    @Column(name="longitude")
    private double longitude;

    public ShapeCoordinate()
    {

    }

    public ShapeCoordinate(Lane lane, int sequence, double latitude, double longitude)
    {
        this.lane = lane;
        this.sequence = sequence;
        this.latitude = latitude;
        this.longitude = longitude;
    }

    public int getId()
    {
        return id;
    }

    public void setId(int id)
    {
        this.id = id;
    }

    public Lane getLane()
    {
        return lane;
    }

    public void setLane(Lane lane)
    {
        this.lane = lane;
    }

    public int getSequence()
    {
        return sequence;
    }

    public void setSequence(int sequence)
    {
        this.sequence = sequence;
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
}
