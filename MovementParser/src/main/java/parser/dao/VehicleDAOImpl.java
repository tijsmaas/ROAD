package parser.dao;

import road.movemententities.entities.Vehicle;
import javax.enterprise.context.ApplicationScoped;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

/**
 * Created by Niek on 28/03/14. © Aidas 2014
 */
@ApplicationScoped
public class VehicleDAOImpl implements VehicleDAO {

    @PersistenceContext(unitName = "MovementPU")
    private EntityManager em;


    @Override
    public int count() {
        Query q = em.createQuery("select count(vehicle) from Vehicle vehicle");
        return ((Long) q.getSingleResult()).intValue();
    }
    
    /**
     * {@inheritDoc}
     *
     * @param vehicle The Vehicle object to persist
     */
    @Override
    public void create(Vehicle vehicle) {
        em.persist(vehicle);
    }

    /**
     * {@inheritDoc}
     *
     * @param vehicle The modified Vehicle object
     */
    @Override
    public void edit(Vehicle vehicle) {
        em.merge(vehicle);
    }

    /**
     * {@inheritDoc}
     *
     * @param vehicle The Vehicle object to remove
     */
    @Override
    public void remove(Vehicle vehicle) {
        em.remove(vehicle);
    }

}
