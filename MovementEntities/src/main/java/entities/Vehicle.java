package entities;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import java.util.List;

/**
 * Created by Niek on 14/03/14.
 * © Aidas 2014
 */
@Entity
public class Vehicle implements MovementEntity<String>
{
    @Id
    private String licensePlate;

    private boolean isStolen = false;

    @OneToMany
    private List<VehicleOwnership> vehicleOwners;

    // Empty constructor for JPA
    public Vehicle() { }

    public Vehicle(String licensePlate) {
        this.licensePlate = licensePlate;
    }
    
    //region Properties
    public List<VehicleOwnership> getVehicleOwners()
    {
        return vehicleOwners;
    }

    public void setVehicleOwners(List<VehicleOwnership> vehicleOwners)
    {
        this.vehicleOwners = vehicleOwners;
    }

    public boolean isStolen()
    {
        return isStolen;
    }

    public void setStolen(boolean isStolen)
    {
        this.isStolen = isStolen;
    }

    public String getLicensePlate()
    {
        return licensePlate;
    }
    
    public String getId()
    {
        return licensePlate;
    }

    public void setLicensePlate(String licensePlate)
    {
        this.licensePlate = licensePlate;
    }
    
    //endregion
}
