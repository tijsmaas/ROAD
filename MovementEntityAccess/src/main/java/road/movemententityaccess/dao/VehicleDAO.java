package road.movemententityaccess.dao;

import road.movemententities.entities.MovementUser;
import road.movemententities.entities.Vehicle;
import road.movemententities.entities.VehicleOwnership;

import java.util.List;

/**
 * Created by Niek on 28/03/14.
 *  Aidas 2014
 */
public interface VehicleDAO
{
    /**
     * Find a vehicle by it's license plate
     * @param licensePlate The license plate number of the vehicle
     * @return The found vehicle
     */
    Vehicle findByLicensePlate(String licensePlate);
    
    /**
     * Find a vehicle by it's license plate
     * @param cartrackerId The cartracker id of the vehicle
     * @return The found vehicle
     */
    Vehicle findByCartracker(String cartrackerId);

    /**
     * Get a list of all the vehicles currently belonging to a user
     * @param userID The ID of the user
     * @return List of owned vehicles
     */
    List<Vehicle> getVehiclesFromUser(Integer userID);

    /**
     * Update the provided vehicle.
     * @param licencePlate the licence plate of the vehicle to be updated.
     * @param contributeGPSData if the vehicle will (not) contribute it's GPS data for traffic jam calculations.
     * @return if the function was successful.
     */
    Boolean updateVehicle(String licencePlate, Boolean contributeGPSData);

    /**
     * Return a list of all vehicles in the database.
     * @return List of vehicles
     */
    List<Vehicle> getAllVehicles();

    /**
     * Find a vehicle by it's internal ID
     * @param vehicleID The ID of the vehicle
     * @return
     */
    Vehicle findByID(int vehicleID);

    /**
     * Get all the Movement Users
     * @return list of MovementUsers
     */
    List<MovementUser> getAllVehicleUsers();

    /**
     * Find a movementUser based on its ID
     * @param movementUserID the ID of the movementUser
     * @return The found MovementUser
     */
    MovementUser findMovementUser(int movementUserID);

    /**
     * Add a new vehicle to the database
     * @param carTrackerID The CartrackerID of the vehicle
     * @param licensePlate The licenseplate for the new vehicle
     * @param movementUser The MovementUser that will be the initial owner of the vehicle
     * @return the newly added vehicle
     */
    Vehicle addNewVehicle(String carTrackerID, String licensePlate, MovementUser movementUser);

    /**
     * Change the ownership of a vehicle
     * @param vehicle The vehicle changing ownerships
     * @param newOwner the new owner of the vehicle
     * @return VehicleOwnership
     */
    VehicleOwnership changeVehicleOwner(Vehicle vehicle, MovementUser newOwner);
}
