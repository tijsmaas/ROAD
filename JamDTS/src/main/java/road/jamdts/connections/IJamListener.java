package road.jamdts.connections;

import road.movementdtos.dtos.LaneDto;
import road.movementdtos.dtos.MovementDto;

import java.util.List;
import java.util.Map;

/**
 * Created by Geert on 21/05/2014.
 */
public interface IJamListener
{
    /**
     * This function will be called on receiving new movements.
     * @param laneMap mapping containing the movement mapped by the lane identifier on which the movements are done. If
     *                the lane is not in this mapping: no movements are done on this lane.
     */
    public void movementsReceived(Map<String, MovementDto> laneMap);
}
