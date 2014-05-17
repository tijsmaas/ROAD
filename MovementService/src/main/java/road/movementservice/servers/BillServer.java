package road.movementservice.servers;

//import aidas.usersystem.IUserManager;

import aidas.userservice.IUserManager;
import aidas.userservice.dto.UserDto;
import road.billdts.connections.IBillQuery;
import road.movementdts.connections.MovementConnection;
import road.movementdts.helpers.DateHelper;
import road.movementdts.helpers.Pair;
import road.movemententities.entities.VehicleMovement;
import road.movemententityaccess.dao.CityDAO;
import road.movemententityaccess.dao.InvoiceDAO;
import road.movemententityaccess.dao.MovementDAO;
import road.movemententities.entities.City;
import road.movemententities.entities.CityRate;
import road.movementservice.connections.ServerConnection;

import java.util.*;

import java.util.List;

/**
 * Created by geh on 11-4-14.
 */
public class BillServer extends ServerConnection implements IBillQuery
{
    private InvoiceDAO invoiceDAO;
    private MovementDAO movementDAO;
    private CityDAO cityDAO;
    private IUserManager userManager;

    public BillServer(InvoiceDAO invoiceDAO, IUserManager userManager, MovementDAO movementDAO, CityDAO cityDAO)
    {
        super(MovementConnection.FactoryName, MovementConnection.BillSystemQueue);

        this.invoiceDAO = invoiceDAO;
        this.userManager = userManager;
        this.movementDAO = movementDAO;
        this.cityDAO = cityDAO;
    }

    /**
     * Called when RPC is initialized.
     */
    public void init()
    {
        super.initRpc(IBillQuery.class, this);
        this.start();
    }

    @Override
    public UserDto authenticate(String user, String password)
    {
        return new UserDto(1, user + " @ bill system");
    }

    @Override
    public boolean adjustKilometerRate(City city, Date addDate, String price)
    {
        return cityDAO.adjustKilometerRate(city, addDate, price);
    }

    @Override
    public List<City> getCities() {
        return cityDAO.findAll();
    }

    @Override
    public Integer generateMonthlyInvoices()
    {
        Pair<Calendar, Calendar> invoiceDateRange = DateHelper.getDateRange();

        System.out.println("Executing query");
        List<VehicleMovement> movements = movementDAO.getMovementsForVehicleInRange(invoiceDateRange.getFirst(), invoiceDateRange.getSecond());

        System.out.println(movements.size());
        return null;
    }


}
