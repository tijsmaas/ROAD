package road.movemententityaccess.dao;

import road.movemententities.entities.Connection;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Query;
import java.util.List;

/**
 * {@inheritDoc}
 *
 * Created by Niek on 28/03/14.
 *  Aidas 2014
 */
public class ConnectionDAOImpl implements ConnectionDAO
{

    private EntityManager em;

    public ConnectionDAOImpl(EntityManagerFactory emf){
        em = emf.createEntityManager();
    }

    /**
     * {@inheritDoc}
     * @param connectionID the ID of the connection
     * @return the found {@link road.movemententities.entities.Connection}
     */
    @Override
    public Connection find(int connectionID)
    {
        Query query = em.createQuery("Select connection from Connection connection where connection.id = :id");
        query.setParameter("id", connectionID);

        List<Connection> foundConnections = query.getResultList();
        return foundConnections.isEmpty() ? null : foundConnections.get(0);
    }


    /**
     * The amount of Connections in the database
     * @return the amount {@link Connection connections} in the database
     */
    @Override
    public Long count()
    {
        Query query = em.createQuery("select count(connection) from Connection connection");
        return (Long)query.getSingleResult();

    }
}
