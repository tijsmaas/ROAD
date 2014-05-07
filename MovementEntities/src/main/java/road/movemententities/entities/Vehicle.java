package road.movemententities.entities;

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

    public void setLicensePlate(String licensePlate)
    {
        this.licensePlate = licensePlate;
    }

    public int getId()
    {
        return id;
    }

    public void setId(int id)
    {
        this.id = id;
    }
    //endregion
}
