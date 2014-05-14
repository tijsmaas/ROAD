package road.movemententityaccess.dao;

import javafx.util.Pair;
import road.movemententities.entities.VehicleMovement;

import java.util.Calendar;
import java.util.Date;
import java.util.List;

/**
 * Created by Niek on 28/03/14.
 * © Aidas 2014
 */
public interface MovementDAO
{
    /**
     * Find a movement by ID
     * @param MovementID The ID of the movement
     * @return The found movement
     */
    VehicleMovement find(int MovementID);

    /**
     * Get all movements of a certain date
     * @param date movement date
     * @return List of found movements
     */
    List<VehicleMovement> getMovementsByDate(Date date);

    /**
     * Get the movements for each
     * @param dateRange The range of dates to get movements from
     * @return tbd
     */
    Object getMovementsForVehicleInRange(Pair<Calendar, Calendar> dateRange);
}
