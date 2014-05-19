package road.movementdtos.dtos;

/**
 * Created by Niek on 18/05/14.
 * © Aidas 2014
 */
public class CityDistanceDto
{
    private int id;
    private String cityName;
//    private double driven;
//    private double drivenKM;
//    private double km_prijs;
//    private double totalCost;

    public CityDistanceDto(int id, String cityName, double driven, double km_prijs)
    {
        this.id = id;
        this.cityName = cityName;
//        this.driven = driven;
//        this.km_prijs = km_prijs;
//
//        double drivenKM = this.driven / 100;
//        drivenKM = (double) Math.round(drivenKM * 100) / 100;
//        this.drivenKM = drivenKM;
//
//        this.totalCost = this.drivenKM * this.km_prijs;
//        this.totalCost = (double) Math.round(this.totalCost * 100) / 100;

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

//    public double getDriven()
//    {
//        return driven;
//    }
//
//    public void setDriven(double driven)
//    {
//        this.driven = driven;
//    }
//
//    public double getDrivenKM()
//    {
//        return drivenKM;
//    }
//
//    public void setDrivenKM(double drivenKM)
//    {
//        this.drivenKM = drivenKM;
//    }
//
//    public double getKm_prijs()
//    {
//        return km_prijs;
//    }
//
//    public void setKm_prijs(double km_prijs)
//    {
//        this.km_prijs = km_prijs;
//    }
//
//    public double getTotalCost()
//    {
//        return totalCost;
//    }
//
//    public void setTotalCost(double totalCost)
//    {
//        this.totalCost = totalCost;
//    }
}
