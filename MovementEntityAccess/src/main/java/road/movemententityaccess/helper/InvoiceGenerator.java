package road.movemententityaccess.helper;

import road.movemententities.entities.*;

import javax.persistence.EntityManager;
import java.math.BigDecimal;
import java.math.MathContext;
import java.util.*;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.persistence.Query;

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
        Double subTotal = 0.0;
        /**
         * README.
         * TODO: Replace km_rate with city.getKMRate() when km rates work!
         */
        double km_rate = 20.0;
        
        int totalMetersDriven = 0;
        Lane prevLane = null;
        Invoice invoice = this.getOrCreateInvoice(vehicleOwnership);
        Map<City, CityDistance> cityDistances = new HashMap<>();
        
        for (VehicleMovement vehicleMovement : vehicleMovements)
        {
            // If the vehicle is on an other lane, then the kilometers on the previous lane have been driven.
            Lane currentLane = vehicleMovement.getMovement().getLane();
            if(prevLane != currentLane)
            {
                int metersDriven = (prevLane == null) ? 0 : Math.round(prevLane.getLength());
                totalMetersDriven += metersDriven;
                
                // Lookup the city in which the lane is, this can be null
                City city = this.getCityByLane(prevLane);
                
                if(city != null) {
                    CityDistance cityDistance = cityDistances.get(city);
                    // add to subtotal
                    subTotal += metersDriven * (km_rate / 10);
                    
                    // Check if we have driven here before or not
                    if(cityDistance == null) 
                    {
                        cityDistance = new CityDistance(city, metersDriven, km_rate);
                        cityDistances.put(city, cityDistance);
                        em.merge(cityDistance);
                    }else{
                        //Add the meters traveled and the km_rate to a new CityDistance object
                        cityDistance.addDistance(metersDriven);
                        em.persist(cityDistance);
                    }
                }
                
                // We are driving on the next lane now
                prevLane = currentLane;
            }
        }
        
        logger.log(Level.INFO, "User : " + vehicleOwnership.getUserID() + " has driven " + totalMetersDriven + " meters with vehicle " + vehicleOwnership.getVehicle().getId());

        vehicleInvoice.setMovementList((List<CityDistance>) cityDistances.values());

        //Set the subtotal of the vehicleInvoice to the calculated subtotal
        vehicleInvoice.setSubTotal(new BigDecimal(subTotal, MathContext.DECIMAL64));

        vehicleInvoice.setMetersDriven(totalMetersDriven);

        //Set the invoice to the vehicle invoice
        vehicleInvoice.setInvoice(invoice);

        em.persist(vehicleInvoice);
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
        Query query = em.createQuery("select count(city) from City city");
        List<City> cities = query.getResultList();
        for(City city : cities) 
        {
            /**
             * Check if lane matches city
             * Lane id = ":-23_2_0"
             * City id = "-23"
             */
            if(lane.getId().startsWith(":"+city.getCityId()))
            {
                return city;
            }
        }
        return null;
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
