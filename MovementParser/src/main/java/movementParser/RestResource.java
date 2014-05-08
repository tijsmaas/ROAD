/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package movementParser;

import javax.ejb.Stateless;
import javax.inject.Inject;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.UriInfo;
import javax.ws.rs.Consumes;
import javax.ws.rs.Path;
import javax.ws.rs.GET;
import javax.ws.rs.HeaderParam;
import javax.ws.rs.NotAuthorizedException;
import javax.ws.rs.POST;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

/**
 * REST Web Service
 *
 * @author tijs
 */
@Path("/updateMovements")
@Stateless
public class RestResource {

    @Context
    private UriInfo context;

    @Inject
    private Parser parser;
    
    @Inject
    private Authentication auth;

    /**
     * Creates a new instance of GenericResource
     */
    public RestResource() {
    }

    /**
     * Retrieves representation of an instance of movementParser.GenericResource
     *
     * @return an instance of java.lang.String
     */
    @GET
    @Produces({MediaType.APPLICATION_XML})
    public String getXml() {
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
    @POST
    @Produces({MediaType.APPLICATION_XML})
    @Consumes({MediaType.APPLICATION_XML})
    public String putXml(
            @HeaderParam("api_key") String api_key, 
            @HeaderParam("serial_number") int sequenceNumber, 
            String movements) {
        try {
            System.out.println("CALLING XML");
            if (auth.checkApiKey(api_key)) {
                System.out.println("API KEY ACCEPTED");
            }else{
                throw new NotAuthorizedException("API Key not valid");
            }
            
            parser.parseChanges(movements, sequenceNumber);

            return "<?xml version=\"1.0\" encoding=\"UTF-8\"?>"
                    + "<response status=\"ok\" VEHICLE_ID=\"2\"/>\n"
                    + "</xml>";
        } catch (Exception e) {
            e.printStackTrace();
            return "<?xml version=\"1.0\" encoding=\"UTF-8\"?>"
                    + "<response status=\"error\" cause=\"" + e.getLocalizedMessage() + "\"/>"
                    + "</xml>";
        }
    }
}
