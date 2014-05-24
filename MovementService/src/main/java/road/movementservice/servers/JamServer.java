package road.movementservice.servers;

import road.movementdtos.dtos.MovementDto;
import road.movementdtos.dtos.MovementUserDto;
import road.movementdts.connections.MovementConnection;
import road.movemententities.entities.Movement;
import road.movemententityaccess.dao.LoginDAO;
import road.movementservice.connections.QueueServer;
import road.movementservice.connections.TopicServer;
import road.movementservice.events.IJamEventListener;
import road.movementservice.events.JamEvent;
import road.movementservice.helpers.DAOHelper;
import road.movementservice.mapper.DtoMapper;
import road.policedts.connections.IPoliceQuery;
import road.userservice.UserDAO;

import java.util.List;

//import javax.inject.Inject;

/**
 * Created by geh on 23-4-14.
 */
public class JamServer extends TopicServer implements IJamEventListener
{
    private DtoMapper dtoMapper;

    public JamServer(DtoMapper dtoMapper)
    {
        super(MovementConnection.FactoryName, MovementConnection.JamTopic);

        this.dtoMapper = dtoMapper;

        JamEvent.subscribe(this);
    }

    public void init()
    {
        this.start();
    }

    @Override
    public void receive(List<Movement> movements)
    {
        List<MovementDto> mappedMovements = this.dtoMapper.mapMovements(movements);
        this.send(mappedMovements);
    }
}
