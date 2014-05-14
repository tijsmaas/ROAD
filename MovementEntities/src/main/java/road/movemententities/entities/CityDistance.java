package road.movemententities.entities;

import javax.persistence.*;

@Entity
public class CityDistance implements MovementEntity<Integer> {
    @Id
    @GeneratedValue
    private int id;
    
    @ManyToOne
    private VehicleInvoice vehicleInvoice;
    
    @ManyToOne
    private City city;
    
    /* Driven distance in KM */
    private double distance;
    
    /* Price per KM */
    private double km_prijs;

    public CityDistance() {
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
}
