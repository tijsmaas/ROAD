package dao;

import entities.Edge;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

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
     */
    @Override
    public int count()
    {
        Query query = em.createQuery("select count(edge) from Edge edge");
        return (Integer)query.getSingleResult();
    }

    /**
     * {@inheritDoc}
     * @param edge The edge object to persist
     */
    @Override
    public void create(Edge edge)
    {
        em.persist(edge);
    }

    /**
     * {@inheritDoc}
     * @param edge The edited edge object
     */
    @Override
    public void edit(Edge edge)
    {
        em.merge(edge);
    }

    /**
     * {@inheritDoc}
     * @param edge The edge object to remove
     */
    @Override
    public void remove(Edge edge)
    {
        em.remove(edge);
    }
}
