package road.movemententities.entities;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;

/**
 * Created by Mitch on 13-5-2014.
 */
public class CityRateId implements Serializable
{
    @ManyToOne
    private City city;

    @Temporal(TemporalType.DATE)
    private Date addDate;

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
}
