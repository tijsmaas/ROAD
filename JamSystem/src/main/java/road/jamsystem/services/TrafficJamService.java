package road.jamsystem.services;

import road.jamsystem.services.internal.JamService;
import road.movementdtos.dtos.LaneDto;

import javax.inject.Inject;
import javax.jws.WebMethod;
import javax.jws.WebService;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Geert on 21/05/2014.
 */
@WebService
public class TrafficJamService
{
    // TODO: Injection in webservice?
    /*
    @Inject
    private JamService jamService;
    */

    /**
     * Create a new instance of the {@link TrafficJamService} class.
     */
    public TrafficJamService()
    {
    }

    @WebMethod
    public List<LaneDto> getTrafficJams()
    {
        // TODO: Get the lanes with a traffic jam from the jam service. Injection?
        // TODO: Use api keys and limit the amount of calls per hour.
        return new ArrayList();
    }
}
