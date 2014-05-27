package road.movementdtos.dtos;

import java.util.Date;

/**
 * Created by Niek on 18/05/14.
 * © Aidas 2014
 */
public class CityDistanceDto
{
    /**
     * The identifier of the city distance.
     */
    private int id;

    /**
     * The name of the city.
     */
    private String cityName;

    /**
     * The amount of driven kilometers.
     */
    private Double drivenKM;

    /**
     * The amount of driven meters.
     */
    private Double drivenMeters;

    /**
     * The price per kilometer.
     */
    private Double km_prijs;

    /**
     * The total costs.
     */
    private Double totalCost;

    /**
     * The date of the movement.
     */
    private Date movementDate;

    /**
     * Creates a new instance of the {@link CityDistanceDto} class.
     * @param id The identifier of the city distance.
     * @param cityName The name of the city.
     * @param drivenMeters The amount of driven meters.
     * @param km_prijs The price per kilometer.
     * @param movementdate The date of the movement.
     */
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

    /**
     * Creates a new instance of the {@link CityDistanceDto} class.
     */
    public CityDistanceDto()
    {
    }

    /**
     * Get the {@link #id} of the {@link CityDistanceDto}.
     * @return The identifier of the city distance.
     */
    public int getId()
    {
        return id;
    }

    /**
     * Set the {@link #id} of the {@link CityDistanceDto}.
     * @param id The identifier of the city distance.
     */
    public void setId(int id)
    {
        this.id = id;
    }

    /**
     * Get the {@link #cityName} of the {@link CityDistanceDto}.
     * @return The name of the city.
     */
    public String getCityName()
    {
        return cityName;
    }

    /**
     * Set the {@link #cityName} of the {@link CityDistanceDto}.
     * @param cityName The name of the city.
     */
    public void setCityName(String cityName)
    {
        this.cityName = cityName;
    }

    /**
     * Get the {@link #drivenKM} of the {@link CityDistanceDto}.
     * @return The amount of driven kilometers.
     */
    public Double getDrivenKM()
    {
        return drivenKM;
    }

    /**
     * Set the {@link #drivenKM} of the {@link CityDistanceDto}.
     * @param drivenKM The amount of driven kilometers.
     */
    public void setDrivenKM(Double drivenKM)
    {
        this.drivenKM = drivenKM;
    }

    /**
     * Get the {@link #drivenMeters} of the {@link CityDistanceDto}.
     * @return The amount of driven meters.
     */
    public Double getDrivenMeters()
    {
        return drivenMeters;
    }

    /**
     * Set the {@link #drivenMeters} of the {@link CityDistanceDto}.
     * @param drivenMeters The amount of driven meters.
     */
    public void setDrivenMeters(Double drivenMeters)
    {
        this.drivenMeters = drivenMeters;
    }

    /**
     * Get the {@link #km_prijs} of the {@link CityDistanceDto}.
     * @return The price per kilometer.
     */
    public Double getKm_prijs()
    {
        return km_prijs;
    }

    /**
     * Set the {@link #km_prijs} of the {@link CityDistanceDto}.
     * @param km_prijs The price per kilometer.
     */
    public void setKm_prijs(Double km_prijs)
    {
        this.km_prijs = km_prijs;
    }

    /**
     * Get the {@link #totalCost} of the {@link CityDistanceDto}.
     * @return The total costs.
     */
    public Double getTotalCost()
    {
        return totalCost;
    }

    /**
     * Set the {@link #totalCost} of the {@link CityDistanceDto}.
     * @param totalCost The total costs.
     */
    public void setTotalCost(Double totalCost)
    {
        this.totalCost = totalCost;
    }

    /**
     * Get the {@link #movementDate} of the {@link CityDistanceDto}.
     * @return The date of the movement.
     */
    public Date getMovementDate()
    {
        return movementDate;
    }

    /**
     * Set the {@link #movementDate} of the {@link CityDistanceDto}.
     * @param movementDate The date of the movement.
     */
    public void setMovementDate(Date movementDate)
    {
        this.movementDate = movementDate;
    }
}
