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

        List<CityDistance> cityDistances = new ArrayList<>();

        Double subTotal = 0.0;

        //TODO: DO NOT HARDCODE KM_RATE
        Double km_rate = 0.20;

        Invoice invoice = this.getOrCreateInvoice(vehicleOwnership);

        int metersDriven = 0;
        for (VehicleMovement vehicleMovement : vehicleMovements)
        {
            City from = vehicleMovement.getMovement().getLane().getEdge().getFrom();
            City to = vehicleMovement.getMovement().getLane().getEdge().getTo();

            //TODO: Calculate the average rate based on BOTH cities
            //TODO: vehicleMovement.getPosition() doesn't translate to meters.
            double meters = vehicleMovement.getPosition();

            //Add the meters traveled and the km_rate to a new CityDistance object
            CityDistance cityDistance = new CityDistance(to, meters, km_rate);

            em.merge(cityDistance);

            cityDistances.add(new CityDistance(to, meters, km_rate));

            //Calculate the extra cost
            double addRange = meters * (km_rate / 10);

            //Add the extra cost to the subtotal
            subTotal += addRange;

            //keep a count of the meters driven (for debugging purposes)
            metersDriven += meters;
        }

        logger.log(Level.INFO, "User : " + vehicleOwnership.getUserID() + " has driven " + metersDriven + " meters with vehicle " + vehicleOwnership.getVehicle().getId());

        vehicleInvoice.setMovementList(cityDistances);

        //Set the subtotal of the vehicleInvoice to the calculated subtotal
        vehicleInvoice.setSubTotal(new BigDecimal(subTotal, MathContext.DECIMAL64));

        vehicleInvoice.setMetersDriven(metersDriven);

        //Set the invoice to the vehicle invoice
        vehicleInvoice.setInvoice(invoice);

        em.persist(vehicleInvoice);
        invoice.addVehicleInvoie(vehicleInvoice);

        logger.log(Level.INFO, "Added vehicle invoice to invoice with ID " + invoice.getInvoiceID());
            em.merge(invoice);
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
