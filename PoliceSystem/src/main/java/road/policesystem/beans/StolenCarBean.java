package road.policesystem.beans;

import com.esotericsoftware.minlog.Log;
import road.movementdtos.dtos.CityDto;
import road.movementdtos.dtos.VehicleDto;
import road.movementdtos.dtos.VehicleMovementDto;
import road.movementdtos.dtos.VehicleOwnerDto;
import road.policesystem.service.PoliceService;

import javax.annotation.PostConstruct;
import javax.faces.bean.ViewScoped;
import javax.faces.event.ActionEvent;
import javax.enterprise.context.SessionScoped;
import javax.faces.bean.ManagedBean;
import javax.inject.Inject;
import javax.inject.Named;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/**
 * Bean for stolen car detail view.
 * In this view the police officer can see movements, current movement, and owner history.
 *
 * Created by Tijs
 */
@ManagedBean
@ViewScoped
public class StolenCarBean
{
    // Maximum number of suggestions for the vehicle search box.
    public static final int MAX_SUGGESTIONS = 15;

    @Inject
    private PoliceService policeService;

    // List of vehicle movements.
    private List<VehicleMovementDto> movements = new ArrayList();
    // The realtime movement, this will be loaded instantly so that it is never null.
    private VehicleMovementDto realtimeLocation = null;
    // The realtime location place is a text that shows it's current location in plain text.
    private String realtimeLocationPlace = "";
    // The license plate of the selected vehicle.
    private String licensePlate = "";

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

    /**
     * Auto complete method for searching vehicles by license plate.
     * @param searchLicensePlate
     * @return
     */
    public List<String> completeStolenCars(String searchLicensePlate)
    {
        // Remove separators from license plate.
        searchLicensePlate = searchLicensePlate.replace("-", "").toUpperCase();

        // Retrieve all license plates, then check which ones match.
        List<VehicleDto> allStolenCars = getStolenCars();
        List<String> matchingStolenCars = new ArrayList();
        int numberOfPlates = 0;
        for(VehicleDto vehicle : allStolenCars)
        {
            String vehicleLicensePlate = vehicle.getLicensePlate();
            // Check if partially matching
            if(vehicleLicensePlate != null && vehicleLicensePlate.startsWith(searchLicensePlate))
            {
                matchingStolenCars.add(vehicleLicensePlate);

                // Stop searching when we have reached the maximum number of suggestions.
                if(++numberOfPlates > MAX_SUGGESTIONS) break;
            }
        }

        return matchingStolenCars;
    }

    /**
     * Update the realtime location of a vehicle.
     * @return
     */
    public boolean updateRealtimeLocation()
    {
        if(licensePlate == null || licensePlate.isEmpty()) return false;
        System.out.println("Updating realtime location...");
        this.movements = policeService.getVehicleMovements(licensePlate);
        if(this.movements == null) return false;

        VehicleMovementDto mostResentMovement = null;
        for(VehicleMovementDto movement : this.movements)
        {
            if(mostResentMovement == null || movement.getMovementDateTime().after(mostResentMovement.getMovementDateTime()))
            {
                mostResentMovement = movement;
            }
        }
        this.realtimeLocation = mostResentMovement;
        updateRealtimeLocationPlace();
        return true;
    }

    /**
     * Update the realtime location of the selected vehicle.
     */
    private void updateRealtimeLocationPlace() {
        if(this.realtimeLocation == null) return;

        CityDto from = this.realtimeLocation.getFrom();
        CityDto to = this.realtimeLocation.getTo();
        String roadtype = this.realtimeLocation.getType();
        String lane_id = this.realtimeLocation.getLane_id();

        this.realtimeLocationPlace = from.getCityName()+" -> "+to.getCityName()+" ";
    }

    /**
     * @return The last known movement of the selected vehicle, if no vehicle selected then null.
     */
    public VehicleMovementDto getRealtimeLocation() {
        return realtimeLocation;
    }

    /**
     * @return The last known movement.location in plain text.
     */
    public String getRealtimeLocationPlace() {
        return realtimeLocationPlace;
    }

    /**
     * Check if we found a license plate, so that we can show detailed vehicle info.
     * Also update realtime movement information so that it is not empty.
     * @return
     */
    public boolean isActive()
    {
        boolean active = this.licensePlate != null && !this.licensePlate.isEmpty();
        if(active) updateRealtimeLocation();
        return active;
    }

    public VehicleDto getStolenCarByLicensePlate()
    {
        return policeService.getStolenCarByLicensePlate(licensePlate);
    }

    /**
     * Get a list of vehicle owners from the currently selected vehicle.
     * If there is no vehicle selected, the an empty list will be returned.
     * @return A list of vehicle owners.
     */
    public List<VehicleOwnerDto> getVehicleOwners()
    {
        if(licensePlate == null || licensePlate.isEmpty()) return new ArrayList();
        return policeService.getVehicleOwners(licensePlate);
    }

    /**
     * Get a list of vehicle movements from the currently selected vehicle.
     * If there is no vehicle selected, the an empty list will be returned.     *
     * @return A list of vehicle movements.
     */
    public List<VehicleMovementDto> getVehicleMovements()
    {
        if(licensePlate == null || licensePlate.isEmpty()) return new ArrayList();
        this.movements = policeService.getVehicleMovements(licensePlate);
        return this.movements;
    }
}
