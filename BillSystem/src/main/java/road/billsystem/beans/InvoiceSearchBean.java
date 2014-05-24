package road.billsystem.beans;

import road.billdts.dto.InvoiceSearchQuery;
import road.billsystem.service.BillService;
import road.movementdtos.dtos.InvoiceDto;
import road.movementdts.helpers.DateHelper;
import road.movementdts.helpers.Pair;

import javax.annotation.PostConstruct;
import javax.enterprise.context.RequestScoped;
import javax.faces.model.SelectItem;
import javax.inject.Inject;
import javax.inject.Named;
import java.text.DateFormatSymbols;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

/**
 * Created by Niek on 20/05/14.
 * © Aidas 2014
 */
@Named
@RequestScoped
public class InvoiceSearchBean
{
    @Inject
    private BillService service;

    private String username;
    private String carTrackerID;
    private int minMonth = 0;
    private int minYear = 2010;
    private int maxMonth = 1;
    private int maxYear = 2015;
    private List<InvoiceDto> foundInvoices;

    @PostConstruct
    public void init()
    {
        this.foundInvoices = new ArrayList<>();
    }

    /**
     * Search for the invoices based on the search query
     */
    public void search()
    {
        if (this.username != null)
        {
            this.username = this.username.trim();
        }

        if (this.carTrackerID != null)
        {
            this.carTrackerID = this.carTrackerID.trim();
        }

        Pair<Calendar, Calendar> minRange = DateHelper.getDateRange(minMonth, minYear);
        Pair<Calendar, Calendar> maxRange = DateHelper.getDateRange(maxMonth, maxYear);

        InvoiceSearchQuery searchQuery = new InvoiceSearchQuery(this.username, this.carTrackerID, minRange.getFirst().getTime(), maxRange.getSecond().getTime());

        this.foundInvoices = service.getInvoicesForSearchQuery(searchQuery);
    }


    /**
     * Update the payment status of an invoice
     *
     * @param invoiceDto the invoice to update
     */
    public void updatePaymentStatus(InvoiceDto invoiceDto)
    {

    }

    public String getUsername()
    {
        return username;
    }

    public void setUsername(String username)
    {
        this.username = username;
    }

    public String getCarTrackerID()
    {
        return carTrackerID;
    }

    public void setCarTrackerID(String carTrackerID)
    {
        this.carTrackerID = carTrackerID;
    }

    public List<InvoiceDto> getFoundInvoices()
    {
        return foundInvoices;
    }

    public void setFoundInvoices(List<InvoiceDto> foundInvoices)
    {
        this.foundInvoices = foundInvoices;
    }

    public SelectItem[] getMonths()
    {
        String[] months = new DateFormatSymbols().getMonths();
        SelectItem[] items = new SelectItem[months.length - 1];


        for (int i = 0; i < months.length - 1; i++)
        {
            items[i] = new SelectItem(i, months[i]);
        }

        return items;
    }

    public SelectItem[] getYears()
    {
        SelectItem[] items = new SelectItem[5];

        //LEKKER HARDCODEN. LEKKER LEGACY SUPPORT
        int year = 2011;
        for (int i = 0; i < 5; i++)
        {
            items[i] = new SelectItem(year++);
        }

        return items;
    }

    public int getMinMonth()
    {
        return minMonth;
    }

    public void setMinMonth(int minMonth)
    {
        this.minMonth = minMonth;
    }

    public int getMinYear()
    {
        return minYear;
    }

    public void setMinYear(int minYear)
    {
        this.minYear = minYear;
    }

    public int getMaxMonth()
    {
        return maxMonth;
    }

    public void setMaxMonth(int maxMonth)
    {
        this.maxMonth = maxMonth;
    }

    public int getMaxYear()
    {
        return maxYear;
    }

    public void setMaxYear(int maxYear)
    {
        this.maxYear = maxYear;
    }
}
