package road.movementdtos.dtos;

import java.math.BigDecimal;
import java.util.List;

/**
 * Created by Niek on 18/05/14.
 * © Aidas 2014
 */
public class VehicleInvoiceDto
{
    private int id;
    private VehicleDto vehicle;
    private BigDecimal subTotal;
    private double kilometersDriven;
    private List<CityDistanceDto> cityMovements;

    public VehicleInvoiceDto(int id, VehicleDto vehicleDto, BigDecimal subTotal, int metersDriven)
    {
        this.id = id;
        this.vehicle  = vehicleDto;
        this.subTotal = subTotal;
        this.kilometersDriven = metersDriven / 1000;
        this.kilometersDriven = Math.round(this.kilometersDriven * 100) / 100;
    }

    public VehicleInvoiceDto(){};


    public int getId()
    {
        return id;
    }

    public void setId(int id)
    {
        this.id = id;
    }

    public VehicleDto getVehicle()
    {
        return vehicle;
    }

    public void setVehicle(VehicleDto vehicle)
    {
        this.vehicle = vehicle;
    }

    public BigDecimal getSubTotal()
    {
        return subTotal;
    }

    public void setSubTotal(BigDecimal subTotal)
    {
        this.subTotal = subTotal;
    }

    public List<CityDistanceDto> getCityMovements()
    {
        return cityMovements;
    }

    public void setCityMovements(List<CityDistanceDto> cityMovements)
    {
        this.cityMovements = cityMovements;
    }

    public double getKilometersDriven()
    {
        return kilometersDriven;
    }

    public void setKilometersDriven(double kilometersDriven)
    {
        this.kilometersDriven = kilometersDriven;
    }
}
