package road.movementdtos.dtos;

/**
 * Created by Mitch on 18-5-2014.
 */
public class CityDto {
    private String cityId;
    private String cityName;
    private double currentRate;

    public CityDto() {
    }

    public CityDto(String cityId) {
        this.cityId = cityId;
    }

    public CityDto(String cityId, String cityName, double currentRate) {
        this.cityId = cityId;
        this.cityName = cityName;
        this.currentRate = currentRate;
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

    public double getCurrentRate()
    {
        return currentRate;
    }

    public void setCityId(String cityId) {
        this.cityId = cityId;
    }

    public void setCityName(String cityName) {
        this.cityName = cityName;
    }

    public void setCurrentRate(double currentRate) {
        this.currentRate = currentRate;
    }
}
