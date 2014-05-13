package beans;

import javax.faces.context.ExternalContext;
import javax.faces.context.FacesContext;
import javax.faces.view.ViewScoped;
import javax.inject.Inject;
import javax.inject.Named;
import java.math.BigDecimal;

/**
 * Created by Niek on 13/05/14.
 * © Aidas 2014
 */
@Named
@ViewScoped
public class PaymentBean
{
    @Inject
    private UserBean userSession;

    private boolean loadError = false;

    private String currentInvoiceID;

    private BigDecimal amountToPay;

    public boolean isLoadError()
    {
        return loadError;
    }

    public String getCurrentInvoiceID()
    {
        return currentInvoiceID;
    }

    public BigDecimal getAmountToPay()
    {
        return amountToPay;
    }

    public void loadInvoice()
    {
        ExternalContext context = FacesContext.getCurrentInstance().getExternalContext();

        String requestedInvoiceID = context.getRequestParameterMap().get("invoiceID");
        if (requestedInvoiceID.isEmpty() || requestedInvoiceID == null)
        {
            this.loadError = true;
        } else {
            this.currentInvoiceID = requestedInvoiceID;
        }

        //TODO: Query for the invoices (after Tijs is finished with invoice functionality)

        this.amountToPay = new BigDecimal(100.20);

    }
}
