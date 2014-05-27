package road.movementdtos.dtos;

import java.util.Date;

/**
 * Created by Niek on 18/05/14.
 *  Aidas 2014
 */
public class CityDistanceDto
{
    private int id;
    private String cityName;
    private Double drivenKM;
    private Double drivenMeters;
    private Double km_prijs;
    private Double totalCost;
    private Date movementDate;

    public CityDistanceDto(int id, String cityName, double drivenMeters, double km_prijs, Date movementdate)
    {
        this.id = id;
        this.cityName = cityName;
        this.drivenMeters = drivenMeters;
        this.drivenKM = (double) Math.round((drivenMeters / 1000) * 100) / 100;
        this.km_prijs = (double) Math.round(km_prijs * 100) / 100;

        this.totalCost = this.drivenKM * this.km_prijs;
        this.totalCost = (double) Math.round(this.totalCost * 100) / 100;
        this.movementDate = movementdate;

    }

    public CityDistanceDto()
    {
    }


    public int getId()
    {
        return id;
    }

    public void setId(int id)
    {
        this.id = id;
    }

    public String getCityName()
    {
        return cityName;
    }

    public void setCityName(String cityName)
    {
        this.cityName = cityName;
    }

    public Double getDrivenKM()
    {
        return drivenKM;
    }

    public void setDrivenKM(Double drivenKM)
    {
        this.drivenKM = drivenKM;
    }

    public Double getDrivenMeters()
    {
        return drivenMeters;
    }

    public void setDrivenMeters(Double drivenMeters)
    {
        this.drivenMeters = drivenMeters;
    }

    public Double getKm_prijs()
    {
        return km_prijs;
    }

    public void setKm_prijs(Double km_prijs)
    {
        this.km_prijs = km_prijs;
    }

    public Double getTotalCost()
    {
        return totalCost;
    }

    public void setTotalCost(Double totalCost)
    {
        this.totalCost = totalCost;
    }

    public Date getMovementDate()
    {
        return movementDate;
    }

    public void setMovementDate(Date movementDate)
    {
        this.movementDate = movementDate;
    }
}
