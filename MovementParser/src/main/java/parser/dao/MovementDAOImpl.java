package parser.dao;

import parser.dao.MovementDAO;
import entities.Movement;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

/**
 * Created by Niek on 28/03/14.
 * © Aidas 2014
 */
public class MovementDAOImpl implements MovementDAO
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
     * @param movement The movement object to persist
     */
    @Override
    public void create(Movement movement)
    {
        em.persist(movement);
    }

    /**
     * {@inheritDoc}
     * @param movement The modified Movement object
     */
    @Override
    public void edit(Movement movement)
    {
        em.merge(movement);
    }

    /**
     * {@inheritDoc}
     * @param movement The Movement object to remove
     */
    @Override
    public void remove(Movement movement)
    {
        em.remove(movement);
    }

}
