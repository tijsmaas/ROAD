package dao;

import entities.Vehicle;

import java.util.List;

/**
 * Created by Niek on 28/03/14.
 * © Aidas 2014
 */
public interface VehicleDAO
{
    /**
     * Persists a vehicle to the database
     * @param vehicle The Vehicle object to persist
     */
    void create(Vehicle vehicle);

    /**
     * Merges the changes of a modified Vehicle
     * @param vehicle The modified Vehicle object
     */
    void edit(Vehicle vehicle);

    /**
     * Removes a Vehicle from the database
     * @param vehicle The Vehicle object to remove.
     */
    void remove(Vehicle vehicle);

}
