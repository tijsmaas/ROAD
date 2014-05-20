package road.billsystem.service;

import road.userservice.dto.UserDto;
import road.billdts.connections.BillClient;
import road.movementdtos.dtos.CityDto;

import javax.annotation.PostConstruct;
import javax.ejb.Schedule;
import javax.ejb.Singleton;
import javax.ejb.Startup;
import javax.ejb.Timer;
import java.io.Serializable;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

/**
 * Created by Niek on 14/05/14.
 * © Aidas 2014
 */
@Singleton
@Startup
public class BillService implements Serializable
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
        Integer result = billClient.generateMonthlyInvoices(month, year);
        System.out.println("result: " + result);
        return result;
    }

    public UserDto login(String username, String password)
    {
        return billClient.authenticate(username, password);
    }

    public boolean adjustKilometerRate(CityDto city, Date addDate, String price)
    {
        return billClient.adjustKilometerRate(city, addDate, price);
    }

    public List<CityDto> getCities() {
        return billClient.getCities();
    }

}
