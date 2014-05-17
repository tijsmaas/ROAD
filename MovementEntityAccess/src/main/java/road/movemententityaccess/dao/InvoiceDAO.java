package road.movemententityaccess.dao;

import road.movemententities.entities.VehicleMovement;

import java.util.List;

/**
 * Created by Niek on 14/05/14.
 * © Aidas 2014
 */
public interface InvoiceDAO
{
    public void generate(List<VehicleMovement> vehicleMovements);
}
