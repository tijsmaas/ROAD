package road.movementdtos.dtos;

/**
 * Created by geh on 25-5-14.
 */
public class CoordinateDto
{
    private double latitude;
    private double longitude;

    public CoordinateDto()
    {

    }

    public CoordinateDto(double latitude, double longitude)
    {
        this.latitude = latitude;
        this.longitude = longitude;
    }

    public double getLatitude()
    {
        return latitude;
    }

    public double getLongitude()
    {
        return longitude;
    }

    public void setLatitude(double latitude)
    {
        this.latitude = latitude;
    }

    public void setLongitude(double longitude)
    {
        this.longitude = longitude;
    }
}
