package road.movementdtos.dtos;

import road.movementdtos.dtos.enumerations.PaymentStatus;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

/**
 * Created by Niek on 17/05/14.
 * © Aidas 2014
 */
public class InvoiceDto
{
    private int invoiceID;
    public MovementUserDto user;
    private Date generationDate;
    private Date startDate;
    private Date endDate;
    private List<VehicleInvoiceDto> vehicleInvoices;
    private PaymentStatus paymentStatus;
    private BigDecimal total;
    private int month;
    private int year;

    public InvoiceDto()
    {
    }

    public InvoiceDto(int invoiceID, MovementUserDto user, Date generationDate, Date startDate, Date endDate, int paymentStatus, BigDecimal total)
    {
        this.invoiceID = invoiceID;
        this.generationDate = generationDate;
        this.startDate = startDate;
        this.endDate = endDate;
        this.user = user;
        this.paymentStatus = PaymentStatus.values()[paymentStatus];
        this.total =  total.setScale(2, RoundingMode.CEILING);

        Calendar cal = Calendar.getInstance();
        cal.setTime(startDate);
        this.year = cal.get(Calendar.YEAR);
        this.month = cal.get(Calendar.MONTH) + 1; //We add the plus one because for some reason months start at 0 instead of 1.

    }

    public int getInvoiceID()
    {
        return invoiceID;
    }

    public void setInvoiceID(int invoiceID)
    {
        this.invoiceID = invoiceID;
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

    public int getMonth()
    {
        return month;
    }

    public void setMonth(int month)
    {
        this.month = month;
    }

    public int getYear()
    {
        return year;
    }

    public void setYear(int year)
    {
        this.year = year;
    }

    public MovementUserDto getUser()
    {
        return user;
    }

    public void setUser(MovementUserDto user) {
        this.user = user;
    }
}

