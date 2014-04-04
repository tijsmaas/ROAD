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
