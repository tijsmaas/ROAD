package road.movemententityaccess.dao;

import road.movemententities.entities.VehicleMovement;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Query;
import java.util.Date;
import java.util.List;

/**
 * Created by Niek on 28/03/14.
 * © Aidas 2014
 */
public class MovementDAOImpl implements MovementDAO
{
    private EntityManager em;

    public MovementDAOImpl(EntityManagerFactory emf)
    {
        this.em = emf.createEntityManager();
    }

    /**
     * {@inheritDoc}
     * @param movementID The ID of the movement
     */
    @Override
    public VehicleMovement find(int movementID)
    {
        Query query = em.createQuery("Select movement from Movement movement where movement.id = :id");
        query.setParameter("id", movementID);

        List<VehicleMovement> resultList = query.getResultList();
        return resultList.isEmpty() ? null : resultList.get(0);
    }

    /**
     * {@inheritDoc}
     * @param date movement date
     */
    @Override
    public List<VehicleMovement> getMovementsByDate(Date date)
    {
        Query query = em.createQuery("select movement from Movement movement where movement.movementDate = :date");
        query.setParameter("date", date);

        List<VehicleMovement> resultList = query.getResultList();
        return resultList;
    }
}
