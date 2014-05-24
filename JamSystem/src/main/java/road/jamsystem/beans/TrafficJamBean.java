package road.jamsystem.beans;

import road.movementdtos.dtos.LaneDto;

import javax.ejb.Stateless;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Geert on 24/05/2014.
 */
@Stateless
public class TrafficJamBean
{
    /**
     * A collection containing all lanes with a traffic jam on them.
     */
    private List<LaneDto> lanesWithJam;

    /**
     * Create a new instance of the {@link TrafficJamBean} bean.
     */
    public TrafficJamBean()
    {
        this.lanesWithJam = new ArrayList();
    }

    public List<LaneDto> getLanesWithJam()
    {
        return this.lanesWithJam;
    }

    public void setLanesWithJam(List<LaneDto> lanesWithJam)
    {
        this.lanesWithJam = lanesWithJam;
    }
}
