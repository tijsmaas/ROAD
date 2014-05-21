package road.driversystem.beans;

import com.paypal.api.payments.*;
import com.paypal.core.rest.APIContext;
import com.paypal.core.rest.OAuthTokenCredential;
import com.paypal.core.rest.PayPalRESTException;
import road.driversystem.service.DriverService;
import road.driversystem.domain.infoobjects.PaymentSession;
import road.driversystem.utils.Utlities;
import road.movementdtos.dtos.InvoiceDto;
import road.movementdtos.dtos.VehicleInvoiceDto;
import road.movementdtos.dtos.enumerations.PaymentStatus;

import javax.annotation.PostConstruct;
import javax.faces.context.ExternalContext;
import javax.faces.context.FacesContext;
import javax.faces.view.ViewScoped;
import javax.inject.Inject;
import javax.inject.Named;
import java.io.IOException;
import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by Niek on 13/05/14.
 * © Aidas 2014
 */
@Named
@ViewScoped
public class PaymentBean
{
    //PayPal API keys, hardcoded because we need to redeploy anyway.
    private final String clientID = "ARSIrRAcnQatU-D7uwbUoxnUT3AAQV2FAWjtpTDmRHlHcGg0l2KM2IYkKad_";
    private final String secret = "EPj6MBAi7BYQL8gsjbhOZS4e3KnjaKN6L7HoFk5_sSDT7qVb4qqW1AlNm5Ni";
    private String accessToken;

    @Inject
    private UserBean userSession;

    @Inject
    private DriverService service;

    private boolean loadError = false;

    private int currentInvoiceID;

    private InvoiceDto invoice;


    public boolean isLoadError()
    {
        return loadError;
    }

    public int getCurrentInvoiceID()
    {
        return currentInvoiceID;
    }

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
            this.currentInvoiceID = Integer.parseInt(requestedInvoiceID);
            this.invoice = service.getInvoiceWithDetails(this.currentInvoiceID);

            if (this.invoice == null || this.invoice.getUser().id() != this.userSession.getLoggedinUser().id() || this.invoice.getPaymentStatus() == PaymentStatus.SUCCESSFUL)
            {
                this.loadError = true;
            }
        }

        //TODO: Query for the invoices (after Tijs is finished with invoice functionality)


        Map<String, String> sdkConfig = new HashMap<String, String>();
        sdkConfig.put("mode", "sandbox");

        try
        {
            //Request the AccessToken to paypal api
            this.accessToken = new OAuthTokenCredential(clientID, secret, sdkConfig).getAccessToken();
        } catch (PayPalRESTException e)
        {
            this.loadError = true;
        }
    }

    /**
     * Create a PayPal payment and redirect to the authorization page.
     */
    public void pay()
    {
        ExternalContext context = FacesContext.getCurrentInstance().getExternalContext();

        //The sdk config for Sandbox mode
        Map<String, String> sdkConfig = new HashMap<String, String>();
        sdkConfig.put("mode", "sandbox");

        String baseURl = Utlities.getHostnameAndContext();

        //Validate the API access token
        if (this.accessToken != null)
        {

            //Create the API context based on the access token
            APIContext payContext = new APIContext(this.accessToken);
            payContext.setConfigurationMap(sdkConfig);

            //TODO: Set details of logged in user as payer.
            //Create a payer, allow him to pay with a paypal account
            Payer payer = new Payer();
            payer.setPaymentMethod("paypal");


            //Create an amount in euros and set the total
            Amount amount = new Amount();
            amount.setCurrency("EUR");



            //Create a PayPal transaction
            Transaction payTransaction = new Transaction();
            payTransaction.setDescription("Invoice #" + this.invoice.getInvoiceID());

            ItemList itemList = new ItemList();
            List<Item> itemsInList = new ArrayList<>();

            //Create an Item (the invoice should be used for this)
            BigDecimal total = new BigDecimal("0");

            for (VehicleInvoiceDto vehicleInvoiceDto : this.invoice.getVehicleInvoices())
            {
                BigDecimal subTotal = vehicleInvoiceDto.getSubTotal().setScale(2, RoundingMode.CEILING);

                total = total.add(subTotal);
                itemsInList.add(new Item("1", "Account driving for vehicle " + vehicleInvoiceDto.getVehicle().getLicensePlate(), subTotal.toString(), "EUR"));
            }
            total = total.setScale(2, RoundingMode.CEILING);
            amount.setTotal(total.toString());


            itemList.setItems(itemsInList);
            payTransaction.setItemList(itemList);

            //Set the amount for the item
            payTransaction.setAmount(amount);

            //Add the transaction to the list of transactions
            List<Transaction> transactions = new ArrayList<>();
            transactions.add(payTransaction);

            Payment payment = new Payment();
            payment.setIntent("sale");
            payment.setPayer(payer);


            //Create the redirect URLs, set the invoice ID and an easy identifier for the success of the payment
            RedirectUrls redirectUrls = new RedirectUrls();
            redirectUrls.setCancelUrl(baseURl + "/processPayment.xhtml?invoiceID=" + this.invoice.getInvoiceID() + "&success=false");
            redirectUrls.setReturnUrl(baseURl + "/processPayment.xhtml?invoiceID=" + this.invoice.getInvoiceID() + "&success=true");
            payment.setRedirectUrls(redirectUrls);

            payment.setTransactions(transactions);



            try
            {
                //Create a payment using the API
                Payment createdPayment = payment.create(payContext);

                //Create a payment session object, containing the Payment ID, payer ID and current invoice
                PaymentSession paySession = new PaymentSession(accessToken, createdPayment.getId(), createdPayment.getPayer().getPayerInfo().getPayerId(), this.invoice);

                //Set the PaymentSession in the user session
                userSession.setPaymentSession(paySession);

                List<Links> links = createdPayment.getLinks();
                Links approvalLink = null;

                //Find the link for the approval
                for (Links link : links)
                {
                    if (link.getRel().equals("approval_url"))
                    {
                        approvalLink = link;
                        break;
                    }
                }

                if (approvalLink != null)
                {
                    //Redirect the user to the PayPal approval page
                    context.redirect(approvalLink.getHref());
                }

            } catch (PayPalRESTException | IOException e)
            {
                this.loadError = true;
                e.printStackTrace();
            }

        }
    }

    public InvoiceDto getInvoice()
    {
        return invoice;
    }
}
