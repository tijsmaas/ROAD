package road.movemententityaccess.dao;

import road.movemententities.entities.Vehicle;
import road.movemententities.entities.VehicleOwnership;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Query;
import javax.persistence.TypedQuery;
import java.util.ArrayList;
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
        return resultList.isEmpty() ? null : resultList.get(0);    
    }
    
    /**
     * {@inheritDoc}
     * @param cartrackerId The cartracker id of the vehicle
     */
    @Override
    public Vehicle findByCartracker(String cartrackerId)
    {
        Query query = em.createQuery("Select vehicle from Vehicle vehicle where vehicle.carTrackerID = :cartrackerId");
        query.setParameter("cartrackerId", cartrackerId);

        List<Vehicle> resultList = query.getResultList();
        return resultList.isEmpty() ? null : resultList.get(0);    
    }

    /**
     * {@inheritDoc}
     * @param userID The ID of the user
     */
    @Override
    public List<Vehicle> getVehiclesFromUser(Integer userID)
    {
        if (userID == null) {
            return new ArrayList();
        }

        TypedQuery query = em.createQuery("SELECT vo.vehicle FROM VehicleOwnership vo WHERE vo.userID = :userId AND vo.registrationExperationDate IS NULL", Vehicle.class);
        query.setParameter("userId", userID);

        return query.getResultList();
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

    /**
     * {@inheritDoc}
     */
    @Override
    public Boolean updateVehicle(String licencePlate, Boolean contributeGPSData) {
        boolean successful = false;

        try {
            Vehicle vehicle = this.findByLicensePlate(licencePlate);
            VehicleOwnership ownership = null;
            for (VehicleOwnership vo : vehicle.getVehicleOwners()) {
                if (vo.getRegistrationExperationDate() == null) {
                    ownership = vo;
                    break;
                }
            }

            if (ownership != null) {
                ownership.setContributeGPSData(contributeGPSData);
            }

            em.merge(vehicle);

            successful = true;
        } catch (Exception ex) {
            ex.printStackTrace();
        }

        return successful;
    }
}
