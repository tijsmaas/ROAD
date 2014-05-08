package road.movemententityaccess.dao;

import road.movemententities.entities.Lane;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Query;
import java.util.List;

/**
 * Created by Niek on 28/03/14.
 * © Aidas 2014
 */
public class LaneDAOImpl implements LaneDAO {

    //@PersistenceUnit(unitName = "MovementPU")
    //private EntityManagerFactory emf;

    private EntityManager em;

    public LaneDAOImpl(EntityManagerFactory emf)
    {
        this.em = emf.createEntityManager();
    }

    /**
     * {@inheritDoc}
     *
     * @param laneIdentifier The SUMO lane identifier
     */
    @Override
    public Lane find(String laneIdentifier) {
        Query query = em.createQuery("Select lane from Lane lane where lane.laneIdentifier = :id");
        query.setParameter("id", laneIdentifier);

        List<Lane> resultList = query.getResultList();
        return resultList.isEmpty() ? null : resultList.get(0);
    }


    /**
     * {@inheritDoc}
     */
    @Override
    public Long count() {
        Query query = em.createQuery("select count(lane) from Lane lane");
        return (Long) query.getSingleResult();

    }


}
