package dao;

import entities.Lane;

/**
 * Created by Niek on 28/03/14.
 * © Aidas 2014
 */
public class LaneDAOImpl implements LaneDAO
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
     * @param lane The lane object to persist
     */
    @Override
    public void create(Lane lane)
    {

    }

    /**
     * {@inheritDoc}
     * @param lane The modified Lane object
     */
    @Override
    public void edit(Lane lane)
    {

    }

    /**
     * {@inheritDoc}
     * @param lane The lane object to remove
     */
    @Override
    public void remove(Lane lane)
    {

    }

    /**
     * {@inheritDoc}
     * @param laneID The ID of the lane to find
     */
    @Override
    public Lane find(int laneID)
    {
        return null;
    }

    /**
     * {@inheritDoc}
     * @param laneIdentifier The SUMO lane identifier
     */
    @Override
    public Lane find(String laneIdentifier)
    {
        return null;
    }
}
