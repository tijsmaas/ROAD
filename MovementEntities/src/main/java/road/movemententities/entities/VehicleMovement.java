package road.movemententities.entities;

import javax.persistence.*;

/**
 * Created by Niek  on 4/03/14..
 * © Aidas 2014
 */
@Entity
public class VehicleMovement implements MovementEntity<Integer>
{
    @Id
    @GeneratedValue
    private int id;

    @ManyToOne
    Movement movement;
    
    /* The moving vehicle */
    @ManyToOne
    Vehicle vehicle;
    
    /* Vehicle position and speed */
    private float position;
    private float speed;

    // Empty constructor for JPA
    public VehicleMovement() { }

    public VehicleMovement(Movement movement, Vehicle vehicle, float position, float speed) {
        this.movement = movement;
        this.vehicle = vehicle;
        this.position = position;
        this.speed = speed;
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

    public Vehicle getVehicles() {
        return vehicle;
    }

    public void setVehicle(Vehicle movementVehicles) {
        this.vehicle = movementVehicles;
    }

    public float getPosition() {
        return position;
    }

    public void setPosition(float position) {
        this.position = position;
    }

    public float getSpeed() {
        return speed;
    }

    public void setSpeed(float speed) {
        this.speed = speed;
    }
}
