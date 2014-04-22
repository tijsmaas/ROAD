package service;

import connections.BillServerConnection;
import connections.DriverServerConnection;

import javax.annotation.PostConstruct;
import javax.ejb.Singleton;
import javax.ejb.Startup;
import javax.inject.Inject;

/**
 * Created by geh on 21-3-14.
 */
@Singleton @Startup
public class Server
{
    @Inject
    private BillServerConnection billServerConnection;
    @Inject
    private DriverServerConnection driverServerConnection;

    public Server()
    {

    }

    @PostConstruct
    public void init()
    {
        billServerConnection.start();
        driverServerConnection.start();
    }
}
