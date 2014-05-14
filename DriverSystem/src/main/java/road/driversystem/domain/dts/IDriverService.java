package road.driversystem.domain.dts;

import aidas.userservice.dto.UserDto;

/**
 * Created by geh on 24-4-14.
 */
public interface IDriverService
{
    public UserDto login(String username, String password);
}
