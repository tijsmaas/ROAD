package road.movementparser.daos;

import road.movemententities.entities.Lane;
import road.movemententities.entities.Vehicle;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Query;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/**
 * Created by geh on 24-5-14.
 */
public class ParserDAOImpl
{
    private EntityManager em;
    private ConcurrentHashMap<String, Lane> lanes;

    public ParserDAOImpl(EntityManagerFactory emf)
    {
        this.em = emf.createEntityManager();
        this.lanes = new ConcurrentHashMap<>();
        this.updateLanes();
    }

    public void updateLanes()
    {
        this.lanes.clear();
        Query allLanes = this.em.createQuery("select lane from Lane lane");
        for(Object obj : allLanes.getResultList())
        {
            Lane lane = (Lane)obj;
            this.lanes.put(lane.getId(), lane);
        }
    }

    public Map<String, Lane> getLanes()
    {
        return this.lanes;
    }

    public Lane getLane(String id)
    {
        return this.lanes.get(id);
    }

    public Vehicle getVehicle(String licensePlate)
    {
        Query query = em.createQuery("Select vehicle from Vehicle vehicle where vehicle.licensePlate = :licensePlate");
        query.setParameter("licensePlate", licensePlate);

        List<Vehicle> resultList = query.getResultList();
        return resultList.isEmpty() ? null : resultList.get(0);
    }

    public Vehicle getVehicle(int id)
    {
        return this.em.find(Vehicle.class, id);
    }

    public void addVehicle(Vehicle vehicle)
    {
        this.em.persist(vehicle);
    }
}
