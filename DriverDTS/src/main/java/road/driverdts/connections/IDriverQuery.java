package road.driverdts.connections;

import aidas.userservice.dto.UserDto;
import road.movementdtos.dtos.InvoiceDto;
import road.movementdtos.dtos.VehicleDto;
import road.movementdtos.dtos.enumerations.PaymentStatus;

import java.util.List;

/**
 * Created by geh on 22-4-14.
 */
public interface IDriverQuery
{
    UserDto authenticate(String userId, String password);

    Long getLaneCount();

    Long getEdgeCount();

    /**
     * Get the vehicles linked to the specified user.
     * @param userId the identifier of the user.
     * @return the vehicles linked to the specified user.
     */
    List<VehicleDto> getVehicles(Integer userId);

    /**
     * Update the provided vehicle.
     * @param vehicleDto the vehicle to be updated.
     * @return if the function was successful.
     */
    Boolean updateVehicle(VehicleDto vehicleDto);

    List<InvoiceDto> getUserInvoices(Integer userID);

    Boolean updateInvoicePaymentStatus(Integer invoiceID, PaymentStatus paymentStatus);

    InvoiceDto getInvoiceDetails(Integer invoiceID);

}
