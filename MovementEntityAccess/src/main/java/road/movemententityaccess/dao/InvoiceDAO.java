package road.movemententityaccess.dao;

import road.movemententities.entities.CityDistance;
import road.movemententities.entities.Invoice;
import road.movemententities.entities.VehicleMovement;
import road.movemententities.entities.enumerations.PaymentStatus;

import java.util.Date;
import java.util.List;

/**
 * Interface containing all methods that are used for querying the database for invoice related stuff
 *
 * Created by Niek on 14/05/14.
 * © Aidas 2014
 */
public interface InvoiceDAO
{
    /**
     * Generate invoices for the list of movements
     * @param vehicleMovements List of movements you want to generate invoices for
     * @param startDate Starting date of the invoice
     * @param endDate End date of the invoices
     * @return Number of generated invoices
     */
    public int generate(List<VehicleMovement> vehicleMovements, Date startDate, Date endDate);

    /**
     * Get all the invoices for a certain user
     * @param userID
     * @return
     */
    public List<Invoice> getInvoicesForUser(int userID);

    /**
     * Find an invoice based on it's ID
     * @param invoiceID
     * @return
     */
    public Invoice getInvoice(int invoiceID);

    /**
     * Update the payment status of an invoice
     * @param invoiceID the ID of the invoice you want to update
     * @param dtoPaymentStatus The new PaymentStatus
     * @return true if success, false if failed.
     */
    public boolean updateInvoicePaymentstatus(int invoiceID, PaymentStatus dtoPaymentStatus);

    /**
     * get all the CityDistances for the current vehicleIncoiceID
     * @param vehicleInvoiceID the id of the VehicleInvoice for which you want to get the citydistances
     * @return list of citydistances
     */
    public List<CityDistance> getCityDistancesForVehicleInvoice(int vehicleInvoiceID);

    /**
     * return all invoices of which the userID matches an id in the list
     * @param eligibleUserIDs List of eligible userIDs
     * @return list of found invoices
     */
    public List<Invoice> findInvoicesForUserIDs(List<Integer> eligibleUserIDs);
}
