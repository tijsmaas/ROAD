/*
 * Copyright by AIDaS.
 */

package road.movemententities.converters;

import road.movementdtos.dtos.VehicleDto;
import road.movemententities.entities.Vehicle;
import road.movemententities.entities.VehicleOwnership;

import java.util.List;

/**
 * This class represents the converter which can be used to convert the {@link Vehicle} class.
 *
 * @author Geert
 */
public class VehicleConverter {

    /**
     *
     * Convert the {@link Vehicle} to a {@link VehicleDto} class.
     * @param vehicle the vehicle entity to convert.
     * @return the converted vehicle entity.
     */
    public static VehicleDto toVehicleDto(Vehicle vehicle) {
        VehicleOwnership currentOwer = VehicleConverter.getCurrentVehicleOwner(vehicle.getVehicleOwners());
        boolean contributeGPSData = currentOwer != null ? currentOwer.getContributeGPSData() : false;

        return new VehicleDto(vehicle.getLicensePlate(), contributeGPSData);
    }

    /**
     * Get the current ownership from the provided list of vehicle ownerships.
     * @param vehicleOwnershipList the list of vehicle ownerships.
     * @return the current vehicle ownership, if none are found null is returned.
     */
    public static VehicleOwnership getCurrentVehicleOwner(List<VehicleOwnership> vehicleOwnershipList) {
        for (VehicleOwnership vo : vehicleOwnershipList) {
            if (vo == null) {
                return vo;
            }
        }

        return null;
    }
}
