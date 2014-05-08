package movementParser;

import java.util.ArrayList;
import java.util.List;
import javax.inject.Inject;
import javax.xml.bind.JAXBElement;
import java.io.Serializable;
import java.util.Calendar;
import entities.*;
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
                if (!(a instanceof JAXBElement)) {
                    continue;
                }
                sumo.movements.jaxb.EdgeType xmlEdge = (sumo.movements.jaxb.EdgeType) ((JAXBElement) a).getValue();

                // set datetime and timestep time of movement to now.
                Movement movement = new Movement(Calendar.getInstance(), timestep.getTime());
                // find the edge of the movement on our map.
                Edge edge = (Edge) entityDAO.findById(Edge.class, xmlEdge.getId());
                movement.setEdge(edge);
                entityDAO.create(movement);

                for (sumo.movements.jaxb.LaneType xmlLane : xmlEdge.getLane()) {
                    Lane lane = (Lane) entityDAO.findById(Lane.class, xmlLane.getId());
                    movement.setLane(lane);

                    List<MovementVehicle> movementVehicles = new ArrayList();

                    for (Serializable b : xmlLane.getContent()) {
                        if (!(b instanceof JAXBElement)) {
                            continue;
                        }

                        sumo.movements.jaxb.VehicleType xmlVehicle = (sumo.movements.jaxb.VehicleType) ((JAXBElement) b).getValue();
                        String licenseplate = xmlVehicle.getId();
                        MovementVehicle movementVehicle = new MovementVehicle(
                                movement, licenseplate + "_" + timestep.getTime(), xmlVehicle.getPos(), xmlVehicle.getSpeed());

                        // Get vehicle by its license plate (<vehicle id="license">) or create a vehicle
                        Vehicle vehicle = (Vehicle) entityDAO.findById(Vehicle.class, licenseplate);
                        if (vehicle == null) {
                            vehicle = new Vehicle(licenseplate);
                            entityDAO.create(vehicle);
                        }
                        movementVehicle.setVehicle(vehicle);

                        entityDAO.edit(movementVehicle);
                        System.out.println("Vehicle " + movementVehicle.getMovementIdentifier());
                        movementVehicles.add(movementVehicle);
                    }

                    // Add all moving vehicles to the movement
                    movement.setMovementVehicles(movementVehicles);
                    entityDAO.edit(movement);
                }
            }
        }// end foreach movement
    }
}
