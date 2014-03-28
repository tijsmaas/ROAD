package dao;

import entities.Connection;

/**
 * Created by Niek on 28/03/14.
 * © Aidas 2014
 */
public interface ConnectionDAO
{
    /**
     *
     * @return the number of connections
     */
    int count();

    /**
     * Persists a new connection in the database
     * @param connection The connection object to persist
     */
    void create(Connection connection);

    /**
     * Saves the changes of an existing connection to the database
     * @param connection The connection object to edit
     */
    void edit(Connection connection);

    /**
     * Removes a connection from the database
     * @param connection The connection object to remove
     */
    void remove(Connection connection);

    /**
     * Searches for a connection in the database
     * @param connectionID the ID of the connection
     * @return The connection found, or null.
     */
    Connection find(int connectionID);


}
