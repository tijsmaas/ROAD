package road.movemententityaccess.dao;

import road.movemententities.entities.Junction;
import road.movemententities.entities.JunctionRequest;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import java.util.List;

/**
 * Created by Niek on 28/03/14.
 * © Aidas 2014
 */
@Stateless
public class JunctionDAOImpl implements JunctionDAO
{
    @PersistenceContext(unitName = "MovementPU")
    private EntityManager em;
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
