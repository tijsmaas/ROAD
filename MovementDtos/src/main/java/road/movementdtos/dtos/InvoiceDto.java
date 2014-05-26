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

    /**
     * The identifier of the invoice.
     */
    private int invoiceID;

    /**
     * The owner of the invoice.
     */
    public MovementUserDto user;

    /**
     * The date when the invoice was generated.
     */
    private Date generationDate;

    /**
     * The start date of the period for which the invoice is.
     */
    private Date startDate;

    /**
     * The end date of the period for which the invoice is.
     */
    private Date endDate;

    /**
     * The list of vehicleInvoices belonging to this invoice.
     */
    private List<VehicleInvoiceDto> vehicleInvoices;

    /**
     * Specifies whether the invoice has been paid for or not.
     */
    private PaymentStatus paymentStatus;

    /**
     * The total price of the invoice.
     */
    private BigDecimal total;

    /**
     * The month for which the invoice is.
     */
    private int month;

    /**
     * The year for which the invoice is.
     */
    private int year;

    /**
     * Creates a new instance of the {@link InvoiceDto} class.
     */
    public InvoiceDto()
    {
    }

    /**
     * Creates a new instance of the {@link InvoiceDto} class.
     * @param invoiceID The identifier of the invoice.
     * @param user The owner of the invoice.
     * @param generationDate The date when the invoice was generated.
     * @param startDate The start date of the period for which the invoice is.
     * @param endDate The end date of the period for which the invoice is.
     * @param paymentStatus Specifies whether the invoice has been paid for or not.
     * @param total The total price of the invoice.
     */
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

    /**
     * Get the {@link #invoiceID} of the {@link InvoiceDto}.
     * @return The identifier of the invoice.
     */
    public int getInvoiceID()
    {
        return invoiceID;
    }

    /**
     * Set the {@link #invoiceID} of the {@link InvoiceDto}.
     * @param invoiceID The identifier of the invoice.
     */
    public void setInvoiceID(int invoiceID)
    {
        this.invoiceID = invoiceID;
    }

    /**
     * Get the {@link #generationDate} of the {@link InvoiceDto}.
     * @return The date when the invoice was generated.
     */
    public Date getGenerationDate()
    {
        return generationDate;
    }

    /**
     * Set the {@link #generationDate} of the {@link InvoiceDto}.
     * @param generationDate The date when the invoice was generated.
     */
    public void setGenerationDate(Date generationDate)
    {
        this.generationDate = generationDate;
    }

    /**
     * Get the {@link #startDate} of the {@link InvoiceDto}.
     * @return The start date of the period for which the invoice is.
     */
    public Date getStartDate()
    {
        return startDate;
    }

    /**
     * Set the {@link #startDate} of the {@link InvoiceDto}.
     * @param startDate The start date of the period for which the invoice is.
     */
    public void setStartDate(Date startDate)
    {
        this.startDate = startDate;
    }

    /**
     * Get the {@link #endDate} of the {@link InvoiceDto}.
     * @return The end date of the period for which the invoice is.
     */
    public Date getEndDate()
    {
        return endDate;
    }

    /**
     * Set the {@link #endDate} of the {@link InvoiceDto}.
     * @param endDate The end date of the period for which the invoice is.
     */
    public void setEndDate(Date endDate)
    {
        this.endDate = endDate;
    }

    /**
     * Get the {@link #vehicleInvoices} of the {@link InvoiceDto}.
     * @return The list of vehicleInvoices belonging to this invoice.
     */
    public List<VehicleInvoiceDto> getVehicleInvoices()
    {
        return vehicleInvoices;
    }

    /**
     * Set the {@link #vehicleInvoices} of the {@link InvoiceDto}.
     * @param vehicleInvoices The list of vehicleInvoices belonging to this invoice.
     */
    public void setVehicleInvoices(List<VehicleInvoiceDto> vehicleInvoices)
    {
        this.vehicleInvoices = vehicleInvoices;
    }

    /**
     * Get the {@link #paymentStatus} of the {@link InvoiceDto}.
     * @return The specification whether the invoice has been paid for or not.
     */
    public PaymentStatus getPaymentStatus()
    {
        return paymentStatus;
    }

    /**
     * Set the {@link #paymentStatus} of the {@link InvoiceDto}.
     * @param paymentStatus The specification whether the invoice has been paid for or not.
     */
    public void setPaymentStatus(PaymentStatus paymentStatus)
    {
        this.paymentStatus = paymentStatus;
    }

    /**
     * Get the {@link #total} of the {@link InvoiceDto}.
     * @return The total price of the invoice.
     */
    public BigDecimal getTotal()
    {
        return total;
    }

    /**
     * Set the {@link #total} of the {@link InvoiceDto}.
     * @param total The total price of the invoice.
     */
    public void setTotal(BigDecimal total)
    {
        this.total = total;
    }

    /**
     * Get the {@link #month} of the {@link InvoiceDto}.
     * @return The month for which the invoice is.
     */
    public int getMonth()
    {
        return month;
    }

    /**
     * Set the {@link #month} of the {@link InvoiceDto}.
     * @param month The month for which the invoice is.
     */
    public void setMonth(int month)
    {
        this.month = month;
    }

    /**
     * Get the {@link #year} of the {@link InvoiceDto}.
     * @return The year for which the invoice is.
     */
    public int getYear()
    {
        return year;
    }

    /**
     * Set the {@link #year} of the {@link InvoiceDto}.
     * @param year The year for which the invoice is.
     */
    public void setYear(int year)
    {
        this.year = year;
    }

    /**
     * Get the {@link #user} of the {@link InvoiceDto}.
     * @return The owner of the invoice.
     */
    public MovementUserDto getUser()
    {
        return user;
    }

    /**
     * Set the {@link #user} of the {@link InvoiceDto}.
     * @param user The owner of the invoice.
     */
    public void setUser(MovementUserDto user) {
        this.user = user;
    }
}

