package road.movementservice.mapper;

import road.movementdtos.dtos.CityDto;
import road.movementdtos.dtos.InvoiceDto;
import road.movementdtos.dtos.VehicleDto;
import road.movementdtos.dtos.VehicleInvoiceDto;
import road.movemententities.entities.*;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

/**
 * The Dto Mapper contains functions for mapping Entity objects to Dto objects
 *
 *
 * TODO: Look for a way to implement a Java alternative to automapper
 *
 * Created by Niek on 18/05/14.
 * © Aidas 2014
 */
public class DtoMapper
{

    /**
     * Map an Invoice object to a simple invoiceDTO, not containing any of the child relations
     *
     * @param invoice the invoice to map
     * @return simple InvoiceDTO
     */
    public InvoiceDto mapSimple(Invoice invoice)
    {
        if (invoice == null)
        {
            return null;
        }
        BigDecimal total = new BigDecimal("0");
        for (VehicleInvoice vehicleInvoice : invoice.getVehicleInvoices())
        {
            total = total.add(vehicleInvoice.getSubTotal());
        }

        InvoiceDto invoiceDto = new InvoiceDto(invoice.getInvoiceID(), invoice.getUserID(), invoice.getGenerationDate(), invoice.getStartDate(), invoice.getEndDate(), invoice.getPaymentStatus().ordinal(), total);

        return invoiceDto;
    }

    /**
     * Map a VehicleInvoice to a simple version of the VehicleInvoiceDTO, not containing the list of cityMovements
     *
     * @param vehicleInvoice the vehicleInvoice to map
     * @return The VehicleInvoiceDto object
     */
    public VehicleInvoiceDto mapSimple(VehicleInvoice vehicleInvoice)
    {
        return new VehicleInvoiceDto(vehicleInvoice.getId(), this.map(vehicleInvoice.getOwnership().getVehicle()), vehicleInvoice.getSubTotal(), vehicleInvoice.getMetersDriven());
    }

    /**
     * Maps a Vehicle to a VehicleDTO
     *
     * @param vehicle The Vehicle object to map
     * @return The Vehicle Dto object
     */
    public VehicleDto map(Vehicle vehicle)
    {
        VehicleOwnership currentOwer = null;
        for (VehicleOwnership vo : vehicle.getVehicleOwners()) {
            if (vo.getRegistrationExperationDate() == null) {
                currentOwer = vo;
                break;
            }
        }

        boolean contributeGPSData = currentOwer != null ? currentOwer.getContributeGPSData() : false;

        return new VehicleDto(vehicle.getLicensePlate(), contributeGPSData);
    }

    /**
     * Maps a collection of Vehicle to a VehicleDTO
     *
     * @param vehicles The Vehicle objects to map
     * @return The Vehicle Dto objects
     */
    public List<VehicleDto> map(List<Vehicle> vehicles)
    {
        List<VehicleDto> returnList = new ArrayList<>();
        for (Vehicle v : vehicles) {
            returnList.add(this.map(v));
        }

        return returnList;
    }

    /**
     * Map an Invoice to a Dto, containing all the invoice details.
     *
     * @param invoice The invoice to map
     * @return The Invoice Dto containing the children (like vehicleMovements)
     */
    public InvoiceDto map(Invoice invoice)
    {
        if (invoice == null)
        {
            return null;
        }

        InvoiceDto invoiceDto = this.mapSimple(invoice);

        List<VehicleInvoiceDto> vehicleInvoiceList = new ArrayList<>();
        for (VehicleInvoice vehicleInvoice : invoice.getVehicleInvoices())
        {
            vehicleInvoiceList.add(this.mapSimple(vehicleInvoice));
        }

        invoiceDto.setVehicleInvoices(vehicleInvoiceList);

        return invoiceDto;
    }

    /**
     *
     * Convert the {@link road.movemententities.entities.City} to a {@link road.movementdtos.dtos.CityDto} class.
     * @param city the city entity to convert.
     * @return the converted city entity.
     */
    public CityDto toCityDto(City city) {
        return new CityDto(city.getCityId(), city.getCityName(), city.getCurrentRate() == null ? null : city.getCurrentRate().getKilometerRate());
    }

    /**
     *
     * Convert the {@link road.movemententities.entities.City} to a {@link road.movementdtos.dtos.CityDto} class.
     * @param city the city entity to convert.
     * @return the converted city entity.
     */
    public City toCity(CityDto city) {
        return new City(city.getCityId(), city.getCityName());
    }

    /**
     *  Convert the {@link road.movemententities.entities.City} list to a {@link road.movementdtos.dtos.CityDto} class list
     * @param cityList the list of cities.
     * @return the converted city list
     */
    public List<CityDto> toCityDtoList(List<City> cityList) {
        List<CityDto> returnList = new ArrayList<>();
        for (City c : cityList) {
            returnList.add(toCityDto(c));
        }

        return returnList;
    }
}
