package road.movemententities.entities;

import javax.persistence.*;
import java.util.Calendar;
import java.util.List;

/**
 * Created by Niek  on 4/03/14..
 * © Aidas 2014
 */
@Entity
public class Movement implements MovementEntity<Integer>
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
    
    // timestep time
    private float time;

    // Empty constructor for JPA
    public Movement() { }
    
    public Movement(Calendar movementDate, float time) {
        this.movementDate = movementDate;
        this.time = time;
    }

    //region Properties
    public Integer getId()
    {
        return id;
    }

    public void setId(int id)
    {
        this.id = id;
    }

    public float getTime() {
        return time;
    }

    public void setTime(float time) {
        this.time = time;
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
