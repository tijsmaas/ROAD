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
     * Create a new instance of the {@link MovementDto}.
     */
    public MovementDto(LaneDto lane, List<VehicleMovementDto> vehicleMovements)
    {
        this.vehicleMovements = vehicleMovements;
        this.lane = lane;
    }

    public List<VehicleMovementDto> getVehicleMovements()
    {
        return vehicleMovements;
    }

    public void setVehicleMovements(List<VehicleMovementDto> vehicleMovements)
    {
        this.vehicleMovements = vehicleMovements;
    }

    public LaneDto getLane()
    {
        return lane;
    }

    public void setLane(LaneDto lane)
    {
        this.lane = lane;
    }
}
