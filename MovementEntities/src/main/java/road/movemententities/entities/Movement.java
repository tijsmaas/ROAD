package road.movemententities.entities;

import javax.persistence.*;
import java.util.Calendar;
import java.util.List;

@Entity
public class Movement implements MovementEntity<Integer> {
    @Id
    @GeneratedValue
    private int id;

    @Temporal(javax.persistence.TemporalType.TIMESTAMP)
    private Calendar movementDate;

    @ManyToOne
    private Lane lane;
    
    @OneToMany
    private List<VehicleMovement> vehicleMovements;

    public Movement(Calendar movementDate, float time, Lane lane) {
        this.movementDate = movementDate;
        this.movementDate.add(Calendar.SECOND, Math.round(time));
        this.lane = lane;
    }

    public Movement(){};

    @Override
    public Integer getId() {
        return id;
    }

    public Calendar getMovementDate() {
        return movementDate;
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
