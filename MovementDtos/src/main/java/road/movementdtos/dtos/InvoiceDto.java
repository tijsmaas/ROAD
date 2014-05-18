package road.movementdtos.dtos;

import road.movementdtos.dtos.enumerations.PaymentStatus;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.Date;
import java.util.List;

/**
 * Created by Niek on 17/05/14.
 * © Aidas 2014
 */
public class InvoiceDto
{
    private int invoiceID;
    private int userID;
    private Date generationDate;
    private Date startDate;
    private Date endDate;
    private List<VehicleInvoiceDto> vehicleInvoices;
    private PaymentStatus paymentStatus;
    private BigDecimal total;

    public InvoiceDto()
    {
    }

    public InvoiceDto(int invoiceID, int userID, Date generationDate, Date startDate, Date endDate, int paymentStatus, BigDecimal total)
    {
        this.invoiceID = invoiceID;
        this.userID = userID;
        this.generationDate = generationDate;
        this.startDate = startDate;
        this.endDate = endDate;
        this.paymentStatus = PaymentStatus.values()[paymentStatus];
        this.total =  total.setScale(2, RoundingMode.CEILING);

    }

    public int getInvoiceID()
    {
        return invoiceID;
    }

    public void setInvoiceID(int invoiceID)
    {
        this.invoiceID = invoiceID;
    }

    public int getUserID()
    {
        return userID;
    }

    public void setUserID(int userID)
    {
        this.userID = userID;
    }

    public Date getGenerationDate()
    {
        return generationDate;
    }

    public void setGenerationDate(Date generationDate)
    {
        this.generationDate = generationDate;
    }

    public Date getStartDate()
    {
        return startDate;
    }

    public void setStartDate(Date startDate)
    {
        this.startDate = startDate;
    }

    public Date getEndDate()
    {
        return endDate;
    }

    public void setEndDate(Date endDate)
    {
        this.endDate = endDate;
    }

    public List<VehicleInvoiceDto> getVehicleInvoices()
    {
        return vehicleInvoices;
    }

    public void setVehicleInvoices(List<VehicleInvoiceDto> vehicleInvoices)
    {
        this.vehicleInvoices = vehicleInvoices;
    }

    public PaymentStatus getPaymentStatus()
    {
        return paymentStatus;
    }

    public void setPaymentStatus(PaymentStatus paymentStatus)
    {
        this.paymentStatus = paymentStatus;
    }

    public BigDecimal getTotal()
    {
        return total;
    }

    public void setTotal(BigDecimal total)
    {
        this.total = total;
    }
}

