package road.movemententityaccess.dao;

import road.movementdtos.dtos.CityDto;
import road.movemententities.converters.CityConverter;
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
    public CityDto find(String cityIdentifier)
    {
        Query query = em.createQuery("select city from City city where city.cityIdentifier = :cityID");
        query.setParameter("cityID", cityIdentifier);

        List<City> resultList = query.getResultList();
        return resultList.isEmpty() ? null : CityConverter.toCityDto(resultList.get(0));
    }

    /**
     * {@inheritDoc}
     * @param cityIdentifier The sumo City Identifier
     * @return the found City object.
     */
    public City get(String cityIdentifier)
    {
        Query query = em.createQuery("select city from City city where city.cityIdentifier = :cityID");
        query.setParameter("cityID", cityIdentifier);

        List<City> resultList = query.getResultList();
        return resultList.isEmpty() ? null : resultList.get(0);
    }

    /**
     * @return the found City object.
     */
    @Override
    public List<CityDto> findAll()
    {
        Query query = em.createQuery("select city from City city");

        List<City> resultList = query.getResultList();
        List<CityDto> returnList = new ArrayList();
        for (City c : resultList) {
            returnList.add(CityConverter.toCityDto(c));
        }
        return returnList;
    }

    @Override
    public boolean adjustKilometerRate(CityDto city, Date addDate, String price)
    {
        try
        {
            em.persist(new CityRate(get(city.getCityId()), addDate, price));
            return true;
        }
        catch(Exception ex)
        {
            return false;
        }
    }

 }
