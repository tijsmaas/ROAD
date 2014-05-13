package road.movemententities.entities;

import java.util.Calendar;
import java.util.List;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;

@Entity
public class Movement implements MovementEntity<Integer> {
    @Id
    @GeneratedValue
    private int id;

    private Calendar movementDate;
    
    // timestep time
    private float time;

    @ManyToOne
    private Lane lane;
    
    @OneToMany
    private List<VehicleMovement> vehicleMovements;

    public Movement(Calendar movementDate, float time, Lane lane) {
        this.movementDate = movementDate;
        this.time = time;
        this.lane = lane;
    }

    @Override
    public Integer getId() {
        return id;
    }

    public Calendar getMovementDate() {
        return movementDate;
    }

    public float getTime() {
        return time;
    }

    public Lane getLane() {
        return lane;
    }

    public List<VehicleMovement> getVehicleMovements() {
        return vehicleMovements;
    }

    public void setVehicleMovements(List<VehicleMovement> vehicleMovements) {
        this.vehicleMovements = vehicleMovements;
    }
}
