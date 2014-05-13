package road.movemententities.entities;

import javax.persistence.Column;
import javax.persistence.Embeddable;
import java.util.Date;

/**
 * Created by Mitch on 13-5-2014.
 */
@Embeddable
public class CityRateId
{
    @Column(unique = false, nullable = true)
    private String cityId;

    @Column(unique = false, nullable = true)
    private Date addDate;

    public String getCityId() {
        return cityId;
    }

    public void setCityId(String cityId) {
        this.cityId = cityId;
    }

    public Date getAddDate() {
        return addDate;
    }

    public void setAddDate(Date addDate) {
        this.addDate = addDate;
    }
}
