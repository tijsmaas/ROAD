package road.movementdtos.dtos;

import java.util.Date;

/**
 * Created by Mitch on 21-5-2014.
 */
public class VehicleMovementDto {
    /**
     * The identifier of the vehicle movement.
     */
    private int id;

    /**
     * The position of the vehicle movement.
     */
    private float position;

    /**
     * The latitude of the vehicle movement.
     */
    private double latitude;

    /**
     * The longitude of the vehicle movement.
     */
    private double longitude;

    /**
     * The speed of the vehicle movement.
     */
    private float speed;

    /**
     * The moment of the movement.
     */
    private Date movementDateTime;

    /**
     * The index of the movement lane.
     */
    private int index;

    /**
     * The length of the movement lane.
     */
    private float length;

    /**
     * The type of the movement lane edge.
     */
    private String type;

    /**
     * The from city of the movement lane edge.
     */
    private CityDto from;

    /**
     * The to city of the movement lane edge.
     */
    private CityDto to;

    /**
     * The id of the movement lane.
     */
    private String lane_id;

    /**
     * The priority of the movement lane edge.
     */
    private Integer priority;

    /**
     * Creates a new instance of the {@link VehicleMovementDto} class.
     */
    public VehicleMovementDto() {
    }

    /**
     * Creates a new instance of the {@link VehicleMovementDto} class.
     * @param id The identifier of the vehicle movement.
     * @param position The position of the vehicle movement.
     * @param speed The speed of the vehicle movement.
     * @param latitude The latitude of the vehicle movement.
     * @param longitude The longitude of the vehicle movement.
     * @param movementDateTime The moment of the movement.
     * @param index The index of the movement lane.
     * @param length The length of the movement lane.
     * @param type The type of the movement lane edge.
     * @param from The from city of the movement lane edge.
     * @param to The to city of the movement lane edge.
     * @param priority The priority of the movement lane edge.
     */
    public VehicleMovementDto(int id, float position, float speed, double latitude, double longitude, Date movementDateTime, int index, float length, String type, CityDto from, CityDto to, Integer priority) {
        this.id = id;
        this.position = position;
        this.latitude = latitude;
        this.longitude = longitude;
        this.speed = speed;
        this.movementDateTime = movementDateTime;
        this.index = index;
        this.length = length;
        this.type = type;
        this.from = from;
        this.to = to;
        this.priority = priority;
        this.lane_id = "";
    }

    /**
     * Creates a new instance of the {@link VehicleMovementDto} class.
     * @param id The identifier of the vehicle movement.
     * @param position The position of the vehicle movement.
     * @param latitude The latitude of the vehicle movement.
     * @param longitude The longitude of the vehicle movement.
     * @param speed The speed of the vehicle movement.
     * @param movementDateTime The moment of the movement.
     * @param index The index of the movement lane.
     * @param length The length of the movement lane.
     * @param type The type of the movement lane edge.
     * @param from The from city of the movement lane edge.
     * @param to The to city of the movement lane edge.
     * @param priority The priority of the movement lane edge.
     * @param lane_id The id of the movement lane.
     */
    public VehicleMovementDto(int id, float position, double latitude, double longitude, float speed, Date movementDateTime, int index, float length, String type, CityDto from, CityDto to, Integer priority, String lane_id)
    {
        this.id = id;
        this.position = position;
        this.latitude = latitude;
        this.longitude = longitude;
        this.speed = speed;
        this.movementDateTime = movementDateTime;
        this.index = index;
        this.length = length;
        this.type = type;
        this.from = from;
        this.to = to;
        this.priority = priority;
        this.lane_id = lane_id;
    }


    /**
     * Get the {@link #id} of the {@link VehicleMovementDto}.
     * @return The identifier of the vehicle movement.
     */
    public int getId() {
        return id;
    }

    /**
     * Get the {@link #movementDateTime} of the {@link VehicleMovementDto}.
     * @return The moment of the movement.
     */
    public Date getMovementDateTime() {
        return movementDateTime;
    }

    /**
     * Get the {@link #type} of the {@link VehicleMovementDto}.
     * @return The type of the movement lane edge.
     */
    public String getType() {
        return type;
    }

