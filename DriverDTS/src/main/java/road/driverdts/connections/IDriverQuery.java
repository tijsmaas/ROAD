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

    /**
     * Change the password of the provided user.
     * @param id the identifier of the user.
     * @param oldPassword the current password of the user.
     * @param newPassword the new password of the user.
     * @param newPasswordValidate the new password of the user again to validate if they are the same.
     * @return null if the change was successful, otherwise return the error.
     */
    String changePassword(Integer id, String oldPassword, String newPassword, String newPasswordValidate);

    /**
     * Change the details of the provided user.
     * @param id the identifier of the user.
     * @param name the name of the user.
     * @param street the street in which the user lives.
     * @param houseNumber the number of the house in the street in which the user lives.
     * @param postalCode the postal code in which the user lives.
     * @param city the city in which the user lives.
     * @return true if changing the details was successful, otherwise false.
     */
    Boolean changeDetails(Integer id, String name, String street, String houseNumber, String postalCode, String city);
}
