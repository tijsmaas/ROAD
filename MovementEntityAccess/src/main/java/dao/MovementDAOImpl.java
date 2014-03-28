package dao;

import entities.Movement;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.Date;
import java.util.List;

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
     * @param MovementID The ID of the movement
     */
    @Override
    public Movement find(int MovementID)
    {
        return null;
    }

    /**
     * {@inheritDoc}
     * @param date movement date
     */
    @Override
    public List<Movement> getMovementsByDate(Date date)
    {
        return null;
    }
}
