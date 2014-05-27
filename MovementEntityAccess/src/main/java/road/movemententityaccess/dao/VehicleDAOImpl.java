package road.movemententityaccess.dao;

import road.movemententities.entities.MovementUser;
import road.movemententities.entities.Vehicle;
import road.movemententities.entities.VehicleOwnership;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Query;
import javax.persistence.TypedQuery;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

/**
 * Created by Niek on 28/03/14.
 *  Aidas 2014
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

        TypedQuery query = em.createQuery("SELECT vo.vehicle FROM VehicleOwnership vo WHERE vo.user.id = :userId AND vo.registrationExperationDate IS NULL", Vehicle.class);
        query.setParameter("userId", userID);

        return query.getResultList();
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

    /**
     * {@inheritDoc}
     * @return list of vehicles
     */
    @Override
    public List<Vehicle> getAllVehicles()
    {
        Query query = em.createQuery("select vehicle from Vehicle vehicle");

        return query.getResultList();
    }

    /**
     * {@inheritDoc}
     * @param vehicleID The ID of the vehicle
     * @return
     */
    @Override
    public Vehicle findByID(int vehicleID)
    {
        Query query = em.createQuery("select vehicle From Vehicle vehicle where vehicle.id = :id");
        query.setParameter("id", vehicleID);

        List<Vehicle> resultList = query.getResultList();
        return resultList.size() != 1 ? null : resultList.get(0);
    }

    /**
     * {@inheritDoc}
     * @return list of users
     */
    @Override
    public List<MovementUser> getAllVehicleUsers()
    {
        Query query = em.createQuery("Select mu from movementusers  mu");

        return query.getResultList();
    }

    /**
     * {@inheritDoc}
     * @param movementUserID the ID of the movementUser
     * @return the found MovementUser
     */
    @Override
    public MovementUser findMovementUser(int movementUserID)
    {
        Query query = em.createQuery("select mu from movementusers mu  where mu.id = :id");
        query.setParameter("id", movementUserID);

        List<MovementUser> resultList = query.getResultList();
        return resultList.size() != 1 ? null : resultList.get(0);
    }

    /**
     * {@inheritDoc}
     * @param carTrackerID The CartrackerID of the vehicle
     * @param licensePlate The licenseplate for the new vehicle
     * @param movementUser The MovementUser that will be the initial owner of the vehicle
     * @return newly added vehicle
     */
    @Override
    public Vehicle addNewVehicle(String carTrackerID, String licensePlate, MovementUser movementUser)
    {
        em.getTransaction().begin();
        Vehicle vehicle = new Vehicle(carTrackerID, licensePlate);
        em.persist(vehicle);

        VehicleOwnership firstOwner = new VehicleOwnership(vehicle, movementUser, Calendar.getInstance(), null);
        em.persist(firstOwner);

        vehicle.addVehicleOwner(firstOwner);
        em.merge(vehicle);

        em.getTransaction().commit();

        return vehicle;

    }

    /**
     * {@inheritDoc}
     * @param vehicle The vehicle changing ownerships
     * @param newOwner the new owner of the vehicle
     * @return
     */
    @Override
    public VehicleOwnership changeVehicleOwner(Vehicle vehicle, MovementUser newOwner)
    {
            em.getTransaction().begin();
        VehicleOwnership currentOwnership = vehicle.getCurrentOwner();
        currentOwnership.setRegistrationExperationDate(Calendar.getInstance());
        em.merge(currentOwnership);

        VehicleOwnership newOwnership = new VehicleOwnership(vehicle, newOwner, Calendar.getInstance(), null);
        em.persist(newOwnership);

        vehicle.addVehicleOwner(newOwnership);
        em.merge(vehicle);
        em.getTransaction().commit();

        return newOwnership;

    }
}
