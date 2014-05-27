package road.movemententities.entities;

import javax.persistence.Column;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import java.util.Date;

/**
 * City Rate entity for saving CityRates
 *
 * Created by Mitch on 12-5-2014.
 */
@Entity
public class CityRate
{
    public static double defaultKilometerRate = 0.10;

    @EmbeddedId
    @Column(unique = false, nullable = true)
    CityRateId id;

    @Column(unique = false, nullable = true)
    private double kilometerRate;

    /**
     * Empty constructor for JPA
     */
    public CityRate(){}

    /**
     * Create a new instance of the CityRate object
     * @param city the {@link City} for this
     * @param addDate the Date the Rate was added
     * @param kilometerRate the kilometer rate for this {@link road.movemententities.entities.CityRate}
     */
    public CityRate(City city, Date addDate, double kilometerRate)
    {
        id = new CityRateId();
        id.setCity(city);
        id.setAddDate(addDate);
        this.kilometerRate = kilometerRate;
    }

    /**
     * Get the ID of this
     * @return
     */
    public CityRateId getId() {
        return id;
    }

    /**
     * Set the ID of this
     * @param id the value of the new ID
     */
    public void setId(CityRateId id) {
        this.id = id;
    }

    /**
     * Get the current rate
     * @return the current rate
     */
    public double getKilometerRate() {
        return kilometerRate;
    }

    /**
     * Set the kilometer rate
     * @param kilometerRate the kilometer rate to set
     */
    public void setKilometerRate(double kilometerRate) {
        this.kilometerRate = kilometerRate;
    }
}
