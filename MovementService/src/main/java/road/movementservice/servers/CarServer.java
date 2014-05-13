package road.movementservice.servers;

import road.cardts.connections.ICarQuery;
import road.movementdts.connections.MovementConnection;
import road.movementservice.connections.ServerConnection;

/**
 * Created by geh on 7-5-14.
 */
public class CarServer extends ServerConnection implements ICarQuery
{
    public CarServer()
    {
        super(MovementConnection.FactoryName, MovementConnection.CarSystemQueue);
    }

    @Override
    public boolean addMovement(String apiKey, long sequence, String xml)
    {
        return false;
    }
}
