package road.movementdtos.dtos;

import java.util.Date;

/**
 * Created by Mitch on 21-5-2014.
 */
public class VehicleMovementDto {
    private int id;
    private float position;
    private float speed;
    private Date movementDateTime;
    private int index;
    private float length;
    private String type;
    private CityDto from;
    private CityDto to;
    private Integer priority;

    public VehicleMovementDto() {
    }

    public VehicleMovementDto(int id, float position, float speed, Date movementDateTime, int index, float length, String type, CityDto from, CityDto to, Integer priority) {
        this.id = id;
        this.position = position;
        this.speed = speed;
        this.movementDateTime = movementDateTime;
        this.index = index;
        this.length = length;
        this.type = type;
        this.from = from;
        this.to = to;
        this.priority = priority;
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

    public Integer getPriority() {
        return priority;
    }

    public float getSpeed() {
        return speed;
    }

    public float getLength() {
        return length;
    }
}
