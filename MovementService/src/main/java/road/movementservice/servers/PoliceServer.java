package road.movementservice.servers;

//import road.usersystem.UserDAO;
import road.movementdtos.dtos.MovementUserDto;
import road.movemententityaccess.dao.LoginDAO;
import road.movementservice.connections.QueueServer;
import road.movementservice.helpers.DAOHelper;
import road.movementservice.mapper.DtoMapper;
import road.userservice.UserDAO;
import road.policedts.connections.IPoliceQuery;
import road.movementdts.connections.MovementConnection;

//import javax.inject.Inject;

/**
 * Created by geh on 23-4-14.
 */
public class PoliceServer extends QueueServer implements IPoliceQuery
{
    private LoginDAO loginDAO;
    private UserDAO userDAO;
    private DtoMapper dtoMapper;

    public PoliceServer(LoginDAO loginDAO, UserDAO userDAO, DtoMapper dtoMapper)
    {
        super(MovementConnection.FactoryName, MovementConnection.PoliceSystemQueue);

        this.loginDAO = loginDAO;
        this.userDAO = userDAO;
        this.dtoMapper = dtoMapper;
    }

    public void init()
    {
        super.initRpc(IPoliceQuery.class, this);
        this.start();
    }

    @Override
    public MovementUserDto authenticate(String user, String password)
    {
        return DAOHelper.authenticate(this.userDAO, this.loginDAO, this.dtoMapper, user, password);
    }
}
