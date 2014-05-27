package road.billsystem.webapi.exceptionmapper;

import road.billsystem.webapi.webexceptions.VehicleInsertException;

import javax.ws.rs.core.Response;
import javax.ws.rs.ext.ExceptionMapper;

/**
 * Created by Niek on 24/05/14.
 *  Aidas 2014
 */
public class VehicleInsertionExceptionMapper
    implements ExceptionMapper<VehicleInsertException>
{
    @Override
    public Response toResponse(VehicleInsertException exception)
    {
        return Response.status(500).entity(exception.getMessage()).type("text/plain").build();
    }
}
