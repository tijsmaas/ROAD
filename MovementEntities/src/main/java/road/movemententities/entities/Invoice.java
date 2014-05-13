package road.movemententities.entities;

import java.util.Calendar;
import java.util.List;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToMany;

@Entity
public class Invoice implements MovementEntity<Integer> {
    @Id
    @GeneratedValue
    private int id;
    
    /* Date of invoice generation */
    private Calendar month;
    
    /* Total price in euro of the invoice */
    private double totalprice;

    @OneToMany
    private List<CityDistance> vehicleMovementsPerCity;
    
    public Invoice() {}

    public Calendar getMonth() {
        return month;
    }

    public List<CityDistance> getVehicleMovementsPerCity() {
        return vehicleMovementsPerCity;
    }

    @Override
    public Integer getId() {
        return this.id;
    }
}
