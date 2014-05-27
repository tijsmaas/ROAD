package road.movementdtos.dtos;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.List;

/**
 * Created by Niek on 18/05/14.
 * © Aidas 2014
 */
public class VehicleInvoiceDto
{
    /**
     * The identifier of the vehicle invoice.
     */
    private int id;

    /**
     * The vehicle of the vehicle invoice.
     */
    private VehicleDto vehicle;

    /**
     * The sub total of the vehicle invoice.
     */
    private BigDecimal subTotal;

    /**
     * The amount of kilometers driven for the vehicle invoice.
     */
    private double kilometersDriven;

    /**
     * The city movements of the vehicle invoice.
     */
    private List<CityDistanceDto> cityMovements;

    /**
     * Creates a new instance of the {@link VehicleInvoiceDto} class.
     * @param id The identifier of the vehicle invoice.
     * @param vehicleDto The vehicle of the vehicle invoice.
     * @param subTotal The sub total of the vehicle invoice.
     * @param metersDriven The amount of kilometers driven for the vehicle invoice.
     */
    public VehicleInvoiceDto(int id, VehicleDto vehicleDto, BigDecimal subTotal, int metersDriven)
    {
        this.id = id;
        this.vehicle  = vehicleDto;
        this.subTotal = subTotal.setScale(2, RoundingMode.CEILING);
        this.kilometersDriven = metersDriven / 1000;
        this.kilometersDriven = Math.round(this.kilometersDriven * 100) / 100;
    }

    /**
     * Creates a new instance of the {@link VehicleInvoiceDto} class.
     */
    public VehicleInvoiceDto(){};

    /**
     * Get the {@link #id} of the {@link VehicleInvoiceDto}.
     * @return The identifier of the vehicle invoice.
     */
    public int getId()
    {
        return id;
    }

    /**
     * Set the {@link #id} of the {@link VehicleInvoiceDto}.
     * @param id The identifier of the vehicle invoice.
     */
    public void setId(int id)
    {
        this.id = id;
    }

    /**
     * Get the {@link #vehicle} of the {@link VehicleInvoiceDto}.
     * @return The vehicle of the vehicle invoice.
     */
    public VehicleDto getVehicle()
    {
        return vehicle;
    }

    /**
     * Set the {@link #vehicle} of the {@link VehicleInvoiceDto}.
     * @param vehicle The vehicle of the vehicle invoice.
     */
    public void setVehicle(VehicleDto vehicle)
    {
        this.vehicle = vehicle;
    }

    /**
     * Get the {@link #subTotal} of the {@link VehicleInvoiceDto}.
     * @return The sub total of the vehicle invoice.
     */
    public BigDecimal getSubTotal()
    {
        return subTotal;
    }

    /**
     * Set the {@link #subTotal} of the {@link VehicleInvoiceDto}.
     * @param subTotal The sub total of the vehicle invoice.
     */
    public void setSubTotal(BigDecimal subTotal)
    {
        this.subTotal = subTotal;
    }

    /**
     * Get the {@link #cityMovements} of the {@link VehicleInvoiceDto}.
     * @return The city movements of the vehicle invoice.
     */
    public List<CityDistanceDto> getCityMovements()
    {
        return cityMovements;
    }

    /**
     * Set the {@link #cityMovements} of the {@link VehicleInvoiceDto}.
     * @param cityMovements The city movements of the vehicle invoice.
     */
    public void setCityMovements(List<CityDistanceDto> cityMovements)
    {
        this.cityMovements = cityMovements;
    }

    /**
     * Get the {@link #kilometersDriven} of the {@link VehicleInvoiceDto}.
     * @return The amount of kilometers driven for the vehicle invoice.
     */
    public double getKilometersDriven()
    {
        return kilometersDriven;
    }

    /**
     * Set the {@link #kilometersDriven} of the {@link VehicleInvoiceDto}.
     * @param kilometersDriven The amount of kilometers driven for the vehicle invoice.
     */
    public void setKilometersDriven(double kilometersDriven)
    {
        this.kilometersDriven = kilometersDriven;
    }


}
