package dao;

import entities.Lane;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import java.util.List;

/**
 * Created by Niek on 28/03/14.
 * © Aidas 2014
 */
public class LaneDAOImpl implements LaneDAO
{
    @PersistenceContext(unitName = "MovementPU")
    private EntityManager em;

    /**
     * {@inheritDoc}
     * @param laneID The ID of the lane to find
     */
    @Override
    public Lane find(int laneID)
    {
        Query query = em.createQuery("Select lane from Lane lane where lane.id = :id");
        query.setParameter("id", laneID);

        List<Lane> resultList = query.getResultList();
        return resultList.isEmpty() ? null : resultList.get(0);
    }

    /**
     * {@inheritDoc}
     * @param laneIdentifier The SUMO lane identifier
     */
    @Override
    public Lane find(String laneIdentifier)
    {
        Query query = em.createQuery("Select lane from Lane lane where lane.laneIdentifier = :id");
        query.setParameter("id", laneIdentifier);

        List<Lane> resultList = query.getResultList();
        return resultList.isEmpty() ? null : resultList.get(0);
    }
    

    /**
     * {@inheritDoc}
     */
    @Override
    public Long count()
    {
        Query query = em.createQuery("select count(lane) from Lane lane");
        return (Long)query.getSingleResult();

    }

    @Override
    public void setEntityManager(EntityManager em)
    {
        this.em = em;
    }


}
