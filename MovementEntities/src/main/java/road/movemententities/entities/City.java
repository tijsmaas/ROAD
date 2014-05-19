package road.movemententities.entities;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Random;

@Entity
public class City implements MovementEntity<String>
{

    @Id
    @Column(unique = true, nullable = false)
    private String cityId;

    @Column(unique = false, nullable = true)
    private String cityName;

    @OneToMany(cascade = CascadeType.ALL)
    private List<CityRate> cityRates;

    // Empty constructor for JPA
    public City()
    {
    }

    public City(String cityId)
    {
        this.cityId = cityId;
    }

    public List<CityRate> getCityRates()
    {
        return cityRates;
    }

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

        this.cityRates.add(new CityRate(this, new Date(), String.valueOf(randomValue)));
    }

    public String getCityId()
    {
        return cityId;
    }

    public String getCityName()
    {
        return cityName;
    }

    public String getId()
    {
        return cityId;
    }

    public void addCityRate(CityRate cityRate)
    {
        if (this.cityRates == null)
        {
            this.cityRates = new ArrayList<>();
        }


        cityRates.add(cityRate);
    }

}
