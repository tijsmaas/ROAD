package road.policedts.connections;

import road.movementdtos.dtos.MovementUserDto;
import road.movementdtos.dtos.VehicleMovementDto;
import road.movementdtos.dtos.VehicleOwnerDto;
import road.movementdts.connections.QueueClient;
import road.movementdtos.dtos.VehicleDto;
import road.userservice.dto.UserDto;
import road.movementdts.connections.MovementConnection;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by geh on 23-4-14.
 */
public class PoliceClient extends QueueClient implements IPoliceQuery
{
    public PoliceClient()
    {
        super("localhost:1099", MovementConnection.FactoryName, MovementConnection.PoliceSystemQueue);
    }

    @Override
    public MovementUserDto authenticate(String userId, String password)
    {
        return this.remoteCall("authenticate", MovementUserDto.class, userId, password);
    }

    @Override
    public VehicleDto getStolenCarByLicensePlate(String licensePlate) {
        return this.remoteCall("getStolenCarByLicensePlate", VehicleDto.class, licensePlate);
    }

    @Override
    public VehicleDto getStolenCarByCartrackerId(String cartrackerId) {
        return this.remoteCall("getStolenCarByCartrackerId", VehicleDto.class, cartrackerId);
    }

    @Override
    public List<VehicleDto> getAllStolenCars() {
        return this.remoteCall("getAllStolenCars", ArrayList.class);
    }

    @Override
    public VehicleDto getVehicleByLicensePlate(String licensePlate) {
        return this.remoteCall("getVehicleByLicensePlate", VehicleDto.class, licensePlate);
    }

    @Override
    public VehicleDto getVehicleByCartrackerId(String cartrackerId) {
        return this.remoteCall("getVehicleByCartrackerId", VehicleDto.class, cartrackerId);
    }

    @Override
    public VehicleDto setStolen(VehicleDto vehicleDto) {
        return this.remoteCall("setStolen", VehicleDto.class, vehicleDto);
    }

    @Override
    public List<VehicleOwnerDto> getVehicleOwners(String licensePlate) {
        return this.remoteCall("getVehicleOwners", ArrayList.class, licensePlate);
    }

    @Override
    public List<VehicleMovementDto> getVehicleMovements(String licensePlate) {
        return this.remoteCall("getVehicleMovements", ArrayList.class, licensePlate);
    }
}
