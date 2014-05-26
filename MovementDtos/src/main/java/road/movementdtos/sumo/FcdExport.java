package road.movementdtos.sumo;

import com.thoughtworks.xstream.annotations.XStreamAlias;
import com.thoughtworks.xstream.annotations.XStreamImplicit;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by geh on 25-5-14.
 */
@XStreamAlias("fcd-export")
public class FcdExport
{
    @XStreamImplicit(itemFieldName="timestep")
    private List<FcdTimeStep> timeSteps;

    public FcdExport()
    {
        this.timeSteps = new ArrayList();
    }

    public FcdExport(List<FcdTimeStep> timeSteps)
    {
        this.timeSteps = timeSteps;
    }

    public FcdExport(FcdTimeStep timeStep)
    {
        this.timeSteps = new ArrayList<FcdTimeStep>();
        this.timeSteps.add(timeStep);
    }

    public List<FcdTimeStep> getTimeSteps()
    {
        return timeSteps;
    }

    public void setTimeSteps(List<FcdTimeStep> timeSteps)
    {
        this.timeSteps = timeSteps;
    }
}
