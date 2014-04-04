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

}
