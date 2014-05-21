package road.policedts.connections;

import road.movementdtos.dtos.MovementUserDto;
import road.userservice.dto.UserDto;

/**
 * Created by geh on 23-4-14.
 */
public interface IPoliceQuery
{
    MovementUserDto authenticate(String userId, String password);
}
