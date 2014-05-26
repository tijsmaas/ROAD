package road.movementdtos.dtos;

import java.util.Date;

/**
 * Created by Mitch on 21-5-2014.
 */
public class VehicleMovementDto {
    private int id;
    private float position;
    private double latitude;
    private double longitude;
    private float speed;
    private Date movementDateTime;
    private int index;
    private float length;
    private String type;
    private CityDto from;
    private CityDto to;
    private String lane_id;
    private Integer priority;

    public VehicleMovementDto() {
    }

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



    public int getId() {
        return id;
    }

    public Date getMovementDateTime() {
        return movementDateTime;
    }

    public String getType() {
        return type;
    }

    public CityDto getFrom() {
        return from;
    }

    public CityDto getTo() {
        return to;
    }

    public double getLatitude()
    {
        return latitude;
    }

    public double getLongitude()
    {
        return longitude;
    }

    public Integer getPriority() {
        return priority;
    }

    public float getSpeed() {
        return speed;
    }

    public float getLength() {
        return length;
    }

    public String getLane_id() {
        return lane_id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setPosition(float position) {
        this.position = position;
    }

    public void setLatitude(double latitude) {
        this.latitude = latitude;
    }

    public void setLongitude(double longitude) {
        this.longitude = longitude;
    }

    public void setSpeed(float speed) {
        this.speed = speed;
    }

    public void setMovementDateTime(Date movementDateTime) {
        this.movementDateTime = movementDateTime;
    }

    public void setIndex(int index) {
        this.index = index;
    }

    public void setLength(float length) {
        this.length = length;
    }

    public void setType(String type) {
        this.type = type;
    }

    public void setFrom(CityDto from) {
        this.from = from;
    }

    public void setTo(CityDto to) {
        this.to = to;
    }

    public void setLane_id(String lane_id) {
        this.lane_id = lane_id;
    }

    public void setPriority(Integer priority) {
        this.priority = priority;
    }
}
