package road.billsystem.service;

import road.billdts.connections.BillClient;

import javax.annotation.PostConstruct;
import javax.ejb.Schedule;
import javax.ejb.Startup;
import javax.ejb.Timer;
import javax.ejb.Singleton;

/**
 * Created by Niek on 14/05/14.
 * © Aidas 2014
 */
@Singleton
@Startup
public class BillService
{
    private BillClient billClient;
    private boolean didSchedule = false;

    @PostConstruct
    private void init()
    {
        this.billClient = new BillClient();
        billClient.start();
    }


    //@Schedule(second = "*/3", minute = "*", hour = "*", info = "Generate monthly invoices")
    @Schedule(month="*", info="Generate monthly invoices")
    public void generateMonthlyInvoice(Timer t)
    {
        this.generateMonthlyInvoices();
    }


    public void generateMonthlyInvoices()
    {
        billClient.generateMonthlyInvoices();
    }
}
