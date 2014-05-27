package road.movemententities.entities;

import road.movemententities.entities.enumerations.PaymentStatus;

import javax.persistence.*;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * The Invoice class specifies the entity that makes it possible to save invoices to the database
 */
@Entity
public class Invoice implements MovementEntity<Integer>, Serializable
{

    @Id
    @GeneratedValue
    private int invoiceID;

    @ManyToOne
    private MovementUser user;

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

    /**
     * Empty constructor required for JPA
     */
    public Invoice() {}

    /**
     * Create a new instance of the Invoice object
     * @param generationDate the Date the invoice was generated
     * @param startDate the Date the first movement of the invoice were calculated
     * @param endDate The last date of movements calculated
     * @param user The {@link MovementUser} this invoice belongs to
     */
    public Invoice(Date generationDate, Date startDate, Date endDate, MovementUser user){
        this.generationDate = generationDate;
        this.startDate = startDate;
        this.endDate = endDate;
        this.paymentStatus = PaymentStatus.NOT_PAID;
        this.user = user;
    }

    /**
     * Get the {@link VehicleInvoice vhicleInvoices} for this invoice
     * @return the {@link road.movemententities.entities.VehicleInvoice vehicleInvoices} for this invoice
     */
    public List<VehicleInvoice> getVehicleInvoices()
    {
        return vehicleInvoices;
    }

    /**
     * get the current {@link PaymentStatus}
     * @return the current {@link road.movemententities.entities.enumerations.PaymentStatus}
     */
    public PaymentStatus getPaymentStatus()
    {
        return paymentStatus;
    }

    /**
     * Get the ID for this invoice
     * @return the ID for this invoice
     */
    public int getInvoiceID()
    {
        return invoiceID;
    }

    /**
     * get the {@link MovementUser} of this invoice
     * @return the {@link MovementUser} of this invoice
     */
    public MovementUser getUser()
    {
        return user;
    }

    /**
     * set the {@link road.movemententities.entities.MovementUser} of this invloice
     * @param user the {@link} movementUser to set
     */
    public void setUser(MovementUser user)
    {
        this.user = user;
    }

    /**
     * Get the start date for this invoice
     * @return the start date for this invoice
     */
    public Date getStartDate()
    {
        return startDate;
    }

    /**
     * get the end date for this invoice
     * @return the end date of this invoice
     */
    public Date getEndDate()
    {
        return endDate;
    }

    /**
     * Set the invoiceID for this invoice
     * @param invoiceID the new ID
     */
    public void setInvoiceID(int invoiceID)
    {
        this.invoiceID = invoiceID;
    }

    /**
     * Set the start date of this invoice
     * @param startDate the new startdate
     */
    public void setStartDate(Date startDate)
    {
        this.startDate = startDate;
    }

    /**
     * Set the end date of this invoice
     * @param endDate the new end date
     */
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
