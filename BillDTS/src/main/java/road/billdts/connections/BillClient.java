package road.billdts.connections;

        import aidas.userservice.dto.UserDto;
        import road.movementdtos.dtos.CityDto;
        import road.movementdts.connections.ClientConnection;
        import road.movementdts.connections.MovementConnection;

        import java.util.ArrayList;
        import java.util.Date;
        import java.util.List;

/**
 * Created by geh on 11-4-14.
 * This is the connection that should be used by the BillSystem. One can call remotecall.
 * This class is NOT THREADSAFE. If you want multithreading, create on BillConnection for EACH
 * thread.
 */
public class BillClient extends ClientConnection implements IBillQuery
{
    public BillClient()
    {
        super("localhost:1099", MovementConnection.FactoryName, MovementConnection.BillSystemQueue);
    }

    @Override
    public UserDto authenticate(String user, String password)
    {
        return this.remoteCall("authenticate", UserDto.class, user, password);
    }

    @Override
    public boolean adjustKilometerRate(CityDto city, Date addDate, String price)
    {
        return this.remoteCall("adjustKilometerRate", boolean.class, city, addDate, price);
    }

    @Override
    public List<CityDto> getCities() {
        return this.remoteCall("getCities", ArrayList.class);
    }

    @Override
    public Integer generateMonthlyInvoices(Integer month, Integer year)
    {
        Object result = this.remoteCall("generateMonthlyInvoices", Integer.class, month, year);
        System.out.println("Remote call result = " + result);
        return (Integer)result;
    }
}
