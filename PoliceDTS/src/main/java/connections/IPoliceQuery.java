package connections;

import aidas.usersystem.dto.UserDto;

/**
 * Created by geh on 23-4-14.
 */
public interface IPoliceQuery
{
    UserDto authenticate(String userId, String password);
}
