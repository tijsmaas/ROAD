package road.billdts.dto;

import java.util.Date;

/**
 * The data transfer object for performing a search query on invoices.
 *
 * Created by Niek on 21/05/14.
 * Copyright Aidas 2014
 */
public class InvoiceSearchQuery
{
    private String username; //Username or user fullname;
    private String cartrackerID;
    private Date minDate;
    private Date maxDate;

    public InvoiceSearchQuery(String username, String cartrackerID, Date minDate, Date maxDate){
        this.username = username;
        this.cartrackerID = cartrackerID;
        this.minDate = minDate;
        this.maxDate = maxDate;
    }

    public InvoiceSearchQuery(){};

    public String getUsername()
    {
        return username;
    }

    public void setUsername(String username)
    {
        this.username = username;
    }

    public String getCartrackerID()
    {
        return cartrackerID;
    }

    public void setCartrackerID(String cartrackerID)
    {
        this.cartrackerID = cartrackerID;
    }

    public Date getMinDate()
    {
        return minDate;
    }

    public void setMinDate(Date minDate)
    {
        this.minDate = minDate;
    }

    public Date getMaxDate()
    {
        return maxDate;
    }

    public void setMaxDate(Date maxDate)
    {
        this.maxDate = maxDate;
    }
}
