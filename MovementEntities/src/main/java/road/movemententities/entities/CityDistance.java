package road.movemententities.entities;

import java.io.Serializable;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToOne;

@Entity
public class CityDistance implements MovementEntity<Integer> {
    @Id
    @GeneratedValue
    private int id;
    
    @ManyToOne
    private Invoice invoice;
    
    @ManyToOne
    private City city;
    
    /* Driven distance in KM */
    private double distance;
    
    /* Price per KM */
    private double km_prijs;

    public CityDistance() {
    }

    public Invoice getInvoice() {
        return invoice;
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
