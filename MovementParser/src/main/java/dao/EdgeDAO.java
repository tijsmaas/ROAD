package dao;

import entities.Edge;

/**
 * Created by Niek on 28/03/14.
 * © Aidas 2014
 */
public interface EdgeDAO
{

    /**
     *
     * @return the number of edges in the database
     */
    int count();

    /**
     * Persists a new Edge in the database
     * @param edge The edge object to persist
     */
    void create(Edge edge);

    /**
     * Saves the changes to an existing edge to the database
     * @param edge The edited edge object
     */
    void edit(Edge edge);

    /**
     * Removes an Edge object from the database
     * @param edge The edge object to remove
     */
    void remove(Edge edge);

}
