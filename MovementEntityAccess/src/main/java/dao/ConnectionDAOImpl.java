package dao;

import entities.Connection;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import java.util.List;

/**
 * Created by Niek on 28/03/14.
 * © Aidas 2014
 */
public class ConnectionDAOImpl implements ConnectionDAO
{

    @PersistenceContext(unitName = "MovementPU")
    private EntityManager em;

    /**
     * {@inheritDoc}
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
     * {@inheritDoc}
     */
    @Override
    public Long count()
    {
        Query query = em.createQuery("select count(connection) from Connection connection");
        return (Long)query.getSingleResult();

    }

    @Override
    public void setEntityManager(EntityManager em)
    {
        this.em = em;
    }
}
