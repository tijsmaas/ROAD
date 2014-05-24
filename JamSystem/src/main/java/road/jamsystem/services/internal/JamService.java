package road.jamsystem.services.internal;

import road.jamdts.connections.IJamListener;
import road.jamdts.connections.JamClient;
import road.jamsystem.beans.TrafficJamBean;
import road.movementdtos.dtos.LaneDto;
import road.movementdtos.dtos.MovementDto;
import road.movementdts.helpers.Pair;

import javax.annotation.PostConstruct;
import javax.ejb.Singleton;
import javax.ejb.Startup;
import javax.inject.Inject;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * Created by Geert on 21/05/2014.
 */
@Singleton @Startup
public class JamService implements IJamListener
{
    @Inject
    private TrafficJamBean trafficJamBean;

    private JamClient jamClient;

    /**
     * Create a new instance of the {@link JamService}.
     */
    public JamService()
    {
    }

    @PostConstruct
    public void init()
    {
        this.jamClient = new JamClient(this);
        this.jamClient.start();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void movementsReceived(Map<String, MovementDto> laneMap)
    {
        List<LaneDto> lanesWithTrafficJams = new ArrayList();
        for (MovementDto movement : laneMap.values())
        {
            // Check if this lane is already checked recursively.
            if (lanesWithTrafficJams.contains(movement.getLane()))
            {
                continue;
            }

            // Get the paths starting with the current lane.
            List<Pair<Float, List<LaneDto>>> jamPaths = this.getJamPaths(movement, laneMap);
            for (Pair<Float, List<LaneDto>> jamPath : jamPaths)
            {
                // Check for all paths if the total length of the jam is longer than 2km.
                if (jamPath.getFirst() >= 2000)
                {
                    // Add the lanes that aren't already in the list with lanes containing traffic jams.
                    for (LaneDto lane : jamPath.getSecond())
                    {
                        if (!lanesWithTrafficJams.contains(lane))
                        {
                            lanesWithTrafficJams.add(lane);
                        }
                    }
                }
            }
        }

        //this.lanesWithJam = lanesWithTrafficJams;
        this.trafficJamBean.setLanesWithJam(lanesWithTrafficJams);
    }

    /**
     * Get all the paths paired with their total length that have traffic jams on them.
     * @param movement the movement that contains the lane that will be checked.
     * @param movements the mapping containing the lane identifiers mapped to the movement on that lane.
     * @return a collection containing all traffic jam paths.
     */
    private List<Pair<Float, List<LaneDto>>> getJamPaths(MovementDto movement, Map<String, MovementDto> movements)
    {
        // A collection containing every path paired with its total length.
        List<Pair<Float, List<LaneDto>>> jamPaths = new ArrayList();
        LaneDto lane = movement.getLane();

        // Check if there are any lanes leading from this lane.
        if (lane.getLaneIdsTo().isEmpty())
        {
            // If this lane leads to no other lanes add this lane.
            List<LaneDto> thisLane = new ArrayList();
            thisLane.add(lane);

            jamPaths.add(new Pair(lane.getLength(), thisLane));
        }
        else
        {
            // Check all lanes to which the vehicle can move from this lane.
            for (String laneIdTo : lane.getLaneIdsTo())
            {
                MovementDto movementLaneTo = movements.get(laneIdTo);

                // Check if on the lane the vehicle can move is a jam.
                if (movementLaneTo != null)
                {
                    // Check recursively for other paths.
                    List<Pair<Float, List<LaneDto>>> jamPathsLaneTo = this.getJamPaths(movementLaneTo, movements);

                    // For all paths that have been found add this lane and add them to the jamPaths.
                    for (Pair<Float, List<LaneDto>> jamPathLaneTo : jamPathsLaneTo)
                    {
                        List<LaneDto> jamPath = jamPathLaneTo.getSecond();
                        Float jamPathLength = jamPathLaneTo.getFirst();

                        jamPath.add(lane);
                        jamPathLength += lane.getLength();

                        jamPaths.add(new Pair(jamPathLength, jamPath));
                    }
                }
            }
        }

        // Return all jam paths.
        return jamPaths;
    }
}
