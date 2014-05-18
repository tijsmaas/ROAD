package road.movementdtos.dtos;

import java.math.BigDecimal;
import java.util.List;

/**
 * Created by Niek on 18/05/14.
 * © Aidas 2014
 */
public class VehicleInvoiceDto
{
    public int vehicleInvoiceID;
    public VehicleDto vehicle;
    public BigDecimal subTotal;
    public List<CityMovementDto> cityMovements;

    public VehicleInvoiceDto(int vehicleInvoiceID, VehicleDto vehicleDto, BigDecimal subTotal)
    {
        this.vehicleInvoiceID = vehicleInvoiceID;
        this.vehicle  = vehicleDto;
        this.subTotal = subTotal;
    }

    public VehicleInvoiceDto(){};
}
