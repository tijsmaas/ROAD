package road.movemententityaccess.helper;

import road.movemententities.entities.*;

import javax.persistence.EntityManager;
import javax.persistence.Query;
import java.math.BigDecimal;
import java.math.MathContext;
import java.util.*;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * Class for generating invoices on the given
 *
 * Created by Niek on 17/05/14.
 * © Aidas 2014
 */
public class InvoiceGenerator
{
    private List<VehicleMovement> monthlyMovements;
    private EntityManager em;

    private Date generationDate;
    private Date startDate;
    private Date endDate;
    private Logger logger;

    private HashMap<String, HashMap<VehicleOwnership, List<VehicleMovement>>> userCarsAndMovements;

    private Map<Integer, Invoice> userInvoices = new HashMap<>();


    public InvoiceGenerator(List<VehicleMovement> monthlyMovements, EntityManager em, Date startDate, Date endDate)
    {
        this.monthlyMovements = monthlyMovements;
        this.em = em;
        this.startDate = startDate;
        this.endDate = endDate;
        this.generationDate = new Date();
        this.logger = Logger.getLogger(this.getClass().getName());

    }

    /**
     * Start the generation of invoices
     */
    public void generate()
    {
        logger.log(Level.INFO, "Starting invoice generation");
        try
        {
            Map<VehicleOwnership, List<VehicleMovement>> vehicleOwnershipListMap = this.getVehicleOwnershipMovements(this.monthlyMovements);

            for (Map.Entry<VehicleOwnership, List<VehicleMovement>> mapEntry : vehicleOwnershipListMap.entrySet())
            {
                this.createOrUpdateInvoice(mapEntry.getKey(), mapEntry.getValue());
            }
        } catch(Exception ex){
            ex.printStackTrace();
        }
    }

    /**
     * Couple the vehicleMovements to car ownerships
     * @param monthlyMovements List of monthlymovements
     * @return A map containing a vehicle ownership and it's movements
     */
    private Map<VehicleOwnership, List<VehicleMovement>> getVehicleOwnershipMovements(List<VehicleMovement> monthlyMovements)
    {
        Map<VehicleOwnership, List<VehicleMovement>> vehicleOwnershipMovements = new HashMap<>();

        for (VehicleMovement vehicleMovement : this.monthlyMovements)
        {
            //Check if the map already contains the current ownership, if so add to that
            if (vehicleOwnershipMovements.containsKey(vehicleMovement.getVehicleOwnership()))
            {
                List<VehicleMovement> ownerMovements = vehicleOwnershipMovements.get(vehicleMovement.getVehicleOwnership());
                ownerMovements.add(vehicleMovement);

                vehicleMovement.getMovement();
            } else
            {
                //If the ownership doesn't exist, create a new one.
                List<VehicleMovement> vehicleMovements = new ArrayList<>();
                vehicleMovements.add(vehicleMovement);

                vehicleOwnershipMovements.put(vehicleMovement.getVehicleOwnership(), vehicleMovements);
            }
        }

        return vehicleOwnershipMovements;
    }

