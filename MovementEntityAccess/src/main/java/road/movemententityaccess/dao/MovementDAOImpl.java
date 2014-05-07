package road.movemententityaccess.dao;

import road.movemententities.entities.Movement;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import java.util.Date;
import java.util.List;

/**
 * Created by Niek on 28/03/14.
 * © Aidas 2014
 */
@Stateless
public class MovementDAOImpl implements MovementDAO
{
    @PersistenceContext(unitName = "MovementPU")
    private EntityManager em;

    /**
     * {@inheritDoc}
     * @param movementID The ID of the movement
     */
    @Override
    public Movement find(int movementID)
    {
        Query query = em.createQuery("Select movement from Movement movement where movement.id = :id");
        query.setParameter("id", movementID);

        List<Movement> resultList = query.getResultList();
        return resultList.isEmpty() ? null : resultList.get(0);
    }

    /**
     * {@inheritDoc}
     * @param date movement date
     */
    @Override
    public List<Movement> getMovementsByDate(Date date)
    {
        Query query = em.createQuery("select movement from Movement movement where movement.movementDate = :date");
        query.setParameter("date", date);

        List<Movement> resultList = query.getResultList();
        return resultList;
    }
}
