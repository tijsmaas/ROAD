package road.movemententities.entities;

import javax.persistence.*;
import java.util.Date;

/**
 * Created by Mitch on 12-5-2014.
 */
@Entity
@IdClass(value=CityRateId.class)
public class CityRate
{
    @Id
    private City city;

    @Id
    private Date addDate;

    private String kilometerRate;

    public CityRate(){}

    public CityRate(City city, Date addDate, String kilometerRate)
    {
        /*id = new CityRateId();
        id.setCity(city);
        id.setAddDate(addDate);*/
        this.city = city;
        this.addDate = addDate;
        this.kilometerRate = kilometerRate;
    }

    public City getCity() {
        return city;
    }

    public void setCity(City city) {
        this.city = city;
    }

    public Date getAddDate() {
        return addDate;
    }

    public void setAddDate(Date addDate) {
        this.addDate = addDate;
    }

    public String getKilometerRate() {
        return kilometerRate;
    }

    public void setKilometerRate(String kilometerRate) {
        this.kilometerRate = kilometerRate;
    }
}
