package road.movemententityaccess.dao;

import road.movemententities.entities.City;

import java.util.Date;
import java.util.List;

/**
 * Created by Mitch on 28/03/14.
 * © Aidas 2014
 */
public interface CityDAO
{

    Long count();

    /**
     * Find a city by the SUMO city identifier
     * @param cityIdentifier The sumo City Identifier
     * @return The found City object
     */
    City find(String cityIdentifier);

    List<City> findAll();

    boolean adjustKilometerRate(City city, Date addDate, String price);

}
