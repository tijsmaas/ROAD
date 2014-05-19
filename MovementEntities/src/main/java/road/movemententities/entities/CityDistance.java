package road.movemententities.entities;

import javax.persistence.*;
import java.util.Date;

@Entity
public class CityDistance implements MovementEntity<Integer> {
    @Id
    @GeneratedValue
    private int id;
    
    @ManyToOne
    private VehicleInvoice vehicleInvoice;
    
    @ManyToOne
    private City city;

    @Temporal(javax.persistence.TemporalType.TIMESTAMP)
    private Date movementDate;
    
    /* Driven distance in KM */
    private double distance;
    
    /* Price per KM */
    private double km_prijs;

    public CityDistance() {
    }

    public CityDistance(City city, double distance, double km_prijs, Date movementDate)
    {
        this.city = city;
        this.distance = distance;
        this.km_prijs = km_prijs;
        this.movementDate = movementDate;
    }

    public void setVehicleInvoice(VehicleInvoice vehicleInvoice)
    {
        this.vehicleInvoice = vehicleInvoice;
    }
    
    public VehicleInvoice getVehicleInvoice() {
        return vehicleInvoice;
    }

    public City getCity() {
        return city;
    }

    public double getDistance() {
        return distance;
    }

    public double getKm_prijs() {
        return km_prijs;
    }

    @Override
    public Integer getId() {
        return this.id;
    }

    public void addDistance(double metersDriven)
    {
        this.distance += metersDriven;
    }

    public Date getMovementDate()
    {
        return movementDate;
    }
}
