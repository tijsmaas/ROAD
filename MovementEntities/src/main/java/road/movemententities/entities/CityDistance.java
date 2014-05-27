package road.movemententities.entities;

import javax.persistence.*;
import java.util.Date;

/**
 * The CityDistance class specifies the Distance Driven in a City and is used in the invoices.
 */
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
    
    /* Driven distance in meters */
    private double distance;
    
    /* Price per KM */
    private double km_prijs;

    /**
     * The Empty constructor is required for JPA
     */
    public CityDistance() {
    }


    /**
     * Create a new CityDistance object
     * @param city The {@link City} for this {@link CityDistance}
     * @param distance the distance driven in meters
     * @param km_prijs The kilometer rate at the time of generation
     * @param movementDate The date of the movement
     */
    public CityDistance(City city, double distance, double km_prijs, Date movementDate)
    {
        this.city = city;
        this.distance = distance;
        this.km_prijs = km_prijs;
        this.movementDate = movementDate;
    }

    /**
     * Set the {@link road.movemententities.entities.VehicleInvoice} for this CityDistance
     * @param vehicleInvoice the {@link road.movemententities.entities.VehicleInvoice} to set
     */
    public void setVehicleInvoice(VehicleInvoice vehicleInvoice)
    {
        this.vehicleInvoice = vehicleInvoice;
    }

    /**
     * get the current {@link road.movemententities.entities.VehicleInvoice}
     * @return the current {@link road.movemententities.entities.VehicleInvoice}
     */
    public VehicleInvoice getVehicleInvoice() {
        return vehicleInvoice;
    }

    /**
     * Get the current {@link road.movemententities.entities.City}
     * @return return the current {@link road.movemententities.entities.City}
     */
    public City getCity() {
        return city;
    }

    /**
     * Get the current distance in meters
     * @return the current distance in meters
     */
    public double getDistance() {
        return distance;
    }

    /**
     * Get the km rate for this {@link road.movemententities.entities.CityDistance}
     * @return the current km_rate for this {@link road.movemententities.entities.CityDistance}
     */
    public double getKm_prijs() {
        return km_prijs;
    }

    /**
     * Get the current ID for this
     * @return the current id
     */
    @Override
    public Integer getId() {
        return this.id;
    }

    /**
     * Add distance to the current distance
     * @param metersDriven the amount to add
     */
    public void addDistance(double metersDriven)
    {
        this.distance += metersDriven;
    }

    /**
     * Get the date of the movement
     * @return the date of the movement
     */
    public Date getMovementDate()
    {
        return movementDate;
    }
}
