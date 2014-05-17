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
    VehicleOwnership vehicleOwnership;

    /* Vehicle position and speed */
    private float position;
    private float speed;

    // Empty constructor for JPA
    public VehicleMovement()
    {
    }

    public VehicleMovement(Movement movement, Vehicle vehicle, float position, float speed)
    {
        this.movement = movement;
        this.vehicleOwnership = vehicle.getCurrentOwner();
        this.position = position;
        this.speed = speed;
    }

    public VehicleMovement(Movement movement, VehicleOwnership vehicleOwnership, float position, float speed)
    {
        this.movement = movement;
        this.vehicleOwnership = vehicleOwnership;
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

    public VehicleOwnership getVehicleOwnership()
    {
        return this.vehicleOwnership;
    }

    public void setVehicle(VehicleOwnership vehicleOwnership)
    {
        this.vehicleOwnership = vehicleOwnership;
    }

    public float getPosition()
    {
        return position;
    }

    public void setPosition(float position)
    {
        this.position = position;
    }

    public float getSpeed()
    {
        return speed;
    }

    public void setSpeed(float speed)
    {
        this.speed = speed;
    }

    public Movement getMovement()
    {
        return movement;
    }
}
