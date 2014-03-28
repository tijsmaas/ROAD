package dao;

import entities.Edge;

/**
 * Created by Niek on 28/03/14.
 * © Aidas 2014
 */
public interface EdgeDAO
{

    /**
     * Find an edge by it's ID
     * @param edgeID The ID of the edge to find
     * @return The found edge object
     */
    Edge find(int edgeID);

    /**
     * Find an edge by the SUMO Edge identifier
     * @param edgeIdentifier The sumo Edge Identifier
     * @return The found Edge object
     */
    Edge findByIdentifier(String edgeIdentifier);
}
