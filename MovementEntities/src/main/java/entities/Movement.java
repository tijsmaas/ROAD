package entities;

import javax.persistence.*;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.List;

/**
 * Created by Niek  on 4/03/14..
 * © Aidas 2014
 */
@Entity
public class Movement
{
    @Id
    @GeneratedValue
    private int id;

    @Temporal(TemporalType.DATE)
    private GregorianCalendar movementDate;

    @ManyToOne
    private Lane lane;

    @ManyToOne
    private Edge edge;

    @OneToMany
    private List<MovementVehicle> movementVehicles;


    //region Properties
    public int getId()
    {
        return id;
    }

    public void setId(int id)
    {
        this.id = id;
    }

    public GregorianCalendar getMovementDate()
    {
        return movementDate;
    }

    public void setMovementDate(GregorianCalendar movementDate)
    {
        this.movementDate = movementDate;
    }

    public Lane getLane()
    {
        return lane;
    }

    public void setLane(Lane lane)
    {
        this.lane = lane;
    }

    public Edge getEdge()
    {
        return edge;
    }

    public void setEdge(Edge edge)
    {
        this.edge = edge;
    }

    public List<MovementVehicle> getMovementVehicles()
    {
        return movementVehicles;
    }

    public void setMovementVehicles(List<MovementVehicle> movementVehicles)
    {
        this.movementVehicles = movementVehicles;
    }
    //endregion
}
