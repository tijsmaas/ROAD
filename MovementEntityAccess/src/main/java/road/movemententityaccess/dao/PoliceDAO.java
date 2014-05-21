package road.movemententityaccess.dao;

import road.movemententities.entities.Movement;
import road.movemententities.entities.Vehicle;

import java.util.List;

/**
 * Created by Niek on 28/03/14.
 * © Aidas 2014
 */
public interface PoliceDAO
{
    /**
     * Find a stolen vehicle by it's license plate
     * @param licensePlate The license plate number of the vehicle
     * @return The found vehicle
     */
    Vehicle findVehicleByLicensePlate(String licensePlate);
    
    /**
     * Find a stolen vehicle by it's cartracker id
     * @param cartrackerId The cartracker id of the vehicle
     * @return The found vehicle
     */
    Vehicle findVehicleByCartracker(String cartrackerId);

    /**
     * Find all stolen vehicles
     * @return The found vehicles
     */
    List<Vehicle> findAllVehicles();
}
