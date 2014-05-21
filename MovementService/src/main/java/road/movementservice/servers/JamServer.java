package road.movementservice.servers;

//import road.usersystem.UserDAO;

import road.movementdtos.dtos.MovementUserDto;
import road.movementdts.connections.MovementConnection;
import road.movemententityaccess.dao.LoginDAO;
import road.movementservice.connections.QueueServer;
import road.movementservice.connections.TopicServer;
import road.movementservice.helpers.DAOHelper;
import road.movementservice.mapper.DtoMapper;
import road.policedts.connections.IPoliceQuery;
import road.userservice.UserDAO;

//import javax.inject.Inject;

/**
 * Created by geh on 23-4-14.
 */
public class JamServer extends TopicServer
{
    public JamServer()
    {
        super(MovementConnection.FactoryName, MovementConnection.JamTopic);
    }

    public void init()
    {
        this.start();
    }

    //TODO create event listener to handle bill server events
}
