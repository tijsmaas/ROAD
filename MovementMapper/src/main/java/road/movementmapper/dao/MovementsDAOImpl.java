package road.movementmapper.dao;

import java.util.Iterator;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.ejb.Singleton;
import javax.enterprise.context.Dependent;
import javax.inject.Named;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import road.movemententities.entities.Lane;
import road.movemententities.entities.Vehicle;

@Dependent
@Named
@Singleton
public class MovementsDAOImpl implements MovementsDAO
{
    @PersistenceContext(unitName = "MovementPU")
    private EntityManager em;

    /* Local caches for fast lookup */
    private List<Lane> lanes;
    private List<Vehicle> vehicles;

    public MovementsDAOImpl() {}
    
    @PostConstruct
    public void init()
    {
        System.out.println("Initializing MovementsDAOImpl");
        Query laneQuery = em.createQuery("select x from Lane x");
        lanes = laneQuery.getResultList();

        Query vehicleQuery = em.createQuery("select x from Vehicle x");
        vehicles = vehicleQuery.getResultList();
    }

    @Override
    public Lane getLaneById(String laneId)
    {
        Iterator<Lane> it = lanes.iterator();
        while (it.hasNext())
        {
            Lane lane = it.next();
            if (laneId.equals(lane.getId()))
            {
                return lane;
            }
        }
        return null;
    }

    @Override
    public Vehicle getOrCreateVehicleById(String licensePlate)
    {
        Iterator<Vehicle> it = vehicles.iterator();
        while (it.hasNext())
        {
            Vehicle vehicle = it.next();
            if (licensePlate.equals(vehicle.getId()))
            {
                return vehicle;
            }
        }
        
        /* Vehicle does not exist, lets create a new one */
        Vehicle vehicle = new Vehicle(licensePlate);
        vehicles.add(vehicle);
        em.persist(vehicle);
        
        return vehicle;
    }
}
