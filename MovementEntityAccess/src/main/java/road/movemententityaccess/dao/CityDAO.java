package road.movemententityaccess.dao;

import road.movemententities.entities.City;

import java.util.Date;
import java.util.List;

/**
 * the {@link road.movemententityaccess.dao.CityDAO} class contains all Database actions involving Citites
 *
 * Created by Mitch on 28/03/14.
 *  Aidas 2014
 */
public interface CityDAO
{

    /**
     * Get the current amount of cities in the db
     * @return the amount of cities in the db
     */
    Long count();

    /**
     * Find a city by the SUMO city identifier
     * @param cityIdentifier The sumo City Identifier
     * @return The found City object
     */
    City find(String cityIdentifier);

    /**
     * Find all cities in the database
     * @return list of found {@link City cities}
     */
    List<City> findAll();

    /**
     * Adjust the kilometr rate for a city
     * @param cityId the ID of the city to change it for
     * @param addDate the new date of the kilomter rate
     * @param price the new price of the kilometer rate
     * @return True when success, false when not.
     */
    boolean adjustKilometerRate(String cityId, Date addDate, double price);

}
