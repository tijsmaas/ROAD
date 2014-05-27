package road.movemententityaccess.dao;

import road.movemententities.entities.Connection;

/**
 * the {@link road.movemententityaccess.dao.ConnectionDAO} specifies all the database action belonging to {@link road.movemententities.entities.Connection connections}
 * Created by Niek on 28/03/14.
 *  Aidas 2014
 */
public interface ConnectionDAO
{
    /**
     * Searches for a connection in the database
     * @param connectionID the ID of the connection
     * @return The connection found, or null.
     */
    Connection find(int connectionID);

    /**
     * Get the amount of connections in the database
     * @return
     */
	Long count();
}
