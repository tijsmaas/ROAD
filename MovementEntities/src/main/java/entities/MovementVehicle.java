package entities;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToOne;

/**
 * Created by Niek on 14/03/14.
 * © Aidas 2014
 */
@Entity
public class MovementVehicle implements MovementEntity
{

    @Id
    @GeneratedValue
    private int id;

    // Is the license plate number of the car + _ + timestep.time
    @Column(unique=false, nullable=false)
    private String movementIdentifier;
    
    @ManyToOne
    private Movement movement;

    // Map by license plate
    @ManyToOne(cascade = {CascadeType.ALL})
    private Vehicle vehicle;

    private double position;
    private double speed;
    
    // Empty constructor for JPA
    public MovementVehicle() { }

    public MovementVehicle(Movement movement, String id, Float pos, Float speed) {
        this.movement = movement;
        this.movementIdentifier = id;
        this.position = pos;
        this.speed = speed;
    }

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

    public Integer getId()
    {
        return id;
    }

    public void setId(int id)
    {
        this.id = id;
    }
    
    public String getMovementIdentifier() {
        return movementIdentifier;
    }

    public void setMovementIdentifier(String movementIdentifier) {
        this.movementIdentifier = movementIdentifier;
    }
}
