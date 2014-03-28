package dao;

import entities.Movement;

import java.util.Date;
import java.util.List;

/**
 * Created by Niek on 28/03/14.
 * © Aidas 2014
 */
public class MovementDAOImpl implements MovementDAO
{
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

    }

    /**
     * {@inheritDoc}
     * @param movement The modified Movement object
     */
    @Override
    public void edit(Movement movement)
    {

    }

    /**
     * {@inheritDoc}
     * @param movement The Movement object to remove
     */
    @Override
    public void remove(Movement movement)
    {

    }

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
