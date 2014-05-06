package parser.dao;

import road.movemententities.entities.Lane;
import javax.enterprise.context.ApplicationScoped;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

/**
 * Created by Niek on 28/03/14.
 * © Aidas 2014
 */
@ApplicationScoped
public class LaneDAOImpl implements LaneDAO
{

    @PersistenceContext(unitName = "MovementPU")
    private EntityManager em;
    /**
     * {@inheritDoc}
     */
    @Override
    public int count()
    {
        Query q = em.createQuery("select count(lane) from Lane lane");
        return ((Long) q.getSingleResult()).intValue();
    }

    /**
     * {@inheritDoc}
     * @param lane The lane object to persist
     */
    @Override
    public void create(Lane lane)
    {
        em.persist(lane);
    }

    /**
     * {@inheritDoc}
     * @param lane The modified Lane object
     */
    @Override
    public void edit(Lane lane)
    {
        em.merge(lane);
    }

    /**
     * {@inheritDoc}
     * @param lane The lane object to remove
     */
    @Override
    public void remove(Lane lane)
    {
        em.remove(lane);
    }

}
