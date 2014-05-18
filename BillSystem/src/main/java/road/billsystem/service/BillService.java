package road.billsystem.service;

import aidas.userservice.dto.UserDto;
import road.billdts.connections.BillClient;

import javax.annotation.PostConstruct;
import javax.ejb.Schedule;
import javax.ejb.Singleton;
import javax.ejb.Startup;
import javax.ejb.Timer;
import java.util.Calendar;

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
        Calendar cal = Calendar.getInstance();
        int year = cal.get(Calendar.YEAR);
        int month = cal.get(Calendar.MONTH);
        this.generateMonthlyInvoices(month, year);
    }


    public int generateMonthlyInvoices(int month, int year)
    {
        Object result = billClient.generateMonthlyInvoices(month, year);
        System.out.println("result: " + result);
        return 0;
    }

    public UserDto login(String username, String password)
    {
        return billClient.authenticate(username, password);
    }

}
