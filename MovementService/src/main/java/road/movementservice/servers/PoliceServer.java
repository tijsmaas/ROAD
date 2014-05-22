package road.movementservice.servers;

//import road.usersystem.UserDAO;
import road.movementdtos.dtos.MovementUserDto;
import road.movementdtos.dtos.StolenCarDto;
import road.movementdtos.dtos.VehicleDto;
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

    @Override
    public MovementUserDto authenticate(String user, String password)
    {
        return DAOHelper.authenticate(this.userDAO, this.loginDAO, this.dtoMapper, user, password);
    }

    @Override
    public StolenCarDto getStolenCarByLicensePlate(String licensePlate) {
        return dtoMapper.toStolenCarDto(policeDAO.findVehicleByLicensePlate(licensePlate), loginDAO);
    }

    @Override
    public StolenCarDto getStolenCarByCartrackerId(String cartrackerId) {
        return dtoMapper.toStolenCarDto(policeDAO.findVehicleByCartracker(cartrackerId), loginDAO);
    }

    @Override
    public List<StolenCarDto> getAllStolenCars() {
        return dtoMapper.toStolenCarDtoList(policeDAO.findAllVehicles(), loginDAO);
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
}
