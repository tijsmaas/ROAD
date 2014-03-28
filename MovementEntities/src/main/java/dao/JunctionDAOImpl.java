package dao;

import entities.Junction;
import entities.JunctionRequest;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import java.util.List;

/**
 * Created by Niek on 28/03/14.
 * © Aidas 2014
 */
public class JunctionDAOImpl implements JunctionDAO
{

    @PersistenceContext(unitName = "MovementPU")
    private EntityManager em;

    /**
     * {@inheritDoc}
     */
    @Override
    public int count()
    {
        Query q = em.createQuery("select count(junction) from Junction junction");
        return (Integer)q.getSingleResult();
    }

    /**
     * {@inheritDoc}
     * @param junction The Junction object to persist
     */
    @Override
    public void create(Junction junction)
    {
        em.persist(junction);
    }

    /**
     * {@inheritDoc}
     * @param junction The edited Junction object
     */
    @Override
    public void edit(Junction junction)
    {
        em.merge(junction);
    }

    /**
     * {@inheritDoc}
     * @param junction The Junction object to remove
     */
    @Override
    public void remove(Junction junction)
    {
        em.remove(junction);
    }

    /**
     * {@inheritDoc}
     * @param junctionID The ID of the junction
     */
    @Override
    public Junction find(int junctionID)
    {
        Query query = em.createQuery("Select junction from Junction junction where junction.id = :id");
        query.setParameter("id", junctionID);

        List<Junction> resultList = query.getResultList();
        return resultList.isEmpty() ? null : resultList.get(0);
    }

    /**
     * {@inheritDoc}
     * @param junctionIdentifier The SUMO Junction Identifier
     */
    @Override
    public Junction findByIdentifier(String junctionIdentifier)
    {
        Query query = em.createQuery("select  junction from Junction junction where junction.junctionIdentifier = :junctionID");
        query.setParameter("junctionID", junctionIdentifier);

        List<Junction> resultList = query.getResultList();
        return resultList.isEmpty() ? null : resultList.get(0);
    }

    /**
     * {@inheritDoc}
     * @param junction The Junction object to search in
     * @param index the index of the junction request
     */
    @Override
    public JunctionRequest findJunctionRequest(Junction junction, int index)
    {
        Query query = em.createQuery("Select request from JunctionRequest request where request.junction.id = :junctionid and request.index = :index");
        query.setParameter("junctionid", junction.getId());
        query.setParameter("index", index);

        List<JunctionRequest> resultList = query.getResultList();
        return resultList.isEmpty() ? null : resultList.get(0);
    }
}
