package road.movemententityaccess.dao;

import road.movemententities.entities.CityDistance;
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
        Query query = em.createQuery("select invoice from Invoice invoice where invoice.userID = :userID order by invoice.paymentStatus asc");
        query.setParameter("userID", userID);

        return query.getResultList();
    }

    @Override
    public Invoice getInvoice(int invoiceID)
    {
        Query query = em.createQuery("select invoice from Invoice invoice where invoice.invoiceID = :id");
        query.setParameter("id", invoiceID);

        List<Invoice> invoices = query.getResultList();

        return invoices.isEmpty() ? null : invoices.get(0);
    }

    @Override
    public boolean updateInvoicePaymentstatus(int invoiceID, PaymentStatus entityPaymentStatus)
    {
        em.getTransaction().begin();

        Invoice invoice = this.getInvoice(invoiceID);


        if(invoice == null){
            throw new IllegalArgumentException("No invoice with given ID exists in the database");
        }

        invoice.setPaymentStatus(entityPaymentStatus);
        em.merge(invoice);

        em.getTransaction().commit();
        return true;

    }

    @Override
    public List<CityDistance> getCityDistancesForVehicleInvoice(int vehicleInvoiceID)
    {
        Query query = em.createQuery("select cityDistance from CityDistance cityDistance where cityDistance.vehicleInvoice.id = :vehicleInvoiceID order by cityDistance.movementDate");
        query.setParameter("vehicleInvoiceID", vehicleInvoiceID);

        return query.getResultList();
    }
}
