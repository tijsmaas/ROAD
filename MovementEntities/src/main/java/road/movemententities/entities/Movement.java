package road.movemententities.entities;

import javax.persistence.*;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

@Entity
public class Movement implements MovementEntity<Integer> {
    @Id
    @GeneratedValue
    private int id;

    @Temporal(javax.persistence.TemporalType.TIMESTAMP)
    private Date movementDateTime;

    @ManyToOne
    private Lane lane;
    
    @OneToMany
    private List<VehicleMovement> vehicleMovements;

    public Movement(Calendar movementDate, float time, Lane lane) {
        Calendar resultDateTime = (Calendar)movementDate.clone();
        System.out.println("Before add: " + resultDateTime.getTime());
        resultDateTime.add(Calendar.SECOND, Math.round(time));
        System.out.println("After addding " + Math.round(time) + " seconds " + resultDateTime.getTime());
        this.movementDateTime = resultDateTime.getTime();
        this.lane = lane;
    }

    public Movement(){};

    @Override
    public Integer getId() {
        return id;
    }

    public Date getMovementDateTime() {
        return movementDateTime;
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
