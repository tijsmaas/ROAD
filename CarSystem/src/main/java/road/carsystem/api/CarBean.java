package road.carsystem.api;

import com.thoughtworks.xstream.XStream;
import org.primefaces.model.UploadedFile;
import road.carsystem.domain.Netstate;
import sumo.movements.jaxb.TimestepType;

import javax.annotation.Resource;
import javax.ejb.Timeout;
import javax.ejb.Timer;
import javax.ejb.TimerService;
import javax.enterprise.context.SessionScoped;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.inject.Inject;
import javax.inject.Named;
import javax.websocket.Session;
import java.io.Serializable;
import java.util.List;

/**
 * Created by geh on 13-5-14.
 */
@SessionScoped @Named("carBean")
public class CarBean implements Serializable
{
    @Resource
    private TimerService timerService;
    @Inject
    private CarSimulator simulator;
    private UploadedFile file;
    private Session session;

    private XStream xStream;

    private List<TimestepType> timeSteps;
    private int sequence = 0;


    public UploadedFile getFile()
    {
        return file;
    }

    public void setFile(UploadedFile file)
    {
        this.file = file;
    }

    public void upload()
    {
        if(file != null)
        {
            FacesMessage msg = new FacesMessage("Successful", file.getFileName() + " is uploaded.");
            FacesContext.getCurrentInstance().addMessage(null, msg);
        }
    }

    public void runSimulation(Session session)
    {
        try
        {
            this.session = session;
            this.timeSteps = ((Netstate)xStream.fromXML(file.getInputstream())).timeSteps;
            this.setTimer(0);
        }
        catch(Exception ex)
        {
            ex.printStackTrace();
        }
    }

    @Timeout
    private void sendTimeStep()
    {
        TimestepType type = this.timeSteps.get(this.sequence);

        if(this.simulator.putXml(CarSocket.apiKey, this.sequence, this.xStream.toXML(type)))
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
            this.setTimer((int)(this.timeSteps.get(sequence + 1).getTime() - type.getTime()));
            this.sequence++;
        }
    }

    private void setTimer(int wait)
    {
        this.timerService.createTimer(wait, null);
    }
}
