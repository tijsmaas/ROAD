package road.movementservice.mapper;

import road.movementdtos.dtos.*;
import road.movemententities.entities.*;
import road.movemententityaccess.dao.LoginDAO;

import java.math.BigDecimal;
import java.util.*;

/**
 * The Dto Mapper contains functions for mapping Entity objects to Dto objects
 * <p/>
 * <p/>
 * TODO: Look for a way to implement a Java alternative to automapper
 * <p/>
 * Created by Niek on 18/05/14.
 * © Aidas 2014
 */
public class DtoMapper
{

    /**
     * Map an invoice object to a simple InvoiceDTO, not containing any child relations
     * @param invoice
     * @return
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

        MovementUserDto movementUserDto = this.toMovementUserDto(invoice.getUser());
        InvoiceDto invoiceDto = new InvoiceDto(invoice.getInvoiceID(), movementUserDto, invoice.getGenerationDate(), invoice.getStartDate(), invoice.getEndDate(), invoice.getPaymentStatus().ordinal(), total);

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

    public VehicleOwnerDto map(VehicleOwnership ownership)
    {
        Date expirationDate = null;
        if(ownership.getRegistrationExperationDate() != null)
        {
            expirationDate = ownership.getRegistrationExperationDate().getTime();
        }
        return new VehicleOwnerDto(this.toMovementUserDto(ownership.getUser()), ownership.getRegistrationdate().getTime(), expirationDate);
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
        for (VehicleOwnership vo : vehicle.getVehicleOwners())
        {
            if (vo.getRegistrationExperationDate() == null)
            {
                currentOwer = vo;
                break;
            }
        }

        boolean contributeGPSData = currentOwer != null ? currentOwer.getContributeGPSData() : false;

        return new VehicleDto(vehicle.getVehicleID(), vehicle.getLicensePlate(), contributeGPSData, vehicle.isStolen(), vehicle.getCarTrackerID());
    }

    /**
     * Map the {@link VehicleMovement} to a {@link VehicleMovementDto}.
     * @param vehicleMovement the vehicle movement to map.
     * @return the mapped vehicle movement.
     */
    public VehicleMovementDto map(VehicleMovement vehicleMovement) {
        return new VehicleMovementDto(
                vehicleMovement.getId(),
                vehicleMovement.getPosition(),
                vehicleMovement.getSpeed(),
                vehicleMovement.getMovement().getMovementDateTime(),
                vehicleMovement.getMovement().getLane().getIndex(),
                vehicleMovement.getMovement().getLane().getLength(),
                vehicleMovement.getMovement().getLane().getEdge().getType(),
                this.toCityDto(vehicleMovement.getMovement().getLane().getEdge().getFrom()),
                this.toCityDto(vehicleMovement.getMovement().getLane().getEdge().getTo()),
                vehicleMovement.getMovement().getLane().getEdge().getPriority());
    }

    /**
     * Map the collection of {@link VehicleMovement} to a collection of {@link VehicleMovementDto}.
     * @param vehicleMovements the vehicle movements to map.
     * @return the collection containing the mapped vehicle movements.
     */
    public List<VehicleMovementDto> mapVehicleMovements(List<VehicleMovement> vehicleMovements)
    {
        List<VehicleMovementDto> returnList = new ArrayList();
        for (VehicleMovement vehicleMovement : vehicleMovements)
        {
            returnList.add(this.map(vehicleMovement));
        }

        return returnList;
    }

    /**
     * Map the {@link Lane} to a {@link LaneDto}.
     * @param lane the lane to map.
     * @return the mapped lane.
     */
    public LaneDto map(Lane lane)
    {
        List<String> laneIdsFrom = new ArrayList();
        for (Lane laneFrom : lane.getLanesFrom())
        {
            laneIdsFrom.add(laneFrom.getId());
        }

        List<String> laneIdsTo = new ArrayList();
        for (Lane laneTo : lane.getLanesTo())
        {
            laneIdsFrom.add(laneTo.getId());
        }

        return new LaneDto(lane.getId(), lane.getLength(), laneIdsFrom, laneIdsTo);
    }

    /**
     * Map the {@link Movement} to a {@link MovementDto}.
     * @param movement the movement to map.
     * @return the mapped movement.
     */
    public MovementDto map(Movement movement)
    {
        return new MovementDto(
                this.map(movement.getLane()),
                this.mapVehicleMovements(movement.getVehicleMovements()));
    }

    /**
     * Maps a collection of {@link Movement} to {@link MovementDto}
     * @param movements the movements to map.
     * @return the mapped movements.
     */
    public List<MovementDto> mapMovements(List<Movement> movements)
    {
        List<MovementDto> returnList = new ArrayList<>();
        for (Movement movement : movements)
        {
            returnList.add(this.map(movement));
        }

        return returnList;
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
        for (Vehicle v : vehicles)
        {
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
     * Convert the {@link road.movemententities.entities.City} to a {@link road.movementdtos.dtos.CityDto} class.
     * @param city the city entity to convert.
     * @return the converted city entity.
     */
    public CityDto toCityDto(City city)
    {
        return city == null ? null : new CityDto(city.getCityId(), city.getCityName(), city.getCurrentRate() == null ? null : city.getCurrentRate().getKilometerRate());
    }

    /**
     * Convert the {@link road.movemententities.entities.City} to a {@link road.movementdtos.dtos.CityDto} class.
     * @param city the city entity to convert.
     * @return the converted city entity.
     */
    public City toCity(CityDto city)
    {
        return new City(city.getCityId(), city.getCityName());
    }

    /**
     *  Convert the {@link road.movemententities.entities.City} list to a {@link road.movementdtos.dtos.CityDto} class list
     * @param cityList the list of cities.
     * @return the converted city list
     */
    public List<CityDto> toCityDtoList(List<City> cityList)
    {
        List<CityDto> returnList = new ArrayList<>();
        for (City c : cityList)
        {
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

    public List<VehicleOwnerDto> mapVehicleOwners(List<VehicleOwnership> owners, LoginDAO loginDAO)
    {
        List<VehicleOwnerDto> ownerDtos = new ArrayList<>();
        for(VehicleOwnership owner : owners)
        {
            Date registrationDate = null;
            if(owner.getRegistrationdate() != null)
            {
                registrationDate = new Date(owner.getRegistrationdate().getTimeInMillis());
            }
            Date registrationExpirationDate = null;
            if(owner.getRegistrationExperationDate() != null)
            {
                registrationExpirationDate = new Date(owner.getRegistrationExperationDate().getTimeInMillis());
            }
            ownerDtos.add(new VehicleOwnerDto(toMovementUserDto(loginDAO.getUser(owner.getUser().getId())),
                    registrationDate, registrationExpirationDate));
        }
        return ownerDtos;
    }
}
