package road.movementdtos.dtos;

import java.math.BigDecimal;
import java.util.List;

/**
 * Created by Niek on 18/05/14.
 * © Aidas 2014
 */
public class VehicleInvoiceDto
{
    private int vehicleInvoiceID;
    private VehicleDto vehicle;
    private BigDecimal subTotal;
    private double kilometersDriven;
    private List<CityMovementDto> cityMovements;

    public VehicleInvoiceDto(int vehicleInvoiceID, VehicleDto vehicleDto, BigDecimal subTotal, int metersDriven)
    {
        this.vehicleInvoiceID = vehicleInvoiceID;
        this.vehicle  = vehicleDto;
        this.subTotal = subTotal;
        this.kilometersDriven = metersDriven / 100;
        this.kilometersDriven = kilometersDriven * 100;
        this.kilometersDriven = Math.round(kilometersDriven);
        this.kilometersDriven = kilometersDriven / 100;
    }

    public VehicleInvoiceDto(){};


    public int getVehicleInvoiceID()
    {
        return vehicleInvoiceID;
    }

    public void setVehicleInvoiceID(int vehicleInvoiceID)
    {
        this.vehicleInvoiceID = vehicleInvoiceID;
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

    public List<CityMovementDto> getCityMovements()
    {
        return cityMovements;
    }

    public void setCityMovements(List<CityMovementDto> cityMovements)
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
