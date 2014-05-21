package road.movementservice.mapper;

import road.movementdtos.dtos.*;
import road.movemententities.entities.*;
import road.movemententityaccess.dao.LoginDAO;
import road.userservice.UserDAO;
import road.userservice.dto.UserDto;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
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

    public CityDistanceDto map(CityDistance cityDistance)
    {
        return new CityDistanceDto(cityDistance.getId(), cityDistance.getCity().getCityName(), cityDistance.getDistance(), cityDistance.getKm_prijs(), cityDistance.getMovementDate());
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

    public MovementUserDto toMovementUserDto(MovementUser user)
    {
        MovementUserDto dto = new MovementUserDto()
                                    .id(user.getId())
                                    .userName(user.getUsername())
                                    .email(user.getEmail())
                                    .name(user.getName())
                                    .street(user.getStreet())
                                    .houseNumber(user.getHouseNumber())
                                    .postalCode(user.getPostalCode())
                                    .city(user.getCity())
                                    .invoiceMail(user.isInvoiceMail());
        return dto;
    }

    public StolenCarDto toStolenCarDto(Vehicle vehicle, LoginDAO loginDAO) {
        List<VehicleOwnerDto> owners = new ArrayList<>();
        for(VehicleOwnership owner : vehicle.getVehicleOwners()) {
            owners.add(new VehicleOwnerDto(toMovementUserDto(loginDAO.getUser(owner.getUserID())),
                    owner.getRegistrationdate(), owner.getRegistrationExperationDate()));
        }
        List<VehicleMovementDto> movements = new ArrayList<>();
        for(VehicleMovement vehicleMovement : vehicle.getVehicleMovements()) {
            Movement movement = vehicleMovement.getMovement();
            Lane lane = movement.getLane();
            Edge edge = lane.getEdge();
            movements.add(new VehicleMovementDto(vehicleMovement.getId(), vehicleMovement.getPosition(),
                    vehicleMovement.getSpeed(), movement.getMovementDateTime(), lane.getIndex(), lane.getLength(),
                    edge.getType(), toCityDto(edge.getFrom()), toCityDto(edge.getTo()), edge.getPriority()));
        }
        return new StolenCarDto(vehicle.getCarTrackerID(), vehicle.getLicensePlate(), owners, movements);
    }

    public List<StolenCarDto> toStolenCarDtoList(List<Vehicle> vehicleList, LoginDAO loginDAO) {
        List<StolenCarDto> returnList = new ArrayList<>();
        for (Vehicle v : vehicleList) {
            returnList.add(toStolenCarDto(v, loginDAO));
        }

        return returnList;
    }
}
