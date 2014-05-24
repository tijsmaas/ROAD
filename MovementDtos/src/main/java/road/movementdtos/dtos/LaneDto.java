package road.movementdtos.dtos;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Geert on 21/05/2014.
 */
public class LaneDto
{
    private String id;
    private float length;
    private List<String> laneIdsFrom;
    private List<String> laneIdsTo;

    /**
     * Create a new instance of {@link LaneDto}.
     */
    public LaneDto()
    {
        this.laneIdsFrom = new ArrayList();
        this.laneIdsTo = new ArrayList();
    }

    /**
     * Create a new instance of {@link LaneDto}.
     * @param id the identifier of the lane.
     * @param length the length of the lane.
     */
    public LaneDto(String id, float length)
    {
        this();
        this.id = id;
        this.length = length;
    }

    /**
     * Create a new instance of {@link LaneDto}.
     * @param id the identifier of the lane.
     * @param length the length of the lane.
     * @param laneIdsFrom the lanes leading to this lane.
     * @param laneIdsTo the lanes leading from this lane.
     */
    public LaneDto(String id, float length, List<String> laneIdsFrom, List<String> laneIdsTo)
    {
        this(id, length);
        this.laneIdsFrom = laneIdsFrom;
        this.laneIdsTo = laneIdsTo;
    }

    public String getId()
    {
        return id;
    }

    public void setId(String id)
    {
        this.id = id;
    }

    public float getLength()
    {
        return length;
    }

    public void setLength(float length)
    {
        this.length = length;
    }

    public List<String> getLaneIdsFrom()
    {
        return laneIdsFrom;
    }

    public void setLaneIdsFrom(List<String> laneIdsFrom)
    {
        this.laneIdsFrom = laneIdsFrom;
    }

    public List<String> getLaneIdsTo()
    {
        return laneIdsTo;
    }

    public void setLaneIdsTo(List<String> laneIdsTo)
    {
        this.laneIdsTo = laneIdsTo;
    }
}