    /**
     * Get the {@link #from} of the {@link VehicleMovementDto}.
     * @return The from city of the movement lane edge.
     */
    public CityDto getFrom() {
        return from;
    }

    /**
     * Get the {@link #to} of the {@link VehicleMovementDto}.
     * @return The to city of the movement lane edge.
     */
    public CityDto getTo() {
        return to;
    }

    /**
     * Get the {@link #latitude} of the {@link VehicleMovementDto}.
     * @return The latitude of the vehicle movement.
     */
    public double getLatitude()
    {
        return latitude;
    }

    /**
     * Get the {@link #longitude} of the {@link VehicleMovementDto}.
     * @return The longitude of the vehicle movement.
     */
    public double getLongitude()
    {
        return longitude;
    }

    /**
     * Get the {@link #priority} of the {@link VehicleMovementDto}.
     * @return The priority of the movement lane edge.
     */
    public Integer getPriority() {
        return priority;
    }

    /**
     *  Get the {@link #speed} of the {@link VehicleMovementDto}.
     * @return The speed of the vehicle movement.
     */
    public float getSpeed() {
        return speed;
    }

    /**
     * Get the {@link #length} of the {@link VehicleMovementDto}.
     * @return The length of the movement lane.
     */
    public float getLength() {
        return length;
    }

    /**
     * Get the {@link #lane_id} of the {@link VehicleMovementDto}.
     * @return The id of the movement lane.
     */
    public String getLane_id() {
        return lane_id;
    }

    /**
     * Set the {@link #id} of the {@link VehicleMovementDto}.
     * @param id The identifier of the vehicle movement.
     */
    public void setId(int id) {
        this.id = id;
    }

    /**
     * Set the {@link #position} of the {@link VehicleMovementDto}.
     * @param position The position of the vehicle movement.
     */
    public void setPosition(float position) {
        this.position = position;
    }

    /**
     * Set the {@link #latitude} of the {@link VehicleMovementDto}.
     * @param latitude The latitude of the vehicle movement.
     */
    public void setLatitude(double latitude) {
        this.latitude = latitude;
    }

    /**
     * Set the {@link #longitude} of the {@link VehicleMovementDto}.
     * @param longitude The longitude of the vehicle movement.
     */
    public void setLongitude(double longitude) {
        this.longitude = longitude;
    }

    /**
     * Set the {@link #speed} of the {@link VehicleMovementDto}.
     * @param speed The speed of the vehicle movement.
     */
    public void setSpeed(float speed) {
        this.speed = speed;
    }

    /**
     * Set the {@link #movementDateTime} of the {@link VehicleMovementDto}.
     * @param movementDateTime The moment of the movement.
     */
    public void setMovementDateTime(Date movementDateTime) {
        this.movementDateTime = movementDateTime;
    }

    /**
     * Set the {@link #index} of the {@link VehicleMovementDto}.
     * @param index The index of the movement lane.
     */
    public void setIndex(int index) {
        this.index = index;
    }

    /**
     * Set the {@link #length} of the {@link VehicleMovementDto}.
     * @param length The length of the movement lane.
     */
    public void setLength(float length) {
        this.length = length;
    }

    /**
     * Set the {@link #type} of the {@link VehicleMovementDto}.
     * @param type The type of the movement lane edge.
     */
    public void setType(String type) {
        this.type = type;
    }

    /**
     * Set the {@link #from} of the {@link VehicleMovementDto}.
     * @param from The from city of the movement lane edge.
     */
    public void setFrom(CityDto from) {
        this.from = from;
    }

    /**
     * Set the {@link #to} of the {@link VehicleMovementDto}.
     * @param to The to city of the movement lane edge.
     */
    public void setTo(CityDto to) {
        this.to = to;
    }

    /**
     * Set the {@link #lane_id} of the {@link VehicleMovementDto}.
     * @param lane_id The id of the movement lane.
     */
    public void setLane_id(String lane_id) {
        this.lane_id = lane_id;
    }

    /**
     * Set the {@link #priority} of the {@link VehicleMovementDto}.
     * @param priority The priority of the movement lane edge.
     */
    public void setPriority(Integer priority) {
        this.priority = priority;
    }
}
