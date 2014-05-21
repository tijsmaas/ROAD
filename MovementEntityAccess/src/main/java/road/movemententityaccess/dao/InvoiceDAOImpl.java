package road.movemententityaccess.dao;

import road.movemententities.entities.CityDistance;
import road.movemententities.entities.Invoice;
import road.movemententities.entities.VehicleMovement;
import road.movemententities.entities.enumerations.PaymentStatus;
import road.movemententityaccess.helper.InvoiceGenerator;

import javax.mail.Message;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import javax.naming.InitialContext;
import javax.naming.NamingException;
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

    /**
     * {@inheritDoc}
     * @param vehicleMovements List of movements you want to generate invoices for
     * @param startDate Starting date of the invoice
     * @param endDate End date of the invoices
     * @return number of generated invoices
     */
    @Override
    public List<Invoice> generate(List<VehicleMovement> vehicleMovements, Date startDate, Date endDate)
    {
        this.em.getTransaction().begin();

        InvoiceGenerator invoiceGenerator = new InvoiceGenerator(vehicleMovements, this.em, startDate, endDate);
        List<Invoice> invoices = invoiceGenerator.generate();

        this.em.getTransaction().commit();

        return invoices;
    }

    /**
     * {@inheritDoc}
     * @param userID the ID of the user
     * @return List of Invoices
     */
    @Override
    public List<Invoice> getInvoicesForUser(int userID)
    {
        Query query = em.createQuery("select invoice from Invoice invoice where invoice.userID = :userID order by invoice.paymentStatus asc");
        query.setParameter("userID", userID);

        return query.getResultList();
    }

    /**
     * {@inheritDoc}
     * @param invoiceID
     * @return the found invoice
     */
    @Override
    public Invoice getInvoice(int invoiceID)
    {
        Query query = em.createQuery("select invoice from Invoice invoice where invoice.invoiceID = :id");
        query.setParameter("id", invoiceID);

        List<Invoice> invoices = query.getResultList();

        return invoices.isEmpty() ? null : invoices.get(0);
    }


    /**
     * {@inheritDoc}
     * @param invoiceID the ID of the invoice you want to update
     * @param entityPaymentStatus
     * @return true when success, false when not
     */
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

    /**
     * {@inheritDoc}
     * @param vehicleInvoiceID the id of the VehicleInvoice for which you want to get the citydistances
     * @return List of cityDistances
     */
    @Override
    public List<CityDistance> getCityDistancesForVehicleInvoice(int vehicleInvoiceID)
    {
        Query query = em.createQuery("select cityDistance from CityDistance cityDistance where cityDistance.vehicleInvoice.id = :vehicleInvoiceID order by cityDistance.movementDate");
        query.setParameter("vehicleInvoiceID", vehicleInvoiceID);

        return query.getResultList();
    }

    /**
     * {@inheritDoc}
     * @param eligibleUserIDs List of eligible userIDs
     * @return
     */
    @Override
    public List<Invoice> findInvoicesForUserIDs(List<Integer> eligibleUserIDs)
    {
        Query query = em.createQuery("Select invoice from Invoice invoice where invoice.userID IN :idList");
        query.setParameter("idList", eligibleUserIDs);

        return query.getResultList();
    }
}
