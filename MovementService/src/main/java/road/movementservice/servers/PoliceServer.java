package road.movementservice.servers;

//import road.usersystem.UserDAO;
import road.movementdtos.dtos.MovementUserDto;
import road.movementdtos.dtos.StolenCarDto;
import road.movemententities.entities.Vehicle;
import road.movemententities.entities.VehicleOwnership;
import road.movemententityaccess.dao.LoginDAO;
import road.movemententityaccess.dao.PoliceDAO;
import road.movementservice.helpers.DAOHelper;
import road.movementservice.mapper.DtoMapper;
import road.userservice.UserDAO;
import road.userservice.dto.UserDto;
import road.policedts.connections.IPoliceQuery;
import road.movementdts.connections.MovementConnection;
import road.movementservice.connections.ServerConnection;

//import javax.inject.Inject;

/**
 * Created by geh on 23-4-14.
 */
public class PoliceServer extends ServerConnection implements IPoliceQuery
{
    private LoginDAO loginDAO;
    private UserDAO userDAO;
    private PoliceDAO policeDAO;
    private DtoMapper dtoMapper;

    public PoliceServer(LoginDAO loginDAO, UserDAO userDAO, PoliceDAO policeDAO, DtoMapper dtoMapper)
    {
        super(MovementConnection.FactoryName, MovementConnection.PoliceSystemQueue);

        this.loginDAO = loginDAO;
        this.userDAO = userDAO;
        this.policeDAO = policeDAO;
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

    public StolenCarDto getStolenCarByLicensePlate(String licensePlate) {
        return dtoMapper.toStolenCarDto(policeDAO.findVehicleByLicensePlate(licensePlate), loginDAO);
    }

    public StolenCarDto getStolenCarByCartrackerId(String cartrackerId) {
        return dtoMapper.toStolenCarDto(policeDAO.findVehicleByCartracker(cartrackerId), loginDAO);
    }
}
