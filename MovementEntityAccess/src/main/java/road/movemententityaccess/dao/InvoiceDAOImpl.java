package road.movemententityaccess.dao;

import road.movemententities.entities.Invoice;
import road.movemententities.entities.VehicleMovement;
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

        InvoiceGenerator invoiceGenerator = new InvoiceGenerator(vehicleMovements, this.em, startDate, endDate);
        invoiceGenerator.generate();

        for (Invoice invoice : invoiceGenerator.getInvoices())
        {
            em.persist(invoice);
            System.out.println("persisting invoice");
        }

        System.out.println("evicting cache");
        em.getEntityManagerFactory().getCache().evictAll();


        return invoiceGenerator.getInvoices().size();

    }

    @Override
    public List<Invoice> getInvoicesForUser(int userID)
    {
        Query query = em.createQuery("select invoice from Invoice invoice where invoice.userID = :userID");
        query.setParameter("userID", userID);

        return query.getResultList();
    }
}
