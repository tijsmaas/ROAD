package road.movementdtos.dtos;

import java.util.List;

/**
 * Created by Mitch on 20-5-2014.
 */
public class StolenCarDto {
    private String cartrackerId;
    private String licensePlate;
    private List<VehicleOwnerDto> owners;
    private List<VehicleMovementDto> movements;

    public StolenCarDto() {
    }

    public StolenCarDto(String cartrackerId, String licensePlate, List<VehicleOwnerDto> owners, List<VehicleMovementDto> movements) {
        this.cartrackerId = cartrackerId;
        this.licensePlate = licensePlate;
        this.owners = owners;
        this.movements = movements;
    }

    public String getLicensePlate() {
        return licensePlate;
    }

    public String getCartrackerId() {
        return cartrackerId;
    }

    public List<VehicleOwnerDto> getOwners() {
        return owners;
    }

    public List<VehicleMovementDto> getMovements() {
        return movements;
    }
}
