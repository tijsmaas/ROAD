/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package road.policesystem.service;

import road.movementdtos.dtos.MovementUserDto;
import road.movementdtos.dtos.VehicleDto;
import road.movementdtos.dtos.VehicleMovementDto;
import road.movementdtos.dtos.VehicleOwnerDto;
import road.userservice.dto.UserDto;
import road.policedts.connections.PoliceClient;

import javax.annotation.PostConstruct;
import javax.enterprise.context.ApplicationScoped;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/**
 * Police system client service.
 * This service uses policeClient to communicate with the remote MovementSystem.
 * @author Mitch
 */
@ApplicationScoped
public class PoliceService implements Serializable
{
    // We use manual injection here because CDI does not play well with JMS Messaging in Glassfish 4.
    //@Inject @ProducerQualifier
    PoliceClient policeClient;
    
    @PostConstruct
    private void init()
    {
        // Initialize the communication with MovementSystem/
        policeClient = new PoliceClient();
        policeClient.start();
    }

    /**
     * Login with police or justice officer credentials.
     * @param username
     * @param password
     * @return The MovementUserDTO (data transfer object) contains information about the authenticated user.
     */
    public MovementUserDto login(String username, String password)
    {
        return policeClient.authenticate(username, password);
    }

    /**
     * Obtain information about a stolen vehicle.
     * @param licensePlate The license plate of the stolen vehicle.
     * @return The stolen vehicle (if found), otherwise null.
     */
    public VehicleDto getStolenCarByLicensePlate(String licensePlate)
    {
        return policeClient.getStolenCarByLicensePlate(licensePlate);
    }

    /**
     * Obtain information about a stolen vehicle.
     * @param cartrackerId The cartracker id of the stolen vehicle.
     * @return The stolen vehicle (if found), otherwise null.
     */
    public VehicleDto getStolenCarByCartrackerId(String cartrackerId)
    {
        return policeClient.getStolenCarByCartrackerId(cartrackerId);
    }

    /**
     * Obtain information about any vehicle by license plate.
     * @param licensePlate The license plate of the vehicle.
     * @return The vehicle (if found), otherwise null.
     */
    public VehicleDto getVehicleByLicensePlate(String licensePlate)
    {
        return policeClient.getVehicleByLicensePlate(licensePlate);
    }

    /**
     * Obtain information about any vehicle by license plate.
     * @param cartrackerId The cartracker id of the vehicle.
     * @return The vehicle (if found), otherwise null.
     */
    public VehicleDto getVehicleByCartrackerId(String cartrackerId)
    {
        return policeClient.getVehicleByCartrackerId(cartrackerId);
    }

    /**
     * Get a list of stolen vehicles.
     * @return A list of stolen vehicles.
     */
    public List<VehicleDto> getAllStolenCars() {
        return policeClient.getAllStolenCars();
    }

    /**
     * Mark or unmark a vehicle as stolen (invert the flag).
     * @param vehicle The obtained vehicle DTO.
     * @return Return the vehicle DTO with the changed isStolen setting.
     */
    public VehicleDto setStolen(VehicleDto vehicle)
    {
        return policeClient.setStolen(vehicle);
    }

    /**
     * Get the vehicle owner history.
     * @param licensePlate The license plate of the vehicle.
     * @return The vehicle owner history.
     */
    public List<VehicleOwnerDto> getVehicleOwners(String licensePlate)
    {
        return policeClient.getVehicleOwners(licensePlate);
    }

    /**
     * Get the vehicle movements.
     * @param licensePlate The license plate of the vehicle.
     * @return The vehicle movements.
     */
    public List<VehicleMovementDto> getVehicleMovements(String licensePlate)
    {
        return policeClient.getVehicleMovements(licensePlate);
    }
}
