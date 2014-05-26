package road.movemententityaccess.helper;

import road.movemententities.entities.*;

import javax.persistence.EntityManager;
import java.math.BigDecimal;
import java.math.MathContext;
import java.util.*;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * Class for generating invoices on the given
 * <p/>
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

    private Map<MovementUser, Invoice> userInvoices = new HashMap<>();

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
    public List<Invoice> generate()
    {
        List<Invoice> invoices = new ArrayList<Invoice>();
        logger.log(Level.INFO, "Starting invoice generation");
        try
        {
            Map<VehicleOwnership, List<VehicleMovement>> vehicleOwnershipListMap = this.getVehicleOwnershipMovements(this.monthlyMovements);

            for (Map.Entry<VehicleOwnership, List<VehicleMovement>> mapEntry : vehicleOwnershipListMap.entrySet())
            {
                invoices.add(this.createOrUpdateInvoice(mapEntry.getKey(), mapEntry.getValue()));
            }
        } catch (Exception ex)
        {
            ex.printStackTrace();
        } finally
        {
            return invoices;
        }
    }

    /**
     * Couple the vehicleMovements to car ownerships
     *
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
     *
     * @param vehicleOwnership The VehicleOwnership of the current invoice to generate
     * @param vehicleMovements The movemets of the ownership
     */
    private Invoice createOrUpdateInvoice(VehicleOwnership vehicleOwnership, List<VehicleMovement> vehicleMovements)
    {
        VehicleInvoice vehicleInvoice = new VehicleInvoice(vehicleOwnership);
        em.persist(vehicleInvoice);

        Double subTotal = 0.0;

        /**
         * README.
         * TODO: Replace km_rate with city.getKMRate() when km rates work!
         */

        double totalkilometersdriven = 0;
        Lane prevLane = null;
        Invoice invoice = this.getOrCreateInvoice(vehicleOwnership);
        Map<City, CityDistance> cityDistances = new HashMap<>();

        City prevFriendlyCity = null;
        for (VehicleMovement vehicleMovement : vehicleMovements)
        {
            Lane currentLane = vehicleMovement.getMovement().getLane();
            if (prevLane != currentLane)
            {
                double metersDriven = (prevLane == null) ? 0.0 : prevLane.getLength();

                City drivenCity = null;
                if (prevLane != null)
                {
                    drivenCity = this.getCityByLane(prevLane);
                } else
                {
                    drivenCity = this.getCityByLane(currentLane);
                }

                if(drivenCity!= null && drivenCity.getCityName().startsWith("NA") && prevFriendlyCity != null){
                    drivenCity = prevFriendlyCity;
                }

                if (drivenCity != null)
                {
                    double km_rate = 0.20;
                    CityRate currentRate = drivenCity.getCurrentRate();
                    if (currentRate != null)
                    {
                        km_rate = currentRate.getKilometerRate();
                    }

                    double kilometersDriven = metersDriven / 1000;
                    if(prevLane != null)
                    {
                        logger.log(Level.INFO, "user :" + vehicleOwnership.getUser().getUsername() + " has driven " + kilometersDriven + " on lane " + prevLane.getId());
                    }

                    subTotal += kilometersDriven * km_rate;
                    totalkilometersdriven += kilometersDriven;

                    CityDistance cityDistance = cityDistances.get(drivenCity);

                    if (cityDistance == null)
                    {
                        //Add the meters traveled and the km_rate to a new CityDistance object
                        Date movementDate = vehicleMovement.getMovement().getMovementDateTime();
                        cityDistance = new CityDistance(drivenCity, metersDriven, km_rate, movementDate);
                        cityDistance.setVehicleInvoice(vehicleInvoice);
                        cityDistances.put(drivenCity, cityDistance);
                    } else
                    {
                        cityDistance.addDistance(metersDriven);
                    }

                }


                prevLane = currentLane;

                if(drivenCity != null && !drivenCity.getCityName().startsWith("NA"))
                {
                    prevFriendlyCity = drivenCity;
                }
            }
        }


        logger.log(Level.INFO, "MovementUser : " + vehicleOwnership.getUser().getUsername()

                        + " has driven " + totalkilometersdriven + " meters with vehicle " + vehicleOwnership.getVehicle().

                        getId()
        );

        vehicleInvoice.setMovementList(new ArrayList<CityDistance>(cityDistances.values()));

        //Set the subtotal of the vehicleInvoice to the calculated subtotal
        vehicleInvoice.setSubTotal(new

                        BigDecimal(subTotal, MathContext.DECIMAL64)
        );

        //Set total meters
        double totalMeters = totalkilometersdriven * 1000;
        int totalMetersDriven = (int) Math.round(totalMeters);
        vehicleInvoice.setMetersDriven(totalMetersDriven);

        //Set the invoice to the vehicle invoice
        vehicleInvoice.setInvoice(invoice);

        em.merge(vehicleInvoice);
        invoice.addVehicleInvoie(vehicleInvoice);

        logger.log(Level.INFO, "Added vehicle invoice to invoice with ID " + invoice.getInvoiceID());
        em.merge(invoice);

        return invoice;
    }

    /**
     * Get the city in which the lane is located.
     *
     * @param lane
     * @return the city
     */
    private City getCityByLane(Lane lane)
    {
        return lane.getEdge().getFrom();
    }


    /**
     * Creates a new invoice when there's no invoice for the current user, returns the existing invoice for the current user if we already created one
     *
     * @param vehicleOwnership The VehicleOwnership of the current invoice to generate
     * @return the new or existing invoice
     */
    private Invoice getOrCreateInvoice(VehicleOwnership vehicleOwnership)
    {
        if (this.userInvoices.containsKey(vehicleOwnership.getUser()))
        {
            return this.userInvoices.get(vehicleOwnership.getUser());
        } else
        {
            Invoice invoice = new Invoice(generationDate, startDate, endDate, vehicleOwnership.getUser());
            em.persist(invoice);

            this.userInvoices.put(vehicleOwnership.getUser(), invoice);

            logger.log(Level.INFO, "Creating new invoice for user with ID " + vehicleOwnership.getUser().getUsername());
            return invoice;
        }
    }

    public Collection<Invoice> getInvoices()
    {
        return this.userInvoices.values();
    }
}
