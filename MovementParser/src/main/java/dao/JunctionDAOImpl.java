package dao;

import entities.Junction;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

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

}
