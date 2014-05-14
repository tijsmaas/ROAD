package road.movemententities.entities;

import road.movemententities.entities.enumerations.PaymentStatus;

import javax.persistence.*;
import java.util.Date;
import java.util.List;

@Entity
public class Invoice implements MovementEntity<Integer> {

    @Id
    @GeneratedValue
    private int invoiceID;

    @Temporal(TemporalType.DATE)
    private Date generationDate;

    /* Start date of invoice */
    @Temporal(TemporalType.DATE)
    private Date startDate;

    @Temporal(TemporalType.DATE)
    private Date endDate;

    @OneToMany
    private List<VehicleInvoice> vehicleInvoices;

    @Enumerated(EnumType.STRING)
    private PaymentStatus paymentStatus;

    public Invoice() {}

    public List<VehicleInvoice> getVehicleInvoices()
    {
        return vehicleInvoices;
    }

    public PaymentStatus getPaymentStatus()
    {
        return paymentStatus;
    }

    public int getInvoiceID()
    {
        return invoiceID;
    }

    public Date getStartDate()
    {
        return startDate;
    }

    public Date getEndDate()
    {
        return endDate;
    }

    public void setInvoiceID(int invoiceID)
    {
        this.invoiceID = invoiceID;
    }

    public void setStartDate(Date startDate)
    {
        this.startDate = startDate;
    }

    public void setEndDate(Date endDate)
    {
        this.endDate = endDate;
    }

    public void setVehicleInvoices(List<VehicleInvoice> vehicleInvoices)
    {
        this.vehicleInvoices = vehicleInvoices;
    }

    public void setPaymentStatus(PaymentStatus paymentStatus)
    {
        this.paymentStatus = paymentStatus;
    }

    public Date getGenerationDate()
    {
        return generationDate;
    }

    public void setGenerationDate(Date generationDate)
    {
        this.generationDate = generationDate;
    }

    @Override
    public Integer getId() {
        return this.invoiceID;
    }
}
