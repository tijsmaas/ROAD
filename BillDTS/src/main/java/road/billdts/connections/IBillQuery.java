package road.billdts.connections;

import road.billdts.dto.InvoiceSearchQuery;
import road.movementdtos.dtos.*;
import road.movementdtos.dtos.enumerations.PaymentStatus;

import java.util.Date;
import java.util.List;

/**
 * Created by geh on 11-4-14.
 * This interface specifies all the possible queries that can be done from the BillSystem.
 */
public interface IBillQuery
{
    MovementUserDto authenticate(String user, String password);

    boolean adjustKilometerRate(CityDto city, Date addDate, double price);

    List<CityDto> getCities();

    Integer generateMonthlyInvoices(Integer month, Integer year);

    /**
     * Get the invoices for the users that have a username matching the @param searchQuery parameter
     * @param searchDetails The search details
     * @return
     */
    List<InvoiceDto> getInvoicesForSearch(InvoiceSearchQuery searchDetails);

    /**
     * Update the paymentstatus of an invoice
     * @param invoiceID the invoiceID you want to update
     * @param paymentStatus the new paymentstatus
     * @return
     */
    Boolean updateInvoicePaymentStatus(Integer invoiceID, PaymentStatus paymentStatus);

    /**
     * Get the details of an invoice
     * @param invoiceID the ID of the invoice you want to get details of
     * @return InvoiceDTO object containing all details
     */
    InvoiceDto getInvoiceDetails(Integer invoiceID);

    /**
     * Get a list of cityDistances for the current vehicleInvoiceID
     * @param vehicleInvoiceID the ID of the vehicleInvoice
     * @return a list of citydistances
     */
    List<CityDistanceDto> getCityDistances(Integer vehicleInvoiceID);

    List<VehicleDto> getAllVehicles();

    List<MovementUserDto> getAllUsers();

    /**
     * Add a new vehicle to the database
     * @param carTrackerID The ID of the vehicle to add
     * @param licensePlate The license plate for the vehicle
     * @param movementUserID The Id of the movementUser
     * @return the newly added vehicle.
     */
    VehicleDto addNewVehicle(String carTrackerID, String licensePlate, Integer movementUserID);

    /**
     * Get the details for a vehicle
     * @param vehicleID The ID of the vehicle
     * @return The vehicle DTO and the current owner DTO
     */
    VehicleDto getVehicleDetails(Integer vehicleID);

    /**
     * Change the current owner of a vehicle
     * @param vehicleID the Vehicle ID of the vehicle to change the owner of
     * @param userID the ID of the new owner
     * @return the new VehicleOwnerDTO object
     */
    VehicleOwnerDto changeVehicleOwner(Integer vehicleID, Integer userID);
}
