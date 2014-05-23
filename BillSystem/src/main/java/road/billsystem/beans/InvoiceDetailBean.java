package road.billsystem.beans;

import road.billsystem.service.BillService;
import road.movementdtos.dtos.InvoiceDto;
import road.movementdtos.dtos.enumerations.PaymentStatus;

import javax.annotation.PostConstruct;
import javax.enterprise.context.RequestScoped;
import javax.faces.context.ExternalContext;
import javax.faces.context.FacesContext;
import javax.inject.Inject;
import javax.inject.Named;

/**
 * Created by Niek on 20/05/14.
 * © Aidas 2014
 */
@Named
@RequestScoped
public class InvoiceDetailBean
{
    @Inject
    private BillService service;

    @Inject
    private UserBean userSession;

    private InvoiceDto invoice;
    private boolean loadError;

    /**
     * Load the invoice based on the requestParameter
     */
    @PostConstruct
    public void init()
    {
        ExternalContext context = FacesContext.getCurrentInstance().getExternalContext();

        String requestedInvoiceID = context.getRequestParameterMap().get("invoiceID");
        if (requestedInvoiceID.isEmpty() || requestedInvoiceID == null)
        {
            this.loadError = true;
        } else
        {
            int invoiceID = Integer.parseInt(requestedInvoiceID);
            this.invoice = service.getInvoiceWithDetails(invoiceID);

            if (this.invoice == null)
            {
                this.loadError = true;
            }
        }
    }

    /**
     * Mark the current invoice as cancelled
     */
    public void cancelInvoice()
    {
        PaymentStatus cancelled = PaymentStatus.CANCELLED;

        boolean updated = service.updateInvoiceStatus(this.invoice.getInvoiceID(), cancelled);
        if (updated)
        {
            this.invoice.setPaymentStatus(cancelled);
        }

    }

    /**
     * Mark the current invoice as paid
     */
    public void markInvoiceAsPaid()
    {
        PaymentStatus paid = PaymentStatus.SUCCESSFUL;

        boolean updated = service.updateInvoiceStatus(this.invoice.getInvoiceID(), paid);
        if (updated)
        {
            this.invoice.setPaymentStatus(paid);
        }
    }

    public InvoiceDto getInvoice()
    {
        return this.invoice;
    }

    public void setInvoice(InvoiceDto invoice)
    {
        this.invoice = invoice;
    }

    public boolean isLoadError()
    {
        return loadError;
    }

    public void setLoadError(boolean loadError)
    {
        this.loadError = loadError;
    }


}
