package entities;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import java.util.List;

/**
 * Created by Niek on 14/03/14.
 * © Aidas 2014
 */
@Entity
public class Vehicle
{
    @Id
    @GeneratedValue
    private int id;

    private String licensePlate;

    private boolean isStolen;

    @OneToMany
    private List<VehicleOwnership> vehicleOwners;
}
