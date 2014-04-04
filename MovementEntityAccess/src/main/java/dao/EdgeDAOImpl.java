package dao;

import entities.Edge;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import java.util.List;

/**
 * Created by Niek on 28/03/14.
 * © Aidas 2014
 */
public class EdgeDAOImpl implements EdgeDAO
{
    @PersistenceContext(unitName = "MovementPU")
    private EntityManager em;

    /**
     * {@inheritDoc}
     * @param edgeID The ID of the edge to find
     * @return the found edge object
     */
    @Override
    public Edge find(int edgeID)
    {
        Query query = em.createQuery("Select edge from Edge edge where edge.id = :id");
        query.setParameter("id", edgeID);

        List<Edge> resultList = query.getResultList();
        return resultList.isEmpty() ? null : resultList.get(0);
    }

    /**
     * {@inheritDoc}
     * @param edgeIdentifier The sumo Edge Identifier
     * @return the found Edge object.
     */
    @Override
    public Edge findByIdentifier(String edgeIdentifier)
    {
        Query query = em.createQuery("select edge from Edge edge where edge.edgeIdentifier = :edgeID");
        query.setParameter("edgeID", edgeIdentifier);

        List<Edge> resultList = query.getResultList();
        return resultList.isEmpty() ? null : resultList.get(0);
    }
}
