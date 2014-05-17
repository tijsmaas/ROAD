package road.movemententityaccess.dao;

import road.movemententities.entities.VehicleMovement;
import road.movemententityaccess.helper.InvoiceGenerator;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
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
    public void generate(List<VehicleMovement> vehicleMovements)
    {
        InvoiceGenerator invoiceGenerator = new InvoiceGenerator(vehicleMovements, this.em);
        invoiceGenerator.generate();
    }
}
