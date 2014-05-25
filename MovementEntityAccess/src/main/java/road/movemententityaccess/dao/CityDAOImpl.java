package road.movemententityaccess.dao;

import road.movemententities.entities.City;
import road.movemententities.entities.CityRate;
import road.movemententities.entities.Vehicle;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Query;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * Created by Mitch on 28/03/14.
 * © Aidas 2014
 */
public class CityDAOImpl implements CityDAO
{
    private EntityManager em;

    public CityDAOImpl(EntityManagerFactory emf){
        this.em = emf.createEntityManager();
    }

    @Override
    public Long count()
    {
        Query query = em.createQuery("Select count(city) from City city");

        return (Long)query.getSingleResult();
    }

    /**
     * {@inheritDoc}
     * @param cityIdentifier The sumo City Identifier
     * @return the found City object.
     */
    @Override
    public City find(String cityIdentifier)
    {
        Query query = em.createQuery("select city from City city where city.cityId = :cityID");
        query.setParameter("cityID", cityIdentifier);

        List<City> resultList = query.getResultList();
        return resultList.isEmpty() ? null : resultList.get(0);
    }

    /**
     * @return the found City object.
     */
    @Override
    public List<City> findAll()
    {
        Query query = em.createQuery("select city from City city");

        List<City> resultList = query.getResultList();
        return resultList;
    }

    @Override
    public boolean adjustKilometerRate(String cityId, Date addDate, String price)
    {
        try
        {
            em.getTransaction().begin();
            City city = find(cityId);
            CityRate cityRate = new CityRate(city, addDate, price);
            em.merge(cityRate);
            em.getTransaction().commit();
            return true;
        }
        catch(Exception ex)
        {
            return false;
        }
    }

 }
