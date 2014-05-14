package domain.infoobjects;

import java.util.Date;

/**
 * Payment session stored when authorizing a payment
 * Created by Niek on 13/05/14.
 * © Aidas 2014
 */
public class PaymentSession
{
    private String accesstoken;
    private String paymentID;
    private String payerID;
    private String invoiceID;
    private Date createdDate;

    public PaymentSession(String accesstoken, String paymentID, String payerID, String invoiceID){
        this.accesstoken = accesstoken;
        this.paymentID = paymentID;
        this.payerID = payerID;
        this.invoiceID = invoiceID;
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

    public String getInvoiceID()
    {
        return invoiceID;
    }
}
