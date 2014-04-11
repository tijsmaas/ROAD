package parser.dao;

import parser.dao.ConnectionDAO;
import entities.Connection;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

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
    public int count()
    {
        Query query = em.createQuery("select count(connection) from Connection connection");
        return (Integer)query.getSingleResult();

    }

    /**
     * {@inheritDoc}
     * @param connection The connection object to persist
     */
    @Override
    public void create(Connection connection)
    {
        em.persist(connection);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void edit(Connection connection)
    {
        em.merge(connection);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void remove(Connection connection)
    {
        em.remove(connection);
    }

}
