package road.billdts.connections;

import aidas.userservice.dto.UserDto;

/**
 * Created by geh on 11-4-14.
 * This interface specifies all the possible queries that can be done from the BillSystem.
 */
public interface IBillQuery
{
    UserDto authenticate(String user, String password);

    Integer generateMonthlyInvoices(Integer month, Integer year);
}
