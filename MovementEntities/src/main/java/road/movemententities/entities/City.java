package road.movemententities.entities;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Random;

/**
 *The City class is the an Entity that can map cities to the database
 */
@Entity
public class City implements MovementEntity<String>
{

    /**
     * The ID of the city, as known by OSM
     */
    @Id
    @Column(unique = true, nullable = false)
    private String cityId;

    /**
     * The name of the City
     */
    @Column(unique = false, nullable = true)
    private String cityName;

    /**
     * List of {@link road.movemententities.entities.CityRate Cityrates} for this city
     */
    @OneToMany(cascade = CascadeType.ALL)
    private List<CityRate> cityRates;

    /**
     * Empty Constructor that is required for JPA
     */
    public City()
    {
    }

    /**
     * Create a new City setting only its ID
     * @param cityId
     */
    public City(String cityId)
    {
        this.cityId = cityId;
        this.cityName = "NA" + cityId;
        CityRate rate = new CityRate(this, new Date(), CityRate.defaultKilometerRate);

        if(this.cityRates == null)
        {
            this.cityRates = new ArrayList();
        }

        this.cityRates.add(rate);
    }

    /**
     * Create a new City setting it's ID and name
     * @param cityId the ID of the City to create
     * @param cityName The name of the city
     */
    public City(String cityId, String cityName)
    {
        this.cityId = cityId;
        this.cityName = cityName;

        if(this.cityRates == null){
            this.cityRates = new ArrayList<>();
        }

        //UGLY GENERATOR FOR RATES
        //TODO: REMOVE THIS UGLY GENERATOR
        double min = 0.1;
        double max = 0.60;
        Random random = new Random();

        double randomValue = min + (max - min) * random.nextDouble();

        this.cityRates.add(new CityRate(this, new Date(), randomValue));
    }

    /**
     * Get a list containing all the {@link CityRate cityrates}
     * @return return list of {@link CityRate}
     */
    public List<CityRate> getCityRates()
    {
        return cityRates;
    }

    /**
     * Get the {@link CityRate} that is active for the city
     * @return the current {@link CityRate}
     */
    public CityRate getCurrentRate()
    {
        CityRate currentRate = null;
        for (CityRate c : cityRates)
        {
            if (currentRate == null || c.getId().getAddDate().after(currentRate.getId().getAddDate()))
            {
                currentRate = c;
            }
        }
        return currentRate;
    }


    /**
     * Get the CityID of the city
     * @return the City's CityID
     */
    public String getCityId()
    {
        return cityId;
    }

    /**
     * Get the name of the City
     * @return the name of the city
     */
    public String getCityName()
    {
        return cityName;
    }

    /**
     * Get the ID of the city
     * @return the ID of this city
     */
    public String getId()
    {
        return cityId;
    }

    /**
     * Add a new {@link CityRate} to this city
     * @param cityRate the {@link CityRate} to add
     */
    public void addCityRate(CityRate cityRate)
    {
        if (this.cityRates == null)
        {
            this.cityRates = new ArrayList<>();
        }


        cityRates.add(cityRate);
    }

    /**
     * Set the name of the city
     * @param cityName the new name for the city
     */
    public void setCityName(String cityName)
    {
        this.cityName = cityName;
    }
}
