package road.movemententityaccess.dao;

import road.movemententities.entities.Lane;


import javax.annotation.PostConstruct;
import javax.ejb.Stateless;
import javax.inject.Named;
import javax.persistence.*;
import java.util.List;

/**
 * Created by Niek on 28/03/14.
 * © Aidas 2014
 */
@Stateless
public class LaneDAOImpl implements LaneDAO {

    @PersistenceUnit(unitName = "MovementPU")
    private EntityManagerFactory emf;

    private EntityManager em;


    @PostConstruct
    public void init() {
        this.em = emf.createEntityManager();
    }

    /**
     * {@inheritDoc}
     *
     * @param laneID The ID of the lane to find
     */
    @Override
    public Lane find(int laneID) {
        Query query = em.createQuery("Select lane from Lane lane where lane.id = :id");
        query.setParameter("id", laneID);

        List<Lane> resultList = query.getResultList();
        return resultList.isEmpty() ? null : resultList.get(0);
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

    @Override
    public void setEntityManager(EntityManager em) {
        this.em = em;
    }


}
