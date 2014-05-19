package road.movemententities.entities;

import java.util.List;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.OneToMany;

@Entity
public class City implements MovementEntity<String> {

    @Id
    @Column(unique = true, nullable = false)
    private String cityId;

    @Column(unique = false, nullable = true)
    private String cityName;

    @Column(unique = false, nullable = true)
    @OneToMany
    private List<CityRate> cityRates;

    // Empty constructor for JPA
    public City() { }

    public City(String cityId) {
        this.cityId = cityId;
    }

    public List<CityRate> getCityRates() {
        return cityRates;
    }

    public CityRate getCurrentRate()
    {
        CityRate currentRate = null;
        for (CityRate c : cityRates) {
            if(currentRate == null || c.getId().getAddDate().after(currentRate.getId().getAddDate()))
            {
                currentRate = c;
            }
        }
        return currentRate;
    }

    public City(String cityId, String cityName) {
        this.cityId = cityId;
        this.cityName = cityName;
    }

    public String getCityId() {
        return cityId;
    }

    public String getCityName() {
        return cityName;
    }

    public String getId() {
        return cityId;
    }

    public void addCityRate(CityRate cityRate)
    {
        cityRates.add(cityRate);
    }

}
