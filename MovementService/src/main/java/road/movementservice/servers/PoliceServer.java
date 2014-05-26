package road.movementservice.servers;

//import road.usersystem.UserDAO;
import road.movementdtos.dtos.MovementUserDto;
import road.movementdtos.dtos.VehicleDto;
import road.movementdtos.dtos.VehicleMovementDto;
import road.movementdtos.dtos.VehicleOwnerDto;
import road.movemententities.entities.Vehicle;
import road.movemententities.entities.VehicleOwnership;
import road.movemententityaccess.dao.LoginDAO;
import road.movementservice.connections.QueueServer;
import road.movemententityaccess.dao.PoliceDAO;
import road.movemententityaccess.dao.VehicleDAO;
import road.movementservice.helpers.DAOHelper;
import road.movementservice.mapper.DtoMapper;
import road.userservice.UserDAO;
import road.policedts.connections.IPoliceQuery;
import road.movementdts.connections.MovementConnection;

import java.util.List;

//import javax.inject.Inject;

/**
 * Created by geh on 23-4-14.
 */
public class PoliceServer extends QueueServer implements IPoliceQuery
{
    private LoginDAO loginDAO;
    private UserDAO userDAO;
    private PoliceDAO policeDAO;
    private DtoMapper dtoMapper;
    private VehicleDAO vehicleDAO;

    public PoliceServer(LoginDAO loginDAO, UserDAO userDAO, PoliceDAO policeDAO, VehicleDAO vehicleDAO, DtoMapper dtoMapper)
    {
        super(MovementConnection.FactoryName, MovementConnection.PoliceSystemQueue);

        this.loginDAO = loginDAO;
        this.userDAO = userDAO;
        this.policeDAO = policeDAO;
        this.vehicleDAO = vehicleDAO;
        this.dtoMapper = dtoMapper;
    }

    public void init()
    {
        super.initRpc(IPoliceQuery.class, this);
        this.start();
    }

    /**
     * Used for authenticating bill system users
     * @param user username
     * @param password password
     * @return MovementUserDto corresponding to the username if the password was correct, if not, null.
     */
    @Override
    public MovementUserDto authenticate(String user, String password)
    {
        return DAOHelper.authenticate(this.userDAO, this.loginDAO, this.dtoMapper, user, password);
    }

    @Override
    public VehicleDto getStolenCarByLicensePlate(String licensePlate) {
        return dtoMapper.map(policeDAO.findVehicleByLicensePlate(licensePlate));
    }

    @Override
    public VehicleDto getStolenCarByCartrackerId(String cartrackerId) {
        return dtoMapper.map(policeDAO.findVehicleByCartracker(cartrackerId));
    }

    @Override
    public List<VehicleDto> getAllStolenCars() {
        return dtoMapper.map(policeDAO.findAllVehicles());
    }

    @Override
    public VehicleDto getVehicleByLicensePlate(String licensePlate) {
        return dtoMapper.map(vehicleDAO.findByLicensePlate(licensePlate));
    }

    @Override
    public VehicleDto getVehicleByCartrackerId(String cartrackerId) {
        return dtoMapper.map(vehicleDAO.findByCartracker(cartrackerId));
    }

    @Override
    public VehicleDto setStolen(VehicleDto vehicleDto) {
        Vehicle vehicle = vehicleDAO.findByLicensePlate(vehicleDto.getLicensePlate());
        vehicle.setStolen(vehicleDto.isStolen());
        return dtoMapper.map(policeDAO.setStolen(vehicle));
    }

    @Override
    public List<VehicleOwnerDto> getVehicleOwners(String licensePlate) {
        return dtoMapper.mapVehicleOwners(vehicleDAO.findByLicensePlate(licensePlate).getVehicleOwners(), loginDAO);
    }

    @Override
    public List<VehicleMovementDto> getVehicleMovements(String licensePlate) {
        return dtoMapper.mapVehicleMovements(vehicleDAO.findByLicensePlate(licensePlate).getVehicleMovements());
    }
}
