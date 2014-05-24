package road.billsystem.webapi.exceptionmapper;

import road.billsystem.webapi.webexceptions.ChangeOwnerFailedException;

import javax.ws.rs.core.Response;
import javax.ws.rs.ext.ExceptionMapper;

/**
 * Created by Niek on 25/05/14.
 * © Aidas 2014
 */
public class ChangeOwnerFailedExceptionMapper
        implements ExceptionMapper<ChangeOwnerFailedException>
{
    @Override
    public Response toResponse(ChangeOwnerFailedException exception)
    {
        return Response.status(500).entity(exception.getMessage()).type("text/plain").build();
    }
}
