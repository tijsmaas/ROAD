package road.carsystem.api;

import org.primefaces.model.UploadedFile;

import javax.ejb.*;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.inject.Inject;
import javax.inject.Named;
import javax.websocket.Session;
import java.io.Serializable;

/**
 * Created by geh on 13-5-14.
 * This bean allows the JSF page to upload the necessary files for simulation
 */
@Named("carBean") @Singleton
public class CarBean implements Serializable
{
    @Inject
    private CarSimulator simulator;
    private UploadedFile file;

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

    /**
     * This starts the simulation. Should only be called after this.file has been set.
     * @param session The websocket session that initiated this simulation
     */
    public void start(Session session)
    {
        this.simulator.runSimulation(session, this.file);
    }
}
