package road.billdts.connections;

import road.userservice.dto.UserDto;
import road.movementdtos.dtos.CityDto;

import java.util.Date;
import java.util.List;

/**
 * Created by geh on 11-4-14.
 * This interface specifies all the possible queries that can be done from the BillSystem.
 */
public interface IBillQuery
{
    UserDto authenticate(String user, String password);

    boolean adjustKilometerRate(CityDto city, Date addDate, String price);

    List<CityDto> getCities();

    Integer generateMonthlyInvoices(Integer month, Integer year);
}
