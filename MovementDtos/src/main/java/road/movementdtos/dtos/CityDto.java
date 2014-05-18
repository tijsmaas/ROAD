package road.movementdtos.dtos;

/**
 * Created by Mitch on 18-5-2014.
 */
public class CityDto {
    private String cityId;
    private String cityName;

    public CityDto(String cityId) {
        this.cityId = cityId;
    }

    public CityDto(String cityId, String cityName) {
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
}
