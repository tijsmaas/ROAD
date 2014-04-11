package test;

import javax.annotation.PostConstruct;
import javax.ejb.Startup;
import javax.inject.Singleton;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

/**
 * Created by Niek on 04/04/14.
 * © Aidas 2014
 */
@Singleton
@Startup
public class TestBeans
{
    @PersistenceContext
    private EntityManager em;

    @PostConstruct
    public void init(){
        Query q = em.createQuery("select edge from entities.Edge edge");
        System.out.println((Long)q.getSingleResult());
    }
}
