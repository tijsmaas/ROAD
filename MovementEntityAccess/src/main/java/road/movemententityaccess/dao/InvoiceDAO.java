package road.movemententityaccess.dao;

import road.movemententities.entities.Invoice;
import road.movemententities.entities.VehicleMovement;

import java.util.Date;
import java.util.List;

/**
 * Created by Niek on 14/05/14.
 * © Aidas 2014
 */
public interface InvoiceDAO
{
    public int generate(List<VehicleMovement> vehicleMovements, Date startDate, Date endDate);

    public List<Invoice> getInvoicesForUser(int userID);
}
