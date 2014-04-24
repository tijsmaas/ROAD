package connections;

import aidas.userservice.dto.UserDto;

/**
 * Created by geh on 22-4-14.
 */
public interface IDriverQuery
{
    UserDto authenticate(String userId, String password);

    Long getLaneCount();

    Long getEdgeCount();
}
