package road.carsystem.api;

import road.carsystem.webservices.trafficjam.JamException_Exception;
import road.carsystem.webservices.trafficjam.LaneDto;
import road.carsystem.webservices.trafficjam.TrafficJamServiceConnection;
import road.carsystem.webservices.trafficjam.Trafficjamservice;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Named;
import javax.xml.ws.WebServiceRef;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Geert on 24/05/2014.
 */
@Named("trafficJamBean")
@ApplicationScoped
public class TrafficJamBean
{
    private static final String API_KEY = "d62969450c934541a4ac59ea1a4c6943";

    /**
     * Web service reference to the traffic jam service.
     */
    @WebServiceRef(Trafficjamservice.class)
    private TrafficJamServiceConnection trafficJamService;

    /**
     * String containing a possible error message of a call to the {@link #trafficJamService}.
     */
    private String errorMessage;

    /**
     * A collection containing all lanes that currently have a traffic jam on them.
     */
    private List<LaneDto> lanesWithTrafficJams;

    /**
     * Create a new instance of the {@link TrafficJamBean} bean.
     */
    public TrafficJamBean()
    {
        this.errorMessage = null;
        this.lanesWithTrafficJams = new ArrayList();
    }

    /**
     * Call the {@link #trafficJamService} to get the current traffic jams.
     */
    public void updateTrafficJams()
    {
        this.errorMessage = null;

        try
        {
            this.lanesWithTrafficJams = this.trafficJamService.getTrafficJams(API_KEY);
        } catch (JamException_Exception e)
        {
            e.printStackTrace();

            this.errorMessage = e.getMessage();
        }
    }

    public String getErrorMessage()
    {
        return errorMessage;
    }

    public void setErrorMessage(String errorMessage)
    {
        this.errorMessage = errorMessage;
    }

    public List<LaneDto> getLanesWithTrafficJams()
    {
        return lanesWithTrafficJams;
    }

    public void setLanesWithTrafficJams(List<LaneDto> lanesWithTrafficJams)
    {
        this.lanesWithTrafficJams = lanesWithTrafficJams;
    }
}
