package road.movemententityaccess.dao;

import road.movemententities.entities.Lane;

/**
 * Created by Niek on 28/03/14.
 *  Aidas 2014
 */
public interface LaneDAO
{
    /**
     * Finds a lane object by the SUMO identifier
     * @param laneIdentifier The SUMO lane identifier
     * @return The found Lane object.
     */
    Lane find(String laneIdentifier);

	Long count();
}
