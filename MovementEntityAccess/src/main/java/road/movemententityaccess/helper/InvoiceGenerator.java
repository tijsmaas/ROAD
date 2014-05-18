package road.movemententityaccess.helper;

import road.movemententities.entities.*;

import javax.persistence.EntityManager;
import java.math.BigDecimal;
import java.math.MathContext;
import java.util.*;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
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

    public void generate()
    {
        System.out.println("starting generation");
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

    private Map<VehicleOwnership, List<VehicleMovement>> getVehicleOwnershipMovements(List<VehicleMovement> monthlyMovements)
    {
        Map<VehicleOwnership, List<VehicleMovement>> vehicleOwnershipMovements = new HashMap<>();
        for (VehicleMovement vehicleMovement : this.monthlyMovements)
        {
            if (vehicleOwnershipMovements.containsKey(vehicleMovement.getVehicleOwnership()))
            {
                List<VehicleMovement> ownerMovements = vehicleOwnershipMovements.get(vehicleMovement.getVehicleOwnership());
                ownerMovements.add(vehicleMovement);

                vehicleMovement.getMovement();
            } else
            {
                List<VehicleMovement> vehicleMovements = new ArrayList<>();
                vehicleMovements.add(vehicleMovement);

                vehicleOwnershipMovements.put(vehicleMovement.getVehicleOwnership(), vehicleMovements);
            }
        }

        return vehicleOwnershipMovements;
    }

    private void createOrUpdateInvoice(VehicleOwnership vehicleOwnership, List<VehicleMovement> vehicleMovements)
    {
        VehicleInvoice vehicleInvoice = new VehicleInvoice(vehicleOwnership);

        List<CityDistance> cityDistances = new ArrayList<>();

        Double subTotal = 0.0;
        Double km_rate = 0.20;

        Invoice invoice = this.getOrCreateInvoice(vehicleOwnership);

        for (VehicleMovement vehicleMovement : vehicleMovements)
        {
            City from = vehicleMovement.getMovement().getLane().getEdge().getFrom();
            City to = vehicleMovement.getMovement().getLane().getEdge().getTo();

            //TODO: Calculate the average rate
            double meters = vehicleMovement.getPosition();
            CityDistance cityDistance = new CityDistance(to, meters, km_rate);

            logger.log(Level.INFO, "Adding new CityDistance");
            em.merge(cityDistance);

            cityDistances.add(new CityDistance(to, meters, km_rate));

            double addRange = meters * (km_rate / 10);
            subTotal += addRange;
        }

        vehicleInvoice.setMovementList(cityDistances);
        vehicleInvoice.setSubTotal(new BigDecimal(subTotal, MathContext.DECIMAL64));
        vehicleInvoice.setInvoice(invoice);

        em.persist(vehicleInvoice);
        invoice.addVehicleInvoie(vehicleInvoice);

        logger.log(Level.INFO, "Added vehicle invoice to invoice with ID " + invoice.getInvoiceID());
            em.merge(invoice);
        }

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
