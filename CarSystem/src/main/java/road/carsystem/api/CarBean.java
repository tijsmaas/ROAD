package road.carsystem.api;

import com.thoughtworks.xstream.XStream;
import org.primefaces.model.UploadedFile;
import road.carsystem.domain.Netstate;
import sumo.movements.jaxb.TimestepType;

import javax.annotation.Resource;
import javax.ejb.*;
import javax.faces.application.FacesMessage;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;
import javax.inject.Inject;
import javax.inject.Named;
import javax.websocket.Session;
import java.io.Serializable;
import java.util.List;

/**
 * Created by geh on 13-5-14.
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

    public void start(Session session)
    {
        this.simulator.runSimulation(session, this.file);
    }
}
