package road.movementservice.helpers;

import road.movementdtos.dtos.MovementUserDto;
import road.movemententities.entities.MovementUser;
import road.movemententityaccess.dao.LoginDAO;
import road.movementservice.mapper.DtoMapper;
import road.userservice.UserDAO;
import road.userservice.dto.UserDto;

import java.sql.DataTruncation;

/**
 * Created by geh on 21-5-14.
 */
public class DAOHelper
{
    public static MovementUserDto authenticate(UserDAO userDAO, LoginDAO loginDAO, DtoMapper dtoMapper, String userName, String password)
    {
        UserDto userDto = userDAO.login(userName, password);
        if(userDto != null)
        {
            MovementUser mUser = loginDAO.getUser(userDto.getUsername());
            if(mUser == null)
            {
                mUser = loginDAO.register(userDto.getUsername(), userDto.getEmail());
            }
            return dtoMapper.toMovementUserDto(mUser);
        }
        else
        {
            return null;
        }
    }
}
