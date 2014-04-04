package dao;

import entities.Lane;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

/**
 * Created by Niek on 28/03/14.
 * © Aidas 2014
 */
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
        return 0;
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
