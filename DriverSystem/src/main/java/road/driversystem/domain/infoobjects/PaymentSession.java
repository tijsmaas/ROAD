package road.driversystem.domain.infoobjects;

import road.movementdtos.dtos.InvoiceDto;

import java.util.Date;

/**
 * Payment session stored when authorizing a payment
 * Created by Niek on 13/05/14.
 *  Aidas 2014
 */
public class PaymentSession
{
    private String accesstoken;
    private String paymentID;
    private String payerID;
    private InvoiceDto invoice;
    private Date createdDate;

    public PaymentSession(String accesstoken, String paymentID, String payerID, InvoiceDto invoice)
    {
        this.accesstoken = accesstoken;
        this.paymentID = paymentID;
        this.payerID = payerID;
        this.invoice = invoice;
        this.createdDate = new Date();
    }

    public String getAccesstoken()
    {
        return accesstoken;
    }

    public String getPaymentID()
    {
        return paymentID;
    }

    public String getPayerID()
    {
        return payerID;
    }

    public Date getCreatedDate()
    {
        return createdDate;
    }

    public InvoiceDto getInvoice()
    {
        return invoice;
    }
}
