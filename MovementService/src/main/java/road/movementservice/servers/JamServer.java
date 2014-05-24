package road.movementservice.servers;

import road.movementdtos.dtos.LaneDto;
import road.movementdtos.dtos.MovementDto;
import road.movementdtos.dtos.VehicleMovementDto;
import road.movementdts.connections.MovementConnection;
import road.movemententities.entities.Movement;
import road.movemententities.entities.VehicleMovement;
import road.movemententities.entities.VehicleOwnership;
import road.movementservice.connections.TopicServer;
import road.movementservice.events.IMovementEventListener;
import road.movementservice.events.MovementEvent;
import road.movementservice.mapper.DtoMapper;

import java.util.ArrayList;
import java.util.List;

//import javax.inject.Inject;

/**
 * Created by geh on 23-4-14.
 */
public class JamServer extends TopicServer implements IMovementEventListener
{
    private DtoMapper dtoMapper;

    public JamServer(DtoMapper dtoMapper)
    {
        super(MovementConnection.FactoryName, MovementConnection.JamTopic);

        this.dtoMapper = dtoMapper;

        MovementEvent.subscribe(this);
    }

    public void init()
    {
        this.start();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void receive(List<Movement> movements)
    {
        List<MovementDto> mappedMovements = new ArrayList();
        for (Movement movement : movements)
        {
            List<VehicleMovementDto> vehicleMovementsUsedInCalc = new ArrayList();

            // Check for each vehicle movement if the vehicle can be used for calculations and the speed is slower than 50 km/h.
            for (VehicleMovement vehicleMovement : movement.getVehicleMovements()) {
                VehicleOwnership owner = vehicleMovement.getVehicleOwnership();

                // Change the speed from m/s to km/h.
                double speedInKmPerHour = vehicleMovement.getSpeed() * 3.6;

                if ((owner == null || owner.getContributeGPSData()) && speedInKmPerHour < 50.0)
                {
                    vehicleMovementsUsedInCalc.add(this.dtoMapper.map(vehicleMovement));
                }
            }

            if (!vehicleMovementsUsedInCalc.isEmpty())
            {
                LaneDto laneDto = this.dtoMapper.map(movement.getLane());
                MovementDto movementDto = new MovementDto(laneDto, vehicleMovementsUsedInCalc);

                mappedMovements.add(movementDto);
            }
        }

        this.send(mappedMovements);
    }
}
