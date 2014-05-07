package road.movemententityaccess.dao;

import road.movemententities.entities.Junction;
import road.movemententities.entities.JunctionRequest;

/**
 * Created by Niek on 28/03/14.
 * © Aidas 2014
 */
public interface JunctionDAO
{

    /**
     * Find a junction by ID
     * @param junctionID The ID of the junction
     * @return The found Junction object
     */
    Junction find(int junctionID);

    /**
     * Find a junction by the SUMO JunctionIdentifier
     * @param junctionIdentifier The SUMO Junction Identifier
     * @return The found Junction object
     */
    Junction findByIdentifier(String junctionIdentifier);

    /**
     * Finds a junction request in the junction
     * @param junction The Junction object to search in
     * @param index the index of the junction request
     * @return The JunctionRequest object found
     */
    JunctionRequest findJunctionRequest(Junction junction, int index);
}
