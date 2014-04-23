package service;

import connections.BillServerConnection;
import connections.DriverServerConnection;
import connections.PoliceServerConnection;

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
    private PoliceServerConnection policeServerConnection;

    public Server()
    {

    }

    @PostConstruct
    public void init()
    {
        this.billServerConnection = new BillServerConnection();
        this.driverServerConnection = new DriverServerConnection();
        this.policeServerConnection = new PoliceServerConnection();

        this.billServerConnection.start();
        this.driverServerConnection.start();
        this.policeServerConnection.start();
    }
}
