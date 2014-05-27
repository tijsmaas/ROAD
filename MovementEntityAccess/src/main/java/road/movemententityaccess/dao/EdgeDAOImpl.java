package road.movemententityaccess.dao;

import road.movemententities.entities.Edge;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Query;
import java.util.List;

/**
 * Created by Niek on 28/03/14.
 *  Aidas 2014
 */
public class EdgeDAOImpl implements EdgeDAO
{
    private EntityManager em;

    public EdgeDAOImpl(EntityManagerFactory emf){
        this.em = emf.createEntityManager();
    }

    @Override
    public Long count()
    {
        Query query = em.createQuery("Select count(edge) from Edge edge");

        return (Long)query.getSingleResult();
    }

    /**
     * {@inheritDoc}
     * @param edgeIdentifier The sumo Edge Identifier
     * @return the found Edge object.
     */
    @Override
    public Edge find(String edgeIdentifier)
    {
        Query query = em.createQuery("select edge from Edge edge where edge.edgeIdentifier = :edgeID");
        query.setParameter("edgeID", edgeIdentifier);

        List<Edge> resultList = query.getResultList();
        return resultList.isEmpty() ? null : resultList.get(0);
    }

 }
