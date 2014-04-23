package service;

import connections.BillServerConnection;
import connections.DriverServerConnection;

import javax.annotation.PostConstruct;
import javax.ejb.Singleton;
import javax.ejb.Startup;

/**
 * Created by geh on 21-3-14.
 */
@Singleton @Startup
public class Server
{
    private BillServerConnection billServerConnection;
    private DriverServerConnection driverServerConnection;

    public Server()
    {

    }

    @PostConstruct
    public void init()
    {
        this.billServerConnection = new BillServerConnection();
        this.driverServerConnection = new DriverServerConnection();

        billServerConnection.start();
        driverServerConnection.start();
    }
}
