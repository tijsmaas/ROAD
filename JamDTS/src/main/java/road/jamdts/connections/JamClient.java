package road.jamdts.connections;

import road.movementdts.connections.MovementConnection;
import road.movementdts.connections.TopicClient;

/**
 * Created by geh on 21-5-14.
 */
public class JamClient extends TopicClient
{
    public JamClient()
    {
        super(MovementConnection.FactoryName, MovementConnection.JamTopic);
    }

    @Override
    public void receive(byte[] message)
    {
        //TODO
    }
}
