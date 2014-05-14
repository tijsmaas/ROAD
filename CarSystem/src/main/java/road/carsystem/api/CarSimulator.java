/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package road.carsystem.api;

import com.thoughtworks.xstream.XStream;
import road.cardts.connections.CarClient;
import road.carsystem.domain.Response;

import javax.annotation.PostConstruct;
import javax.ejb.Stateless;
import javax.enterprise.context.ApplicationScoped;
import javax.ws.rs.*;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.UriInfo;
import javax.xml.bind.JAXBContext;
import java.io.Serializable;

/**
 * REST Web Service
 *
 * @author tijs
 */
@ApplicationScoped
public class CarSimulator implements Serializable
{
    private CarClient carClient;
    private XStream xStream;

    public CarSimulator()
    {

    }

    @PostConstruct
    private void init()
    {
        this.xStream = new XStream();
        this.xStream.alias("response", Response.class);

        this.carClient = new CarClient();
        this.carClient.start();
    }

    /**
     * method for adding movements
     *
     * @param api_key The api key, as a header parameter (autorisatiecode)
     * @param sequenceNumber The sequence number of the movements file
     * @param movements The xml movements
     * @return a boolean to notify of the success or failure of the operation
     */
    public boolean putXml(String api_key, int sequenceNumber, String movements)
    {
        String result = this.carClient.addMovement(api_key, sequenceNumber, movements);
        Response response = (Response)this.xStream.fromXML(result);

        switch(response.status)
        {
            case "ok":
                System.out.println("Successfully added movement for vehicle with id " + response.VEHICLE_ID);
                return true;
            case "error":
                System.out.println("API Key not valid");
                return false;
            default:
                System.out.println("Unrecognizable response status");
                return false;
        }
    }
}
