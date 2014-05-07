package road.movemententities.entities;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToOne;

/**
 * Created by Niek on 14/03/14.
 * © Aidas 2014
 */
@Entity
public class MovementVehicle
{

    @Id
    @GeneratedValue
    private int id;

    @ManyToOne
    private Movement movement;

    @ManyToOne
    private Vehicle vehicle;

    private double position;
    private double speed;

    //region Properties
    public double getSpeed()
    {
        return speed;
    }

    public void setSpeed(double speed)
    {
        this.speed = speed;
    }

    public double getPosition()
    {
        return position;
    }

    public void setPosition(double position)
    {
        this.position = position;
    }

    public Vehicle getVehicle()
    {
        return vehicle;
    }

    public void setVehicle(Vehicle vehicle)
    {
        this.vehicle = vehicle;
    }

    public Movement getMovement()
    {
        return movement;
    }

    public void setMovement(Movement movement)
    {
        this.movement = movement;
    }

    public int getId()
    {
        return id;
    }

    public void setId(int id)
    {
        this.id = id;
    }
    //endregion
}
