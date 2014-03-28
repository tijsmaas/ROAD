package dao;

import entities.Vehicle;

import java.util.List;

/**
 * Created by Niek on 28/03/14.
 * © Aidas 2014
 */
public class VehicleDAOImpl implements VehicleDAO
{

    /**
     * {@inheritDoc}
     * @param vehicle The Vehicle object to persist
     */
    @Override
    public void create(Vehicle vehicle)
    {

    }


    /**
     * {@inheritDoc}
     * @param vehicle The modified Vehicle object
     */
    @Override
    public void edit(Vehicle vehicle)
    {

    }

    /**
     * {@inheritDoc}
     * @param vehicle The Vehicle object to remove
     */
    @Override
    public void remove(Vehicle vehicle)
    {

    }

    /**
     * {@inheritDoc}
     * @param id the ID of the vehicle
     */
    @Override
    public Vehicle find(int id)
    {
        return null;
    }

    /**
     * {@inheritDoc}
     * @param licensePlate The license plate number of the vehicle
     */
    @Override
    public Vehicle findByLicensePlate(String licensePlate)
    {
        return null;
    }

    /**
     * {@inheritDoc}
     * @param userID The ID of the user
     */
    @Override
    public List<Vehicle> getVehiclesFromUser(int userID)
    {
        return null;
    }

    /**
     * {@inheritDoc}
     * @param vehicle The vehicle switching owners
     * @param userID The user ID of the new owner.
     */
    @Override
    public void changeVehicleOwnership(Vehicle vehicle, int userID)
    {

    }
}
