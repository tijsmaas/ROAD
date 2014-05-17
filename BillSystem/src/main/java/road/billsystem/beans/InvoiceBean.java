package road.billsystem.beans;

import road.billsystem.service.BillService;

import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;
import javax.inject.Named;

/**
 * Created by Niek on 14/05/14.
 * © Aidas 2014
 */
@Named
@RequestScoped
public class InvoiceBean
{
    @Inject
    private BillService billService;

    private int selectedMonth;

    private int selectedYear;

    private boolean done = false;

    private int numberGenerated;

    public boolean isDone()
    {
        return done;
    }

    public int getNumberGenerated()
    {
        return numberGenerated;
    }

    public int getSelectedMonth()
    {
        return selectedMonth;
    }

    public void setSelectedMonth(int selectedMonth)
    {
        this.selectedMonth = selectedMonth;
    }

    public int getSelectedYear()
    {
        return selectedYear;
    }

    public void setSelectedYear(int selectedYear)
    {
        this.selectedYear = selectedYear;
    }

    public void manualGeneration(){

        numberGenerated = billService.generateMonthlyInvoices(this.selectedMonth, this.selectedYear);
        done = true;

    }
}
