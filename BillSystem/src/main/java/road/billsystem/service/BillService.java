package road.billsystem.service;

import road.movementdtos.dtos.CityDistanceDto;
import road.movementdtos.dtos.InvoiceDto;
import road.movementdtos.dtos.MovementUserDto;
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


    /**
     * Timer for generating the monthly invoices after each month, automatically.
     * @param t
     */
    @Schedule(month="*", info="Generate monthly invoices") //The scheduling goes off every month
    public void generateMonthlyInvoice(Timer t)
    {
        Calendar cal = Calendar.getInstance();
        int year = cal.get(Calendar.YEAR);
        int month = cal.get(Calendar.MONTH);
        this.generateMonthlyInvoices(month, year);
    }


    /**
     * Generate the monthly invoices for the month and year specified
     * @param month Month for which you want to generate invoices
     * @param year Year for which you want to generate invoices
     * @return The number of invoices generated
     */
    public int generateMonthlyInvoices(int month, int year)
    {
        Integer result = billClient.generateMonthlyInvoices(month, year);
        System.out.println("result: " + result);
        return result;
    }

    public MovementUserDto login(String username, String password)
    {
        return billClient.authenticate(username, password);
    }

    public boolean adjustKilometerRate(CityDto city, Date addDate, String price)
    {
        return billClient.adjustKilometerRate(city, addDate, price);
    }

    /**x
     * Get a list of all available cities
     * @return List of CityDTOS
     */
    public List<CityDto> getCities() {
        return billClient.getCities();
    }

    /**
     * Get a list of invoices for user based on the search query
     * @param searchQuery
     * @return
     */
    public List<InvoiceDto> getInvoicesForSearchQuery(String searchQuery){
        return billClient.getInvoicesForUserSearch(searchQuery);
    }

    /**
     * get the City movements for a vehicleInvoice
     * @param vehicleInvoiceID the vehicleInvoiceID
     * @return
     */
    public List<CityDistanceDto> getCityMovements(int vehicleInvoiceID){
        return billClient.getCityDistances(vehicleInvoiceID);
    }

    /**
     * Get an invoiceDTO containing the details of an invoice
     * @param invoiceID
     * @return
     */
    public InvoiceDto getInvoiceWithDetails(int invoiceID){
        return billClient.getInvoiceDetails(invoiceID);
    }
}
