package road.movemententities.entities;

import java.util.ArrayList;
import java.util.List;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.OneToMany;

/**
 * Created by Niek on 14/03/14.
 * © Aidas 2014
 */
@Entity
public class Vehicle implements MovementEntity
{
    
    @Id
    private String licensePlate;    
    
    @OneToMany
    private List<VehicleMovement> movements = new ArrayList();
    
    @OneToMany
    private List<Invoice> invoices = new ArrayList();

    @OneToMany
    private List<VehicleOwnership> vehicleOwners = new ArrayList();
    
    private boolean isStolen = false;
    
    // Empty constructor for JPA
    public Vehicle() { }

    public Vehicle(String licensePlate) {
        this.licensePlate = licensePlate;
    }

    //region Properties

    public List<VehicleMovement> getVehicleMovements()
    {
        return movements;
    }

    public void addVehicleMovement(VehicleMovement movement)
    {
        this.movements.add(movement);
    }
    
    public List<VehicleOwnership> getVehicleOwners()
    {
        return vehicleOwners;
    }
    
    public void addVehicleOwner(VehicleOwnership vehicleOwner)
    {
        this.vehicleOwners.add(vehicleOwner);
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
}
