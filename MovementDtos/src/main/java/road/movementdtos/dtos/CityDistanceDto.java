package road.movementdtos.dtos;

import java.util.Date;

/**
 * Created by Niek on 18/05/14.
 * © Aidas 2014
 */
public class CityDistanceDto
{
    private int id;
    private String cityName;
    private double drivenKM;
    private double km_prijs;
    private double totalCost;
    private Date movementDate;

    public CityDistanceDto(int id, String cityName, double driven, double km_prijs, Date movementdate)
    {
        this.id = id;
        this.cityName = cityName;
        this.drivenKM = (double) Math.round(driven * 100) / 100;
        this.km_prijs = (double) Math.round(km_prijs * 100) / 100;

        this.totalCost = this.drivenKM * this.km_prijs;
        this.totalCost = (double) Math.round(this.totalCost * 100) / 100;
        this.movementDate = movementdate;

    }

    public CityDistanceDto()
    {
    }

    ;

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

    public double getDrivenKM()
    {
        return drivenKM;
    }

    public void setDrivenKM(double drivenKM)
    {
        this.drivenKM = drivenKM;
    }

    public double getKm_prijs()
    {
        return km_prijs;
    }

    public void setKm_prijs(double km_prijs)
    {
        this.km_prijs = km_prijs;
    }

    public double getTotalCost()
    {
        return totalCost;
    }

    public void setTotalCost(double totalCost)
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
