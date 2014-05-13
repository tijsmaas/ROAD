package movementParser;

import java.util.ArrayList;
import java.util.List;
import javax.inject.Inject;
import javax.xml.bind.JAXBElement;
import java.io.Serializable;
import java.util.Calendar;
import road.movemententities.entities.*;
import parser.dao.EntityDAO;
import sumo.movements.jaxb.SumoNetstateType;

/**
 * The MovementParser reads movements and updates the database.
 */
public class MovementParser {

    @Inject
    private EntityDAO entityDAO;

    /**
     * Parse all timesteps and all vehicle movements within them.
     */
    public void parseTimesteps(JAXBElement<SumoNetstateType> root) {
        if (root == null) {
            throw new IllegalArgumentException();
        }

        // foreach movement
        for (sumo.movements.jaxb.TimestepType timestep : root.getValue().getTimestep()) {
            for (Serializable a : timestep.getContent()) {
                sumo.movements.jaxb.EdgeType xmlEdge = (sumo.movements.jaxb.EdgeType) ((JAXBElement) a).getValue();

                for (sumo.movements.jaxb.LaneType xmlLane : xmlEdge.getLane()) {
                    Lane lane = (Lane) entityDAO.findById(Lane.class, xmlLane.getId());
                    // set datetime and timestep time of movement to now.
                    Movement movement = new Movement(Calendar.getInstance(), timestep.getTime(), lane);
                    entityDAO.create(movement);
                    
                    List<VehicleMovement> movementVehicles = new ArrayList();

                    for (Serializable b : xmlLane.getContent()) {
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
