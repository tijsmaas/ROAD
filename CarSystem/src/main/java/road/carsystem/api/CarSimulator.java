/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package road.carsystem.api;

import road.cardts.connections.CarClient;

import javax.annotation.PostConstruct;
import javax.ejb.Stateless;
import javax.enterprise.context.ApplicationScoped;
import javax.ws.rs.*;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.UriInfo;

/**
 * REST Web Service
 *
 * @author tijs
 */
@ApplicationScoped
public class CarSimulator
{
    @Context
    private UriInfo context;
    private CarClient carClient;

    /**
     * Creates a new instance of GenericResource
     */
    public CarSimulator()
    {

    }

    @PostConstruct
    private void init()
    {
        this.carClient = new CarClient();
        this.carClient.start();
    }

    /**
     * Retrieves representation of an instance of movementParser.GenericResource
     *
     * @return an instance of java.lang.String
     */
    @GET @Produces({MediaType.APPLICATION_XML})
    public String getXml()
    {
        return "<?xml version=\"1.0\" encoding=\"UTF-8\"?>"
                    + "<response status=\"error\" cause=\"No movements have been sent\"/>"
                    + "</xml>";
    }

    /**
     * POST method for adding movements
     *
     * @param api_key The api key, as a header parameter (autorisatiecode)
     * @param sequenceNumber The sequence number of the movements file
     * @param movements The xml movements
     * @return an HTTP response according to the PTS-SEI study guide.
     */
    @POST @Produces({MediaType.APPLICATION_XML}) @Consumes({MediaType.APPLICATION_XML})
    public String putXml(@HeaderParam("api_key") String api_key, @HeaderParam("serial_number") int sequenceNumber, String movements)
    {
        if(this.carClient.addMovement(api_key, sequenceNumber, movements))
        {
            System.out.println("API KEY ACCEPTED");
            return "<?xml version=\"1.0\" encoding=\"UTF-8\"?>"
                    + "<response status=\"ok\" VEHICLE_ID=\"2\"/>\n"
                    + "</xml>";
        }
        else
        {
            System.out.println("API Key not valid");
            return "<?xml version=\"1.0\" encoding=\"UTF-8\"?>"
                    + "<response status=\"error\" cause=\"" + new NotAuthorizedException("API Key not valid").getLocalizedMessage() + "\"/>"
                    + "</xml>";
        }
    }
}
