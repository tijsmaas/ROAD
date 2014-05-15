package road.movementmapper.dao;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.URL;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.Iterator;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.annotation.PostConstruct;
import javax.ejb.Singleton;
import javax.enterprise.context.Dependent;
import javax.inject.Named;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import road.movemententities.entities.Lane;
import road.movemententities.entities.Vehicle;
import road.movemententities.entities.VehicleOwnership;

/**
 * MovementsDAOImpl is needed by the movements parser. This class caches all
 * lanes and vehicles, so that we can use it in Batch processing.
 *
 * @author tijs
 */
@Dependent
@Named
@Singleton
public class MovementsDAOImpl implements MovementsDAO
{
    /* File with license plates */
    public static final String LICENSES = "licenses.csv";

    /* USER ID that references to a user */
    public static int USER_ID = 0;
    
    @PersistenceContext(unitName = "MovementPU")
    private EntityManager em;

    /* Local caches for fast lookup */
    private List<Lane> lanes;
    private List<Vehicle> vehicles;
    
    private Iterator<String> itLicensePlates;
    
    public MovementsDAOImpl()
    {
    }

    /**
     * Preload all lanes and vehicles once.
     */
    @PostConstruct
    public void init()
    {
        System.out.println("Initializing MovementsDAOImpl");
        Query laneQuery = em.createQuery("select x from Lane x");
        lanes = laneQuery.getResultList();

        Query vehicleQuery = em.createQuery("select x from Vehicle x");
        vehicles = vehicleQuery.getResultList();
        
        generateLicensePlates(LICENSES);
    }

    /**
     * Get a lane from the cached list.
     *
     * @param laneId
     * @return
     */
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

    
    
    /**
     * Get a vehicle by cartracker id from the cached list, if the vehicle does
     * not exist it will be created.
     *
     * @param cartrackerID
     * @return The found or newly created vehicle.
     */
    @Override
    public Vehicle getOrCreateVehicleById(String cartrackerID)
    {
        Vehicle returnVehicle = null;

        Iterator<Vehicle> it = vehicles.iterator();
        while (it.hasNext())
        {
            Vehicle vehicle = it.next();
            if (cartrackerID.equals(vehicle.getCarTrackerID()))
            {
                returnVehicle = vehicle;
                break;
            }
        }

        if (returnVehicle == null)
        {
            /* Vehicle does not exist, lets create a new one */
            /* Also create a vehicle ownership */
            GregorianCalendar registerdate = new GregorianCalendar();
            registerdate.add(Calendar.YEAR, -3);
            VehicleOwnership vehicleOwnership = new VehicleOwnership();
            vehicleOwnership.setContributeGPSData(true);
            vehicleOwnership.setRegistrationdate(registerdate);
            vehicleOwnership.setRegistrationExperationDate(null);
            // create user by incrementing user id
            vehicleOwnership.setUserID(USER_ID++);
            // create car
            returnVehicle = new Vehicle(cartrackerID);
            returnVehicle.setLicensePlate(itLicensePlates.next());
            List<VehicleOwnership> owners = new ArrayList();
            owners.add(vehicleOwnership);
            returnVehicle.setVehicleOwners(owners);
            vehicles.add(returnVehicle);
            em.persist(returnVehicle);
            
            vehicleOwnership.setVehicle(returnVehicle);
            em.persist(vehicleOwnership);
            
        }

        return returnVehicle;
    }
    
    /**
     * Generate license plates from a file.
     * @param filename 
     */
    private void generateLicensePlates(String filename){
        File licenseplatefile = getResourceFile(filename);
        BufferedReader in = null;
        try
        {
            in = new BufferedReader(new InputStreamReader(new FileInputStream(licenseplatefile)));
        } catch (FileNotFoundException ex)
        {
            Logger.getLogger(MovementsDAOImpl.class.getName()).log(Level.SEVERE, null, ex);
        }
        List<String> licensePlates = new ArrayList();
        String line;
        try
        {
            while((line = in.readLine()) != null) {
                licensePlates.add(line);
            }
        } catch (IOException ex)
        {
            Logger.getLogger(MovementsDAOImpl.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            try
            {
                in.close();
            } catch (IOException ex)
            {
                Logger.getLogger(MovementsDAOImpl.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        itLicensePlates = licensePlates.iterator();     
    }
    
    
    private File getResourceFile(String filename)
    {
        URL url = this.getClass().getResource("/" + filename);
        System.out.println("url: " + url.getPath());
        return new File(url.getFile());
    }
}
