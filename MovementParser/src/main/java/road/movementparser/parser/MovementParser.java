package road.movementparser.parser;

import java.util.ArrayList;
import java.util.GregorianCalendar;
import java.util.List;
import javax.xml.bind.JAXBElement;
import java.io.Serializable;
import java.util.Calendar;

import road.movemententities.entities.*;
import road.movemententityaccess.dao.EntityDAO;
import sumo.movements.jaxb.*;

/**
 * The road.movementparser.parser.MovementParser reads movements and updates the database.
 * And other things
 */
/**
 * The MovementParser reads movements and updates the database.
 */
public class MovementParser
{
    /* Package name of generated movement classes */
    private static final String SUMOMOVEMENTSJAXBPACKAGE = "sumo.movements.jaxb";

    private EntityDAO entityDAO;
    private GenericParser genericParser;

    private int missed = 0;
    private int numberOfMovementParses = 0;

    public MovementParser(EntityDAO entityDAO, GenericParser genericParser)
    {
        this.entityDAO = entityDAO;
        this.genericParser = genericParser;
    }

    /**
     * Parse movements from a string
     *
     * @param changes
     */
    public List<Movement> parseChanges(String changes, int sequencenr)
    {
        if (numberOfMovementParses != sequencenr)
        {
            System.out.println("Tried to add Movements with serial number " + sequencenr + ", but counter was at " + numberOfMovementParses);
            this.missed++;
        }
        
        this.numberOfMovementParses++;

        long startTime = System.nanoTime();

        @SuppressWarnings("unchecked")
        JAXBElement<SumoNetstateType> root = (JAXBElement<SumoNetstateType>) genericParser.parse(changes, SUMOMOVEMENTSJAXBPACKAGE);

        List<Movement> movements = this.parseTimesteps(root, new GregorianCalendar());
        System.out.println("Parsed changes in " + (System.nanoTime() - startTime) + "ns");

        return movements;
    }

    /**
     * Parsers exactly a timestep and returns a collection of all movements in the Netstate {@link root} variable.
     * @param root Sumo Netstate variable to be parsed, that should contain exactly one timestep.
     * @param date The exact time on which this timestep is received.
     * @return
     */
    public List<Movement> parseTimesteps(JAXBElement<SumoNetstateType> root, Calendar date)
    {
        if (root == null)
        {
            throw new IllegalArgumentException();
        }

        List<Movement> movements = new ArrayList<>();

        /**
         * Parses the Timesteps of the Sumo Netstate {@link root} variable.
         * However, this is a real-time parser, so the root Netstate should contain
         * exactly one Timestep.
          */
        for (TimestepType timestep : root.getValue().getTimestep())
        {
            for (Serializable rawEdge : timestep.getContent())
            {
                if (rawEdge instanceof String)
                {
                    continue;
                }

                EdgeType xmlEdge = (EdgeType)((JAXBElement)rawEdge).getValue();

                for (LaneType xmlLane : xmlEdge.getLane())
                {
                    Lane lane = (Lane)entityDAO.findById(Lane.class, xmlLane.getId());

                    // set datetime and timestep time of movement to now.
                    Movement movement = new Movement(date, timestep.getTime(), lane);
                    entityDAO.create(movement);

                    List<VehicleMovement> movementVehicles = new ArrayList();

                    for (Serializable rawVehicle : xmlLane.getContent())
                    {
                        if (rawVehicle instanceof String)
                        {
                            continue;
                        }

                        VehicleType xmlVehicle = (VehicleType)((JAXBElement)rawVehicle).getValue();
                        String licenseplate = xmlVehicle.getId();

                        // Get vehicle by its license plate (<vehicle id="license">) or create a vehicle
                        Vehicle vehicle = (Vehicle)entityDAO.findById(Vehicle.class, licenseplate);
                        if (vehicle == null)
                        {
                            vehicle = new Vehicle(licenseplate);
                            entityDAO.create(vehicle);
                        }

                        VehicleMovement movementVehicle = new VehicleMovement(movement, vehicle, xmlVehicle.getPos(), xmlVehicle.getSpeed());
                        entityDAO.create(movementVehicle);
                        vehicle.addVehicleMovement(movementVehicle);
                        movementVehicles.add(movementVehicle);
                        entityDAO.edit(vehicle);
                    }

                    // Add all moving vehicles to the movement
                    movement.setVehicleMovements(movementVehicles);
                    entityDAO.edit(movement);
                    movements.add(movement);
                }
            }
        }// end foreach movement

        return movements;
    }
}
