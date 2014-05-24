package road.billsystem.webapi;

import road.billsystem.service.BillService;
import road.billsystem.webapi.jsonclasses.NewOwnerHelper;
import road.billsystem.webapi.jsonclasses.NewVehicleObject;
import road.billsystem.webapi.webexceptions.ChangeOwnerFailedException;
import road.billsystem.webapi.webexceptions.VehicleInsertException;
import road.movementdtos.dtos.MovementUserDto;
import road.movementdtos.dtos.VehicleDto;
import road.movementdtos.dtos.VehicleOwnerDto;

import javax.inject.Inject;
import javax.ws.rs.*;
import java.util.List;

/**
 * Created by Niek on 24/05/14.
 * © Aidas 2014
 */
@Path("/CartrackerAPI")
public class CartrackerAPI
{
    @Inject
    private BillService service;

    @GET
    @Path("/users")
    @Produces("application/json")
    public List<MovementUserDto> getAvailableUsers(){
        List<MovementUserDto> users = service.getAllUsers();
        return users;
    }

    @POST
    @Path("/addVehicle")
    @Consumes("application/json")
    @Produces("application/json")
    public VehicleDto addVehicle(NewVehicleObject newVehicle) throws VehicleInsertException
    {
        try
        {
            VehicleDto vehicle = service.addVehicle(newVehicle.getCartrackerID(), newVehicle.getLicensePlate(), newVehicle.getMovementUserID());
            if(vehicle == null){
                throw new VehicleInsertException("Error adding a vehicle to the database");
            }
            return vehicle;
        } catch(Exception ex){
            throw new VehicleInsertException(ex.getMessage());
        }
    }

    @POST
    @Path("/updateVehicleOwner")
    @Consumes("application/json")
    @Produces("application/json")
    public VehicleOwnerDto changeCurrentOwner(NewOwnerHelper newOwnerHelper) throws ChangeOwnerFailedException
    {
        return service.changeVehicleOwner(newOwnerHelper.getVehicleID(), newOwnerHelper.getNewOwnerID());
    }


    @GET
    @Path("/vehicle/{vehicleID}")
    @Consumes("application/json")
    @Produces("application/json")
    public VehicleDto getVehicleDetails(@PathParam("vehicleID") String vehicleID)
    {
        VehicleDto dto = service.getVehicleDetails(Integer.parseInt(vehicleID));
        return dto;
    }
}
