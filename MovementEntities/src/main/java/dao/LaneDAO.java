package dao;

import entities.Lane;

/**
 * Created by Niek on 28/03/14.
 * © Aidas 2014
 */
public interface LaneDAO
{

    /**
     *
     * @return The number of lanes in the database
     */
    int count();

    /**
     * Persists a new lane to the database
     * @param lane The lane object to persist
     */
    void create(Lane lane);

    /**
     * Saves the changes made to an existing lane object to the database
     * @param lane The modified Lane object
     */
    void edit(Lane lane);

    /**
     * Removes a Lane from the database
     * @param lane The lane object to remove
     */
    void remove(Lane lane);

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
}
