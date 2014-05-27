package road.driversystem.beans;

import road.driversystem.service.DriverService;
import road.movementdtos.dtos.InvoiceDto;

import javax.annotation.PostConstruct;
import javax.enterprise.context.RequestScoped;
import javax.faces.context.ExternalContext;
import javax.faces.context.FacesContext;
import javax.inject.Inject;
import javax.inject.Named;

/**
 * Created by Niek on 20/05/14.
 *  Aidas 2014
 */
@Named
@RequestScoped
public class InvoiceDetailBean
{
    @Inject
    private DriverService service;

    @Inject
    private UserBean userSession;

    private InvoiceDto invoice;
    private boolean loadError;

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

            if (this.invoice == null || this.invoice.getUser().id() != this.userSession.getLoggedinUser().id())
            {
                this.loadError = true;
            }
        }
    }

    public InvoiceDto getInvoice()
    {
        return invoice;
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
