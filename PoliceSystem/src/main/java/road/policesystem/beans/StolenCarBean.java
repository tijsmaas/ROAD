package road.policesystem.beans;

import road.movementdtos.dtos.VehicleDto;
import road.movementdtos.dtos.VehicleMovementDto;
import road.movementdtos.dtos.VehicleOwnerDto;
import road.policesystem.service.PoliceService;

import javax.enterprise.context.SessionScoped;
import javax.inject.Inject;
import javax.inject.Named;
import java.io.Serializable;
import java.util.List;

/**
 * Created by Mitch on 21-5-2014.
 */
@Named
@SessionScoped
public class StolenCarBean implements Serializable{
    @Inject
    private PoliceService policeService;

    private String licensePlate;

    public String getLicensePlate() {
        return licensePlate;
    }

    public void setLicensePlate(String licensePlate) {
        this.licensePlate = licensePlate;
    }
    
    public List<VehicleDto> getStolenCars()
    {
        return policeService.getAllStolenCars();
    }

    public VehicleDto getStolenCarByLicensePlate()
    {
        return policeService.getStolenCarByLicensePlate(licensePlate);
    }

    public List<VehicleOwnerDto> getVehicleOwners(String licensePlate)
    {
        return policeService.getVehicleOwners(licensePlate);
    }

    public List<VehicleMovementDto> getVehicleMovements(String licensePlate)
    {
        return policeService.getVehicleMovements(licensePlate);
    }
}
