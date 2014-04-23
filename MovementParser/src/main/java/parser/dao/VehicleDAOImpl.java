package parser.dao;

import parser.dao.VehicleDAO;
import entities.Vehicle;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

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
     * @param vehicle The Vehicle object to persist
     */
    @Override
    public void create(Vehicle vehicle)
    {
        em.persist(vehicle);
    }


    /**
     * {@inheritDoc}
     * @param vehicle The modified Vehicle object
     */
    @Override
    public void edit(Vehicle vehicle)
    {
        em.merge(vehicle);
    }

    /**
     * {@inheritDoc}
     * @param vehicle The Vehicle object to remove
     */
    @Override
    public void remove(Vehicle vehicle)
    {
        em.remove(vehicle);
    }

}
