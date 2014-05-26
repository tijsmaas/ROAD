package road.movementdtos.dtos;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * Created by Geert on 21/05/2014.
 */
public class LaneDto
{
    /**
     * The identifier of the lane.
     */
    private String id;

    /**
     * The length of the lane.
     */
    private float length;

    /**
     * The from lane identifiers.
     */
    private List<String> laneIdsFrom;

    /**
     * The to lane identifiers.
     */
    private List<String> laneIdsTo;

    /**
     * The shape of the lane.
     */
    private Map<Integer, CoordinateDto> shape;

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
    public LaneDto(String id, float length, List<String> laneIdsFrom, List<String> laneIdsTo, Map<Integer, CoordinateDto> shape)
    {
        this(id, length);
        this.laneIdsFrom = laneIdsFrom;
        this.laneIdsTo = laneIdsTo;
        this.shape = shape;
    }

    /**
     * Get the {@link #id} of the {@link LaneDto}.
     * @return The identifier of the lane.
     */
    public String getId()
    {
        return id;
    }

    /**
     * Set the {@link #id} of the {@link LaneDto}.
     * @param id The identifier of the lane.
     */
    public void setId(String id)
    {
        this.id = id;
    }

    /**
     * Get the {@link #length} of the {@link LaneDto}.
     * @return The length of the lane.
     */
    public float getLength()
    {
        return length;
    }

    /**
     * Set the {@link #length} of the {@link LaneDto}.
     * @param length The length of the lane.
     */
    public void setLength(float length)
    {
        this.length = length;
    }

    /**
     * Get the {@link #laneIdsFrom} of the {@link LaneDto}.
     * @return The from lane identifiers.
     */
    public List<String> getLaneIdsFrom()
    {
        return laneIdsFrom;
    }

    /**
     * Set the {@link #laneIdsFrom} of the {@link LaneDto}.
     * @param laneIdsFrom The from lane identifiers.
     */
    public void setLaneIdsFrom(List<String> laneIdsFrom)
    {
        this.laneIdsFrom = laneIdsFrom;
    }

    /**
     * Get the {@link #laneIdsTo} of the {@link LaneDto}.
     * @return The to lane identifiers.
     */
    public List<String> getLaneIdsTo()
    {
        return laneIdsTo;
    }

    /**
     * Set the {@link #laneIdsTo} of the {@link LaneDto}.
     * @param laneIdsTo The to lane identifiers.
     */
    public void setLaneIdsTo(List<String> laneIdsTo)
    {
        this.laneIdsTo = laneIdsTo;
    }

    /**
     * Set the {@link #shape} of the {@link LaneDto}.
     * @param shape The shape of the lane.
     */
    public void setShape(Map<Integer, CoordinateDto> shape) {
        this.shape = shape;
    }
}
