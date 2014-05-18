package road.movemententities.entities;

import road.movemententities.entities.enumerations.PaymentStatus;

import javax.persistence.*;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Entity
public class Invoice implements MovementEntity<Integer>, Serializable
{

    @Id
    @GeneratedValue
    private int invoiceID;

    @Id
    private int userID;

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

    public Invoice(Date generationDate, Date startDate, Date endDate, int userID){
        this.generationDate = generationDate;
        this.startDate = startDate;
        this.endDate = endDate;
        this.paymentStatus = PaymentStatus.NOT_PAID;
        this.userID = userID;
    }

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

    public int getUserID()
    {
        return userID;
    }

    public void setUserID(int userID)
    {
        this.userID = userID;
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

    public void addVehicleInvoie(VehicleInvoice vehicleInvoice){
        if(this.vehicleInvoices == null){
            this.vehicleInvoices = new ArrayList<>();
        }

        this.vehicleInvoices.add(vehicleInvoice);
    }

    @Override
    public Integer getId() {
        return this.invoiceID;
    }
}
