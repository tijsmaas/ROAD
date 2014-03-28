package entities;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import java.awt.*;
import java.util.List;

/**
 * Created by Niek on 14/03/14.
 * © Aidas 2014
 */
@Entity
public class Junction
{
    @Id
    @GeneratedValue
    private int id;
    private String junctionIdentifier;
    private double x;
    private double y;

    @OneToMany
    private List<Lane> incLanes;

    @OneToMany
    private List<Lane> intLanes;

    private Polygon shape;

    @OneToMany
    private List<JunctionRequest> requests;

    //region Properties
    public int getId()
    {
        return id;
    }

    public void setId(int id)
    {
        this.id = id;
    }

    public String getJunctionIdentifier()
    {
        return junctionIdentifier;
    }

    public void setJunctionIdentifier(String junctionIdentifier)
    {
        this.junctionIdentifier = junctionIdentifier;
    }

    public double getX()
    {
        return x;
    }

    public void setX(double x)
    {
        this.x = x;
    }

    public double getY()
    {
        return y;
    }

    public void setY(double y)
    {
        this.y = y;
    }

    public List<Lane> getIncLanes()
    {
        return incLanes;
    }

    public void setIncLanes(List<Lane> incLanes)
    {
        this.incLanes = incLanes;
    }

    public List<Lane> getIntLanes()
    {
        return intLanes;
    }

    public void setIntLanes(List<Lane> intLanes)
    {
        this.intLanes = intLanes;
    }

    public Polygon getShape()
    {
        return shape;
    }

    public void setShape(Polygon shape)
    {
        this.shape = shape;
    }

    public List<JunctionRequest> getRequests()
    {
        return requests;
    }

    public void setRequests(List<JunctionRequest> requests)
    {
        this.requests = requests;
    }
    //endregion
}
