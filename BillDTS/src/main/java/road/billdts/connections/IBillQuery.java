package road.billdts.connections;

import road.movementdtos.dtos.MovementUserDto;
import road.movementdtos.dtos.CityDistanceDto;
import road.movementdtos.dtos.InvoiceDto;
import road.movementdtos.dtos.enumerations.PaymentStatus;
import road.userservice.dto.UserDto;
import road.movementdtos.dtos.CityDto;

import java.util.Date;
import java.util.List;

/**
 * Created by geh on 11-4-14.
 * This interface specifies all the possible queries that can be done from the BillSystem.
 */
public interface IBillQuery
{
    MovementUserDto authenticate(String user, String password);

    boolean adjustKilometerRate(CityDto city, Date addDate, String price);

    List<CityDto> getCities();

    Integer generateMonthlyInvoices(Integer month, Integer year);

    /**
     * Get the invoices for the users that have a username matching the @param searchQuery parameter
     * @param searchQuery
     * @return
     */
    List<InvoiceDto> getInvoicesForUserSearch(String searchQuery);

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
}
