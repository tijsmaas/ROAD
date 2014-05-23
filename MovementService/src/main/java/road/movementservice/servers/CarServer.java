package road.movementservice.servers;

import road.cardts.connections.ICarQuery;
import road.movementdts.connections.MovementConnection;
import road.movemententities.entities.Movement;
import road.movemententityaccess.dao.EntityDAO;
import road.movementparser.parser.Authentication;
import road.movementparser.parser.GenericParser;
import road.movementparser.parser.MovementParser;
import road.movementservice.connections.QueueServer;
import road.movementservice.events.JamEvent;

import javax.ws.rs.NotAuthorizedException;
import java.util.List;


/**
 * Created by geh on 7-5-14.
 */
public class CarServer extends QueueServer implements ICarQuery
{
    private EntityDAO entityDAO;
    private Authentication authentication;
    private MovementParser movementParser;

    public CarServer(EntityDAO entityDAO)
    {
        super(MovementConnection.FactoryName, MovementConnection.CarSystemQueue);

        this.entityDAO = entityDAO;
        this.authentication = new Authentication("/authentication.ini");
        this.movementParser = new MovementParser(entityDAO, new GenericParser());
    }

    /**
     * This starts the server by calling initRpc() and start().
     */
    public void init()
    {
        super.initRpc(ICarQuery.class, this);
        this.start();
    }

    @Override
    public String addMovement(String apiKey, Long sequence, String xml)
    {
        if(this.authentication.checkApiKey(apiKey))
        {
            List<Movement> movements = this.movementParser.parseChanges(xml, sequence.intValue());
            JamEvent.fire(movements);

            return "<?xml version=\"1.0\" encoding=\"UTF-8\"?>"
                   + "<response status=\"ok\" VEHICLE_ID=\"2\"/></xml>";
        }
        else
        {
            return "<?xml version=\"1.0\" encoding=\"UTF-8\"?>"
                    + "<response status=\"error\" cause=\""
                    + new NotAuthorizedException("API Key not valid").getLocalizedMessage()
                    + "\"/>"
                    + "</xml>";
        }
    }
}
