package road.movemententityaccess.helper;

import road.movemententities.entities.*;

import javax.persistence.EntityManager;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by Niek on 17/05/14.
 * © Aidas 2014
 */
public class InvoiceGenerator
{
    private List<VehicleMovement> monthlyMovements;
    private EntityManager em;

    private HashMap<String, HashMap<VehicleOwnership, List<VehicleMovement>>> userCarsAndMovements;


    public InvoiceGenerator(List<VehicleMovement> monthlyMovements, EntityManager em)
    {
        this.monthlyMovements = monthlyMovements;
        this.em = em;

    }

    public void generate()
    {
        Map<VehicleOwnership, List<VehicleMovement>> vehicleOwnershipListMap = this.getVehicleOwnershipMovements(this.monthlyMovements);


        Map<Integer, Map<VehicleOwnership, List<VehicleMovement>>> userMovements = scrambleOwnersForOwnerships(vehicleOwnershipListMap);

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

    private VehicleInvoice calculateVehicleInvoice(VehicleOwnership vehicleOwnership, List<VehicleMovement> vehicleMovements)
    {
        VehicleInvoice vehicleInvoice = new VehicleInvoice(vehicleOwnership);

        List<CityDistance> cityDistances = new ArrayList<>();
        BigDecimal subTotal = new BigDecimal("0");

        for (VehicleMovement vehicleMovement :vehicleMovements)
        {
            City from = vehicleMovement.getMovement().getLane().getEdge().getFrom();
            City to = vehicleMovement.getMovement().getLane().getEdge().getTo();

            //TODO: Calculate the rate
        }


        //TODO: Return actual value
        return null;

    }



    private Map<Integer, Map<VehicleOwnership, List<VehicleMovement>>> scrambleOwnersForOwnerships(Map<VehicleOwnership, List<VehicleMovement>> vehicleOwnershipMovements)
    {
        Map<Integer, Map<VehicleOwnership, List<VehicleMovement>>> userMovements = new HashMap<>();

        for (Map.Entry<VehicleOwnership, List<VehicleMovement>> vehicleOwnershipListEntry : vehicleOwnershipMovements.entrySet())
        {
            if (userMovements.containsKey(vehicleOwnershipListEntry.getKey().getUserID()))
            {
                Map<VehicleOwnership, List<VehicleMovement>> existingMap = userMovements.get(vehicleOwnershipListEntry.getKey().getUserID());

                existingMap.put(vehicleOwnershipListEntry.getKey(), vehicleOwnershipListEntry.getValue());
            } else
            {
                HashMap<VehicleOwnership, List<VehicleMovement>> mapToAdd = new HashMap<>();
                mapToAdd.put(vehicleOwnershipListEntry.getKey(), vehicleOwnershipListEntry.getValue());

                userMovements.put(vehicleOwnershipListEntry.getKey().getUserID(), mapToAdd);
            }
        }

        //TODO: Return actual value
        return null;
    }

}
