package dao;

import entities.Lane;

import javax.persistence.EntityManager;

/**
 * Created by Niek on 28/03/14.
 * © Aidas 2014
 */
public interface LaneDAO
{
    /**
     * Selects a lane object by ID
     * @param laneID The ID of the lane to find
     * @return The found Lane object
     */
    Lane find(int laneID);

    /**
     * Finds a lane object by the SUMO identifier
     * @param laneIdentifier The SUMO lane identifier
     * @return The found Lane object.
     */
    Lane find(String laneIdentifier);

	int count();

    void setEntityManager(EntityManager em);
}
