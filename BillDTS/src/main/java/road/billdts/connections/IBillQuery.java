package road.billdts.connections;

import aidas.userservice.dto.UserDto;
import road.movemententities.entities.City;

import java.util.Date;
import java.util.List;

/**
 * Created by geh on 11-4-14.
 * This interface specifies all the possible queries that can be done from the BillSystem.
 */
public interface IBillQuery
{
    UserDto authenticate(String user, String password);

    boolean adjustKilometerRate(City city, Date addDate, String price);

    List<City> getCities();

    Integer generateMonthlyInvoices(Integer month, Integer year);
}
