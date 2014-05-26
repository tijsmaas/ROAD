package road.movementdtos.dtos;

import java.util.List;

/**
 * Created by Geert on 21/05/2014.
 */
public class MovementDto
{
    /**
     * The lane on which the {@link #vehicleMovements} are currently driving.
     */
    private LaneDto lane;

    /**
     * The vehicles currently driving on the {@link #lane}.
     */
    private List<VehicleMovementDto> vehicleMovements;

    /**
     * No-args constructor for (de)serialization
     */
    public MovementDto()
    {

    }

    /**
     * Create a new instance of the {@link MovementDto}.
     */
    public MovementDto(LaneDto lane, List<VehicleMovementDto> vehicleMovements)
    {
        this.vehicleMovements = vehicleMovements;
        this.lane = lane;
    }

    /**
     * Get the {@link #vehicleMovements} of the {@link MovementDto}.
     * @return The vehicles currently driving on the {@link #lane}.
     */
    public List<VehicleMovementDto> getVehicleMovements()
    {
        return vehicleMovements;
    }

    /**
     * Set the {@link #vehicleMovements} of the {@link MovementDto}.
     * @param vehicleMovements The vehicles currently driving on the {@link #lane}.
     */
    public void setVehicleMovements(List<VehicleMovementDto> vehicleMovements)
    {
        this.vehicleMovements = vehicleMovements;
    }

    /**
     * Get the {@link #lane} of the {@link MovementDto}.
     * @return The lane on which the {@link #vehicleMovements} are currently driving.
     */
    public LaneDto getLane()
    {
        return lane;
    }

    /**
     * Set the {@link #lane} of the {@link MovementDto}.
     * @param lane The lane on which the {@link #vehicleMovements} are currently driving.
     */
    public void setLane(LaneDto lane)
    {
        this.lane = lane;
    }
}
