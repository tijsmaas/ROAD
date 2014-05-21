package road.billsystem.beans;

import road.billsystem.service.BillService;
import road.movementdtos.dtos.InvoiceDto;

import javax.annotation.PostConstruct;
import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;
import javax.inject.Named;
import java.util.ArrayList;
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

    private String searchQuery;
    private List<InvoiceDto> foundInvoices;

    @PostConstruct
    public void init(){
        this.foundInvoices = new ArrayList<>();
    }

    /**
     * Search for the invoices based on the search query
     */
    public void search(){
        this.searchQuery = this.searchQuery.trim();
        if(this.searchQuery == null || this.searchQuery.isEmpty()) {
            this.foundInvoices = new ArrayList<>();
            return;
        }

        this.foundInvoices = service.getInvoicesForSearchQuery(this.searchQuery);
    }


    /**
     * Update the payment status of an invoice
     * @param invoiceDto the invoice to update
     */
    public void updatePaymentStatus(InvoiceDto invoiceDto){

    }

    public String getSearchQuery()
    {
        return searchQuery;
    }

    public void setSearchQuery(String searchQuery)
    {
        this.searchQuery = searchQuery;
    }

    public List<InvoiceDto> getFoundInvoices()
    {
        return foundInvoices;
    }

    public void setFoundInvoices(List<InvoiceDto> foundInvoices)
    {
        this.foundInvoices = foundInvoices;
    }
}
