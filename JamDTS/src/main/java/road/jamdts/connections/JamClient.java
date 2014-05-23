package road.jamdts.connections;

import road.movementdtos.dtos.LaneDto;
import road.movementdtos.dtos.MovementDto;
import road.movementdts.connections.MovementConnection;
import road.movementdts.connections.TopicClient;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by geh on 21-5-14.
 */
public class JamClient extends TopicClient
{
    private IJamListener listener;

    public JamClient(IJamListener listener)
    {
        super(MovementConnection.FactoryName, MovementConnection.JamTopic);

        this.listener = listener;
    }

    @Override
    public void receive(byte[] message)
    {
        List<MovementDto> movements = this.connection.serializer.deSerialize(message, ArrayList.class);
        Map<String, MovementDto> laneMap = new HashMap();

        for (MovementDto movement : movements)
        {
            laneMap.put(movement.getLane().getId(), movement);
        }

        this.listener.movementsReceived(laneMap);
    }
}
