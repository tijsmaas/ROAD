package road.movemententities.entities;

import javax.persistence.*;
import java.util.Calendar;
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

    private Calendar movementDate;

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

    public Calendar getMovementDate()
    {
        return movementDate;
    }

    public void setMovementDate(Calendar movementDate)
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
