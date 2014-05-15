package road.billsystem.beans;

import road.billsystem.service.BillService;

import javax.inject.Inject;
import javax.inject.Named;

/**
 * Created by Niek on 14/05/14.
 * © Aidas 2014
 */
@Named
public class InvoiceBean
{
    @Inject
    private BillService billService;

    public void forceGeneration(){
        billService.generateMonthlyInvoices();
    }
}
