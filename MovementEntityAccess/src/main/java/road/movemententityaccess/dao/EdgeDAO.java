package road.movemententityaccess.dao;

import road.movemententities.entities.Edge;

/**
 * Created by Niek on 28/03/14.
 *  Aidas 2014
 */
public interface EdgeDAO
{

    Long count();

    /**
     * Find an edge by the SUMO Edge identifier
     * @param edgeIdentifier The sumo Edge Identifier
     * @return The found Edge object
     */
    Edge find(String edgeIdentifier);

}
