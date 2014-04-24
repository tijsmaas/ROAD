package dao;

import entities.Vehicle;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.List;

/**
 * Created by Niek on 28/03/14.
 * © Aidas 2014
 */
public class VehicleDAOImpl implements VehicleDAO
{
    @PersistenceContext(unitName = "MovementPU")
    private EntityManager em;

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

    @Override
    public void setEntityManager(EntityManager em)
    {
       this.em = em;
    }
}
