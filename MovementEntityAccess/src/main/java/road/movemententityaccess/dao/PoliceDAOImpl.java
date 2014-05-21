package road.movemententityaccess.dao;

import road.movemententities.entities.Vehicle;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Query;
import java.util.List;

/**
 * Created by Niek on 28/03/14.
 * © Aidas 2014
 */
public class PoliceDAOImpl implements PoliceDAO
{
    private EntityManager em;

    public PoliceDAOImpl(EntityManagerFactory emf)
    {
        this.em = emf.createEntityManager();
    }


    /**
     * {@inheritDoc}
     * @param licensePlate The license plate number of the vehicle
     */
    @Override
    public Vehicle findVehicleByLicensePlate(String licensePlate)
    {
        Query query = em.createQuery("Select vehicle from Vehicle vehicle where vehicle.licensePlate = :licensePlate and vehicle.isStolen = true");
        query.setParameter("licensePlate", licensePlate);

        List<Vehicle> resultList = query.getResultList();
        return resultList.isEmpty() ? null : resultList.get(0);    
    }
    
    /**
     * {@inheritDoc}
     * @param cartrackerId The cartracker id of the vehicle
     */
    @Override
    public Vehicle findVehicleByCartracker(String cartrackerId)
    {
        Query query = em.createQuery("Select vehicle from Vehicle vehicle where vehicle.carTrackerID = :cartrackerId and vehicle.isStolen = true");
        query.setParameter("cartrackerId", cartrackerId);

        List<Vehicle> resultList = query.getResultList();
        return resultList.isEmpty() ? null : resultList.get(0);    
    }

    @Override
    public List<Vehicle> findAllVehicles()
    {
        Query query = em.createQuery("Select vehicle from Vehicle vehicle where vehicle.isStolen = true");

        List<Vehicle> resultList = query.getResultList();
        return resultList;
    }

    @Override
    public boolean setStolen(Vehicle vehicle)
    {
        try
        {
            em.merge(vehicle);
            return true;
        }
        catch(Exception ex)
        {
            return false;
        }
    }
}
