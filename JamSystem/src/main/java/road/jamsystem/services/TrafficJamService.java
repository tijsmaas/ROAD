package road.jamsystem.services;

import road.jamsystem.beans.TrafficJamBean;
import road.jamsystem.exceptions.JamException;
import road.jamsystem.services.internal.JamService;
import road.movementdtos.dtos.LaneDto;

import javax.ejb.Stateless;
import javax.inject.Inject;
import javax.jws.WebMethod;
import javax.jws.WebParam;
import javax.jws.WebService;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.net.URL;
import java.util.GregorianCalendar;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author Geert
 */
@WebService(name= "TrafficJamServiceConnection", serviceName = "trafficjamservice")
public class TrafficJamService
{
    private final static String KEY_FILE_NAME = "authentication.ini";
    private final static int REQUESTS_PER_HOUR = 25000;

    @Inject
    private TrafficJamBean trafficJamBean;

    private Map<String, GregorianCalendar> keyUsings;

    /**
     * Create a new instance of the {@link TrafficJamService} class.
     */
    public TrafficJamService()
    {
        this.keyUsings = new HashMap();
    }

    /**
     * Function to get the current traffic jams.
     * @param apiKey the api key of the client that executes this function.
     * @return a list of lanes on which currently the traffic is jammed.
     * @throws JamException when the api key is invalid on not ready to be used.
     */
    @WebMethod(operationName = "getTrafficJams")
    public List<LaneDto> getTrafficJams(@WebParam(name = "ApiKey") String apiKey) throws JamException
    {
        if (!processApiKey(apiKey))
        {
            throw new JamException("Api key is invalid or cannot be used at this time.");
        }

        //return this.jamService.getLanesWithJam();
        return this.trafficJamBean.getLanesWithJam();
    }

    /**
     * Process the api key. Check if it's valid and update the last time it has been used.
     * @param apiKey the api key to process.
     * @return true if the api key is valid and can be used, otherwise false.
     */
    private boolean processApiKey(String apiKey)
    {
        boolean keyValidated = false;

        if (apiKey != null)
        {
            GregorianCalendar lastKeyUsing = this.keyUsings.get(apiKey);

            if (lastKeyUsing == null)
            {
                keyValidated = this.validateApiKey(apiKey);
            }
            else
            {
                GregorianCalendar lastCallAllowed = new GregorianCalendar();
                lastCallAllowed.add(GregorianCalendar.MINUTE, -60 / REQUESTS_PER_HOUR);

                keyValidated = lastKeyUsing.before(lastCallAllowed);
            }
        }

        if (keyValidated)
        {
            this.keyUsings.put(apiKey, new GregorianCalendar());
        }

        return keyValidated;
    }

    /**
     * Check if the api key is a valid api key by checking all allowed api keys in the file.
     * @param apiKey the api key to validate.
     * @return true if the provided api key is valid, otherwise false.
     */
    private boolean validateApiKey(String apiKey)
    {
        boolean keyValidated = false;

        try
        {
            URL resourceFileURL = this.getClass().getResource("/" + KEY_FILE_NAME);
            BufferedReader reader = new BufferedReader(new FileReader(resourceFileURL.getFile()));

            String key = reader.readLine();
            while (key != null)
            {
                if (apiKey.equals(key))
                {
                    keyValidated = true;
                    break;
                }

                key = reader.readLine();
            }

            reader.close();
        } catch (Exception e)
        {
            e.printStackTrace();
        }

        return keyValidated;
    }
}
