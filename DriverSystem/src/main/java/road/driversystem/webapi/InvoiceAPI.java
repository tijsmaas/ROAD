package road.driversystem.webapi;

import road.driversystem.service.DriverService;
import road.movementdtos.dtos.CityDistanceDto;

import javax.inject.Inject;
import javax.ws.rs.*;
import java.util.List;

/**
 * Created by Niek on 19/05/14.
 *  Aidas 2014
 */
@Path("/invoiceAPI")
public class InvoiceAPI
{
    @Inject
    private DriverService driverService;

    @GET
    @Path("/details/{vehicleInvoiceID}")
    @Consumes("application/json")
    @Produces("application/json")
    public List<CityDistanceDto> movementList(@PathParam("vehicleInvoiceID") int vehicleInvoiceID)
    {
        try
        {
            List<CityDistanceDto> distanances = driverService.getCityMovements(vehicleInvoiceID);
            return distanances;
        } catch(Exception ex){
            ex.printStackTrace();
        }
        return null;
    }
}
