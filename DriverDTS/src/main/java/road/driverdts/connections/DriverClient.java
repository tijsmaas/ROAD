package road.driverdts.connections;

import aidas.userservice.dto.UserDto;
import road.movementdtos.dtos.VehicleDto;
import road.movementdts.connections.ClientConnection;
import road.movementdts.connections.MovementConnection;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by geh on 22-4-14.
 */
public class DriverClient extends ClientConnection implements IDriverQuery
{
    public DriverClient()
    {
        super(MovementConnection.ServerAddress, MovementConnection.FactoryName, MovementConnection.DriverSystemQueue);
    }

    @Override
    public UserDto authenticate(String userId, String password)
    {
        return this.remoteCall("authenticate", UserDto.class, userId, password);
    }

    @Override
    public Long getLaneCount()
    {
        return this.remoteCall("getLaneCount", Long.class);
    }

    @Override
    public Long getEdgeCount()
    {
        return this.remoteCall("getEdgeCount", Long.class);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public List<VehicleDto> getVehicles(Integer userId) {
        return this.remoteCall("getVehicles", ArrayList.class, userId);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Boolean updateVehicle(VehicleDto vehicleDto) {
        return this.remoteCall("updateVehicle", Boolean.class, vehicleDto);
    }
}
