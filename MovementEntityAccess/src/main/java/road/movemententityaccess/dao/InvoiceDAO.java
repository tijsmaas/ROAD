package road.movemententityaccess.dao;

import road.movemententities.entities.CityDistance;
import road.movemententities.entities.Invoice;
import road.movemententities.entities.VehicleMovement;
import road.movemententities.entities.enumerations.PaymentStatus;

import java.util.Date;
import java.util.List;

/**
 * Created by Niek on 14/05/14.
 * © Aidas 2014
 */
public interface InvoiceDAO
{
    public List<Invoice> generate(List<VehicleMovement> vehicleMovements, Date startDate, Date endDate);

    public List<Invoice> getInvoicesForUser(int userID);

    public Invoice getInvoice(int invoiceID);

    public boolean updateInvoicePaymentstatus(int invoiceID, PaymentStatus dtoPaymentStatus);

    public List<CityDistance> getCityDistancesForVehicleInvoice(int vehicleInvoiceID);
}
