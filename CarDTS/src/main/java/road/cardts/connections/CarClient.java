package road.cardts.connections;

import road.movementdts.connections.QueueClient;
import road.movementdts.connections.MovementConnection;

/**
 * Created by geh on 6-5-14.
 */
public class CarClient extends QueueClient implements ICarQuery
{
    public CarClient()
    {
        super(MovementConnection.ServerAddress, MovementConnection.FactoryName, MovementConnection.CarSystemQueue);
    }

    public String addMovement(String apiKey, Long sequence, String xml)
    {
        return this.remoteCall("addMovement", String.class, apiKey, sequence, xml);
    }
}
