package road.movementparser.injectable;

import java.io.File;
import java.util.ArrayList;
import java.util.List;
import javax.inject.Inject;
import javax.xml.bind.JAXBElement;
import java.io.Serializable;
import java.util.Calendar;
import java.util.logging.Level;
import java.util.logging.Logger;
import road.movemententities.entities.*;
import road.movemententityaccess.dao.EntityDAO;
import road.movementmapper.util.HttpClient;
import sumo.movements.jaxb.SumoNetstateType;

/**
 * The MovementParser reads movements and updates the database.
 */
public class MovementParser {
    /* Package name of generated movement classes */
    private static final String SUMOMOVEMENTSJAXBPACKAGE = "sumo.movements.jaxb";

    @Inject
    private EntityDAO entityDAO;
    
    @Inject
    private GenericParser genericParser;
    
    private int numberOfMovementParses = 0;

    /**
     * Parse movements from a file on date insertDate
     * @param changes 
     */
    public void parseChanges(File changes, Calendar insertDate) {        
        try {
            long startTime = System.nanoTime();
//            HttpClient httpClient = new HttpClient();
//            httpClient.sendPost(changes, insertDate);
            @SuppressWarnings("unchecked")
            JAXBElement<SumoNetstateType> root = (JAXBElement<SumoNetstateType>) genericParser.parse(changes, SUMOMOVEMENTSJAXBPACKAGE);
            parseTimesteps(root, insertDate);
            
            System.out.println("Parsed " + changes.getName() + " in " + (System.nanoTime() - startTime) + "ns");
        } catch (Exception ex) {
            Logger.getLogger(MovementParser.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    /**
     * Parse movements from a string
     * @param changes 
     */
    public void parseChanges(String changes, int sequencenr) {
        if(numberOfMovementParses != sequencenr)
            throw new IllegalArgumentException("Tried to add Movements with serial number "+sequencenr+", but counter was at "+numberOfMovementParses);
        numberOfMovementParses++;
        
        long startTime = System.nanoTime();
        @SuppressWarnings("unchecked")
        JAXBElement<SumoNetstateType> root = (JAXBElement<SumoNetstateType>) genericParser.parse(changes, SUMOMOVEMENTSJAXBPACKAGE);
        parseTimesteps(root, Calendar.getInstance());
        System.out.println("Parsed changes in " + (System.nanoTime() - startTime) + "ns");
    }
    
    /**
     * Parse all timesteps and all vehicle movements within them.
     */
    public void parseTimesteps(JAXBElement<SumoNetstateType> root, Calendar date) {
        if (root == null) {
            throw new IllegalArgumentException();
        }

        // foreach movement
        for (sumo.movements.jaxb.TimestepType timestep : root.getValue().getTimestep()) {
            for (Serializable a : timestep.getContent()) {
                if(a instanceof String) continue;
                sumo.movements.jaxb.EdgeType xmlEdge = (sumo.movements.jaxb.EdgeType) ((JAXBElement) a).getValue();

                for (sumo.movements.jaxb.LaneType xmlLane : xmlEdge.getLane()) {
                    Lane lane = (Lane) entityDAO.findById(Lane.class, xmlLane.getId());
                    // set datetime and timestep time of movement to now.
                    Movement movement = new Movement(date, timestep.getTime(), lane);
                    entityDAO.create(movement);
                    
                    List<VehicleMovement> movementVehicles = new ArrayList();

                    for (Serializable b : xmlLane.getContent()) {
                        if(b instanceof String) continue;
                        sumo.movements.jaxb.VehicleType xmlVehicle = (sumo.movements.jaxb.VehicleType) ((JAXBElement) b).getValue();
                        String licenseplate = xmlVehicle.getId();
                        
                        // Get vehicle by its license plate (<vehicle id="license">) or create a vehicle
                        Vehicle vehicle = (Vehicle) entityDAO.findById(Vehicle.class, licenseplate);
                        if (vehicle == null) {
                            vehicle = new Vehicle(licenseplate);
                            entityDAO.create(vehicle);
                        }
                        
                        VehicleMovement movementVehicle = new VehicleMovement(
                                movement, vehicle, xmlVehicle.getPos(), xmlVehicle.getSpeed());
                        entityDAO.create(movementVehicle);
                        vehicle.addVehicleMovement(movementVehicle);
                        movementVehicles.add(movementVehicle);
                        entityDAO.edit(vehicle);
                    }

                    // Add all moving vehicles to the movement
                    movement.setVehicleMovements(movementVehicles);
                    entityDAO.edit(movement);
                }
            }
        }// end foreach movement
    }
}
