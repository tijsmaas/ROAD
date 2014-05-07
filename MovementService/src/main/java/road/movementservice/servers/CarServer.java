package road.movementservice.servers;

import javax.enterprise.context.SessionScoped;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import java.io.Serializable;

/**
 * Created by geh on 7-5-14.
 */
@Path("car") @SessionScoped
public class CarServer implements Serializable
{
    private int lastMessage = 0;

    public CarServer()
    {

    }

    @POST @Path("movement/{key}|{sequence}|{xml}")
    public String addMovement(@PathParam("key") String key, @PathParam("sequence") int sequence, @PathParam("xml") String xml)
    {
        return null;
    }
}