    /**
     * Create a new invoice, or update the existing one if a vehicleOwnership belongs to a user we already created an invoice for
     * @param vehicleOwnership The VehicleOwnership of the current invoice to generate
     * @param vehicleMovements The movemets of the ownership
     */
    private void createOrUpdateInvoice(VehicleOwnership vehicleOwnership, List<VehicleMovement> vehicleMovements)
    {
        VehicleInvoice vehicleInvoice = new VehicleInvoice(vehicleOwnership);
        em.persist(vehicleInvoice);
        Double subTotal = 0.0;
        /**
         * README.
         * TODO: Replace km_rate with city.getKMRate() when km rates work!
         */
        double km_rate = 0.20;
        
        double totalkilometersdriven = 0;
        Lane prevLane = null;
        Invoice invoice = this.getOrCreateInvoice(vehicleOwnership);
        Map<City, CityDistance> cityDistances = new HashMap<>();
        
        for (VehicleMovement vehicleMovement : vehicleMovements)
        {
            // If the vehicle is on an other lane, then the kilometers on the previous lane have been driven.
            Lane currentLane = vehicleMovement.getMovement().getLane();
            if(prevLane != currentLane)
            {
                double kilometersDriven = (prevLane == null) ? 0.0 : prevLane.getLength();

                City city = null;

                // Lookup the city in which the lane is, this can be null
                if(prevLane!= null)
                {
                    city = this.getCityByLane(prevLane);
                } else if(currentLane != null){
                    city = this.getCityByLane(currentLane);
                }
                
                if(city != null) {
                    CityDistance cityDistance = cityDistances.get(city);
                    // add to subtotal
                    totalkilometersdriven += kilometersDriven;
                    subTotal += kilometersDriven * (km_rate);
                    
                    // Check if we have driven here before or not
                    if(cityDistance == null) 
                    {
                        //Add the meters traveled and the km_rate to a new CityDistance object
                        Date movementDate = vehicleMovement.getMovement().getMovementDateTime();
                        cityDistance = new CityDistance(city, kilometersDriven, km_rate, movementDate);
                        cityDistance.setVehicleInvoice(vehicleInvoice);
                        cityDistances.put(city, cityDistance);
                    }else{
                        cityDistance.addDistance(kilometersDriven);
                    }
                }
                
                // We are driving on the next lane now
                prevLane = currentLane;
            }
        }
        
        logger.log(Level.INFO, "User : " + vehicleOwnership.getUserID() + " has driven " + totalkilometersdriven + " meters with vehicle " + vehicleOwnership.getVehicle().getId());

        vehicleInvoice.setMovementList(new ArrayList<CityDistance>(cityDistances.values()));

        //Set the subtotal of the vehicleInvoice to the calculated subtotal
        vehicleInvoice.setSubTotal(new BigDecimal(subTotal, MathContext.DECIMAL64));

        //Set total meters
        double totalMeters = totalkilometersdriven * 1000;
        int totalMetersDriven = (int)Math.round(totalMeters);
        vehicleInvoice.setMetersDriven(totalMetersDriven);

        //Set the invoice to the vehicle invoice
        vehicleInvoice.setInvoice(invoice);

        em.merge(vehicleInvoice);
        invoice.addVehicleInvoie(vehicleInvoice);

        logger.log(Level.INFO, "Added vehicle invoice to invoice with ID " + invoice.getInvoiceID());
        em.merge(invoice);
    }
    
    /**
     * Get the city in which the lane is located.
     * @param lane
     * @return the city
     */
    private City getCityByLane(Lane lane) {
        String laneID = lane.getId();
        String[] laneIDs = laneID.split("\\_");
        laneID = laneIDs[0];
        laneID = laneID.startsWith(":") ? laneID.substring(1) : laneID;

        Query query = em.createQuery("select city from City city where city.cityId like :laneID");
        query.setParameter("laneID", laneID+"%");

        List<City> cities = query.getResultList();
        return cities.size() != 1 ? null : cities.get(0);
    }
    

    /**
     * Creates a new invoice when there's no invoice for the current user, returns the existing invoice for the current user if we already created one
     * @param vehicleOwnership The VehicleOwnership of the current invoice to generate
     * @return the new or existing invoice
     */
    private Invoice getOrCreateInvoice(VehicleOwnership vehicleOwnership)
    {
        if (this.userInvoices.containsKey(vehicleOwnership.getUserID()))
        {
            return this.userInvoices.get(vehicleOwnership.getUserID());
        } else
        {
            Invoice invoice = new Invoice(generationDate, startDate, endDate, vehicleOwnership.getUserID());
            em.persist(invoice);

            this.userInvoices.put(vehicleOwnership.getUserID(), invoice);

            logger.log(Level.INFO, "Creating new invoice for user with ID " + vehicleOwnership.getUserID());
            return invoice;
        }
    }

    public Collection<Invoice> getInvoices(){
        return this.userInvoices.values();
    }

}
