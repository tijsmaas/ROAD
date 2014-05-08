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
public class VehicleDAOImpl implements VehicleDAO
{
    private EntityManager em;

    public VehicleDAOImpl(EntityManagerFactory emf)
    {
        this.em = emf.createEntityManager();
    }


    /**
     * {@inheritDoc}
     * @param licensePlate The license plate number of the vehicle
     */
    @Override
    public Vehicle findByLicensePlate(String licensePlate)
    {
        Query query = em.createQuery("Select vehicle from Vehicle vehicle where vehicle.licensePlate = :licensePlate");
        query.setParameter("licensePlate", licensePlate);

        List<Vehicle> resultList = query.getResultList();
        return resultList.isEmpty() ? null : resultList.get(0);    }

    /**
     * {@inheritDoc}
     * @param userID The ID of the user
     */
    @Override
    public List<Vehicle> getVehiclesFromUser(int userID)
    {
        return null;
    }

    /**
     * {@inheritDoc}
     * @param vehicle The vehicle switching owners
     * @param userID The user ID of the new owner.
     */
    @Override
    public void changeVehicleOwnership(Vehicle vehicle, int userID)
    {

    }
}
