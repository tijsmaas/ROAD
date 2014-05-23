package road.policedts.connections;

import road.movementdtos.dtos.MovementUserDto;
import road.movementdtos.dtos.VehicleDto;
import road.movementdtos.dtos.VehicleMovementDto;
import road.movementdtos.dtos.VehicleOwnerDto;
import road.userservice.dto.UserDto;

import java.util.List;

/**
 * Created by geh on 23-4-14.
 */
public interface IPoliceQuery
{
    MovementUserDto authenticate(String userId, String password);

    VehicleDto getStolenCarByLicensePlate(String licensePlate);

    VehicleDto getStolenCarByCartrackerId(String cartrackerId);

    List<VehicleDto> getAllStolenCars();

    VehicleDto getVehicleByLicensePlate(String licensePlate);

    VehicleDto getVehicleByCartrackerId(String cartrackerId);

    VehicleDto setStolen(VehicleDto vehicleDto);

    List<VehicleOwnerDto> getVehicleOwners(String licensePlate);

    List<VehicleMovementDto> getVehicleMovements(String licensePlate);
}
