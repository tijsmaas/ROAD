package road.policedts.connections;

import road.movementdtos.dtos.MovementUserDto;
import road.movementdtos.dtos.StolenCarDto;
import road.movementdtos.dtos.VehicleDto;
import road.userservice.dto.UserDto;

import java.util.List;

/**
 * Created by geh on 23-4-14.
 */
public interface IPoliceQuery
{
    MovementUserDto authenticate(String userId, String password);

    StolenCarDto getStolenCarByLicensePlate(String licensePlate);

    StolenCarDto getStolenCarByCartrackerId(String cartrackerId);

    List<StolenCarDto> getAllStolenCars();

    VehicleDto getVehicleByLicensePlate(String licensePlate);

    VehicleDto getVehicleByCartrackerId(String cartrackerId);

    boolean setStolen(VehicleDto vehicleDto, boolean isStolen);
}
