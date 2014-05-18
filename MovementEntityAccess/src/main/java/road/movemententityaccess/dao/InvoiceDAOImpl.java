package road.movemententityaccess.dao;

import road.movemententities.entities.Invoice;
import road.movemententities.entities.VehicleMovement;
import road.movemententities.entities.enumerations.PaymentStatus;
import road.movemententityaccess.helper.InvoiceGenerator;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Query;
import java.util.Date;
import java.util.List;

/**
 * Created by Niek on 14/05/14.
 * © Aidas 2014
 */
public class InvoiceDAOImpl implements InvoiceDAO
{
    private EntityManager em;

    public InvoiceDAOImpl(EntityManagerFactory emf)
    {
        em = emf.createEntityManager();
    }

    @Override
    public int generate(List<VehicleMovement> vehicleMovements, Date startDate, Date endDate)
    {

        em.getTransaction().begin();
        InvoiceGenerator invoiceGenerator = new InvoiceGenerator(vehicleMovements, this.em, startDate, endDate);
        invoiceGenerator.generate();


        em.getTransaction().commit();
        return invoiceGenerator.getInvoices().size();

    }

    @Override
    public List<Invoice> getInvoicesForUser(int userID)
    {
        Query query = em.createQuery("select invoice from Invoice invoice where invoice.userID = :userID");
        query.setParameter("userID", userID);

        return query.getResultList();
    }

    @Override
    public Invoice getInvoice(int invoiceID)
    {
        return em.find(Invoice.class, invoiceID);
    }

    @Override
    public boolean updateInvoicePaymentstatus(int invoiceID, road.movementdtos.dtos.enumerations.PaymentStatus dtoPaymentStatus)
    {
        em.getTransaction().begin();
        PaymentStatus entityPaymentStatus = road.movemententities.entities.enumerations.PaymentStatus.values()[dtoPaymentStatus.ordinal()];

        Invoice invoice = this.getInvoice(invoiceID);

        if(invoice == null){
            throw new IllegalArgumentException("No invoice with given ID exists in the database");
        }

        invoice.setPaymentStatus(entityPaymentStatus);
        em.merge(invoice);

        em.getTransaction().commit();
        return true;

    }
}
