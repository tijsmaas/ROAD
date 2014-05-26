package road.movementmapper.beans;

import org.primefaces.model.UploadedFile;
import org.xml.sax.SAXException;
import road.movementmapper.MovementMapper;
import road.movementparser.injectable.MovementParser;

import javax.batch.operations.JobOperator;
import javax.batch.runtime.BatchRuntime;
import javax.batch.runtime.JobExecution;
import javax.ejb.Singleton;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.inject.Inject;
import javax.inject.Named;
import java.io.Serializable;
import java.util.Properties;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * Created by geh on 25-5-14.
 */
@Named("mapBean") @Singleton
public class MapBean implements Serializable
{
    private boolean mappingDone = false;
    public static UploadedFile movements;
    private static final String JOBNAME = "PayrollJob";

    @Inject
    private MovementMapper mapParser;

    @Inject
    private MovementParser movementParser;

    private UploadedFile osmMapFile;
    private UploadedFile sumoMapFile;
    private UploadedFile movementsFile;

    public void upload()
    {
        if(sumoMapFile != null && osmMapFile != null && movementsFile != null)
        {
            FacesMessage msg = new FacesMessage("Successful", sumoMapFile.getFileName() + " is uploaded.");
            FacesContext.getCurrentInstance().addMessage(null, msg);
        }
        else
        {
            String missing = "";
            if(osmMapFile == null) missing += "OSM Map ";
            if(sumoMapFile == null) missing += "SUMO Map ";
            if(movementsFile == null) missing += "Movements ";

            FacesMessage msg = new FacesMessage("Failed, missing [" + missing + "]");
            FacesContext.getCurrentInstance().addMessage(null, msg);
        }
    }

    public void startMapParse()
    {
        try
        {
            mapParser.parseSUMO(sumoMapFile);
            mapParser.parseOSM(osmMapFile);
            System.out.println("[PARSERSERVICE] Finished parsing initial map");

            MapBean.movements = this.movementsFile;
            this.mappingDone = true;
        }
        catch (SAXException ex)
        {
            Logger.getLogger(this.getClass().getName()).log(Level.SEVERE, null, ex);
        }
    }

    public void startBatchJob()
    {
        try
        {
            JobOperator jobOperator = BatchRuntime.getJobOperator();

            for (String job : jobOperator.getJobNames())
            {
                System.out.println("EXISTING JOB: " + job);
            }

            Properties props = new Properties();
            props.setProperty("basedate", "11-02-2014|11:11:11");

            System.out.println("Starting batch via servlet");
            long executionID = jobOperator.start(JOBNAME, props);

            Thread.sleep(300);

            System.out.println("Job with ID " + executionID + " started");
            JobExecution jobExec = jobOperator.getJobExecution(executionID);
            String status = jobExec.getBatchStatus().toString();
            System.out.println("Job status: " + status);
        }
        catch (Exception ex)
        {
            ex.printStackTrace();
        }
    }

    public UploadedFile getOsmMapFile()
    {
        return osmMapFile;
    }

    public void setOsmMapFile(UploadedFile osmMapFile)
    {
        this.osmMapFile = osmMapFile;
    }

    public UploadedFile getSumoMapFile()
    {
        return sumoMapFile;
    }

    public void setSumoMapFile(UploadedFile sumoMapFile)
    {
        this.sumoMapFile = sumoMapFile;
    }

    public UploadedFile getMovementsFile()
    {
        return movementsFile;
    }

    public void setMovementsFile(UploadedFile movementsFile)
    {
        this.movementsFile = movementsFile;
    }

    public boolean isMappingDone()
    {
        return mappingDone;
    }

    public void setMappingDone(boolean mappingDone)
    {
        this.mappingDone = mappingDone;
    }
}
