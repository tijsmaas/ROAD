package road.movementservice.servers;

//import aidas.usersystem.IUserManager;

import aidas.userservice.IUserManager;
import aidas.userservice.dto.UserDto;
import aidas.utils.DateHelper;
import javafx.util.Pair;
import road.billdts.connections.IBillQuery;
import road.movementdts.connections.MovementConnection;
import road.movemententityaccess.dao.InvoiceDAO;
import road.movemententityaccess.dao.MovementDAO;
import road.movementservice.connections.ServerConnection;

import java.util.Date;

/**
 * Created by geh on 11-4-14.
 */
public class BillServer extends ServerConnection implements IBillQuery
{
    private InvoiceDAO invoiceDAO;
    private MovementDAO movementDAO;
    private IUserManager userManager;

    public BillServer(InvoiceDAO invoiceDAO, IUserManager userManager, MovementDAO movementDAO)
    {
        super(MovementConnection.FactoryName, MovementConnection.BillSystemQueue);

        this.invoiceDAO = invoiceDAO;
        this.userManager = userManager;
        this.movementDAO = movementDAO;
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
    public Integer generateMonthlyInvoices()
    {
        Pair<Date, Date> invoiceDateRange = DateHelper.getDateRange();

        return null;
    }



}
