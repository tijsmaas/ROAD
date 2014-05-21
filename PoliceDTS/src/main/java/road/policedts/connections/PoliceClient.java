package road.policedts.connections;

import road.movementdtos.dtos.MovementUserDto;
import road.movementdtos.dtos.StolenCarDto;
import road.movementdtos.dtos.VehicleDto;
import road.userservice.dto.UserDto;
import road.movementdts.connections.ClientConnection;
import road.movementdts.connections.MovementConnection;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by geh on 23-4-14.
 */
public class PoliceClient extends ClientConnection implements IPoliceQuery
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
    public StolenCarDto getStolenCarByLicensePlate(String licensePlate) {
        return this.remoteCall("getStolenCarByLicensePlate", StolenCarDto.class, licensePlate);
    }

    @Override
    public StolenCarDto getStolenCarByCartrackerId(String cartrackerId) {
        return this.remoteCall("getStolenCarByCartrackerId", StolenCarDto.class, cartrackerId);
    }

    @Override
    public List<StolenCarDto> getAllStolenCars() {
        return this.remoteCall("getAllStolenCars", ArrayList.class);
    }

    @Override
    public VehicleDto getVehicleByLicensePlate(String licensePlate) {
        return this.remoteCall("getVehicleByLicensePlate", VehicleDto.class);
    }

    @Override
    public VehicleDto getVehicleByCartrackerId(String cartrackerId) {
        return this.remoteCall("getVehicleByCartrackerId", VehicleDto.class);
    }

    @Override
    public boolean setStolen(VehicleDto vehicleDto, boolean isStolen) {
        return this.remoteCall("setStolen", boolean.class, vehicleDto, isStolen);
    }
}
