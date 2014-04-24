package connections;

import aidas.usersystem.dto.UserDto;

/**
 * Created by geh on 11-4-14.
 * This interface specifies all the possible queries that can be done from the BillSystem.
 */
public interface IBillQuery
{
    UserDto authenticate(String userId, String password);
}
