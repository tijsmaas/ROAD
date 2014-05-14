package road.cardts.connections;

import road.movementdts.connections.ClientConnection;
import road.movementdts.connections.MovementConnection;

/**
 * Created by geh on 6-5-14.
 */
public class CarClient extends ClientConnection implements ICarQuery
{
    public CarClient()
    {
        super(MovementConnection.ServerAddress, MovementConnection.FactoryName, MovementConnection.CarSystemQueue);
    }

    public String addMovement(String apiKey, long sequence, String xml)
    {
        return this.remoteCall("addMovement", String.class, apiKey, sequence, xml);
    }
}
