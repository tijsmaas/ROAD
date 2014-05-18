/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package road.carsystem.api;

import com.thoughtworks.xstream.XStream;
import org.primefaces.model.UploadedFile;
import road.cardts.connections.CarClient;
import road.carsystem.domain.*;
import sumo.movements.jaxb.EdgeType;
import sumo.movements.jaxb.LaneType;
import sumo.movements.jaxb.TimestepType;
import sumo.movements.jaxb.VehicleType;

import javax.annotation.PostConstruct;
import javax.annotation.Resource;
import javax.ejb.Singleton;
import javax.ejb.Timeout;
import javax.ejb.TimerService;
import javax.faces.bean.ApplicationScoped;
import javax.websocket.Session;
import java.io.Serializable;
import java.util.List;

/**
 * REST Web Service
 *
 * @author tijs
 */
@Singleton
public class CarSimulator implements Serializable
{
    @Resource
    private TimerService timerService;
    private XStream xStream;

    private CarClient carClient;
    private Session session;
    private List<TimeStep> timeSteps;
    private int sequence = 0;

    public CarSimulator()
    {

    }

    @PostConstruct
    private void init()
    {
        this.xStream = new XStream();
        this.xStream.setMode(XStream.NO_REFERENCES);
        this.xStream.alias("response", Response.class);
        this.xStream.useAttributeFor(Response.class, "status");
        this.xStream.useAttributeFor(Response.class, "VEHICLE_ID");
        this.xStream.useAttributeFor(Response.class, "cause");
        this.xStream.alias("sumo-netstate", Netstate.class);
        this.xStream.alias("timestep", TimeStep.class);
        this.xStream.useAttributeFor(TimeStep.class, "time");
        this.xStream.alias("edge", Edge.class);
        this.xStream.useAttributeFor(Edge.class, "id");
        this.xStream.alias("lane", Lane.class);
        this.xStream.useAttributeFor(Lane.class, "id");
        this.xStream.alias("vehicle", Vehicle.class);
        this.xStream.useAttributeFor(Vehicle.class, "id");
        this.xStream.useAttributeFor(Vehicle.class, "pos");
        this.xStream.useAttributeFor(Vehicle.class, "speed");
        this.xStream.addImplicitCollection(Netstate.class, "timeSteps");
        this.xStream.addImplicitCollection(TimeStep.class, "edges");
        this.xStream.addImplicitCollection(Edge.class, "lanes");
        this.xStream.addImplicitCollection(Lane.class, "vehicles");

        this.carClient = new CarClient();
        this.carClient.start();
    }

    public void runSimulation(Session session, UploadedFile file)
    {
        try
        {
            this.session = session;
            Netstate state = ((Netstate)xStream.fromXML(file.getInputstream()));
            this.timeSteps = state.timeSteps;
            this.setTimer(0);
        }
        catch(Exception ex)
        {
            ex.printStackTrace();
        }
    }

    private void setTimer(int wait)
    {
        this.timerService.createTimer(wait, null);
    }

    @Timeout
    private void sendTimeStep()
    {
        TimeStep type = this.timeSteps.get(this.sequence);

        if(this.putXml(CarSocket.apiKey, this.sequence, this.xStream.toXML(new Netstate(type))))
        {
            this.session.getAsyncRemote().sendText(
                    "Successfully sent movement. Sequence number " + this.sequence + " of " + this.timeSteps.size()
            );
        }
        else
        {
            this.session.getAsyncRemote().sendText(
                    "Failed to send movement! Sequence number " + this.sequence + " of " + this.timeSteps.size()
            );
        }

        if(this.sequence + 1 < this.timeSteps.size())
        {
            this.setTimer((int)(this.timeSteps.get(sequence + 1).time - type.time));
            this.sequence++;
        }
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
        String result = this.carClient.addMovement(api_key, (long)sequenceNumber, movements);
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
