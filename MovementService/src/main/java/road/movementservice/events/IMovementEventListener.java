package road.movementservice.events;

import road.movemententities.entities.Movement;

import java.util.List;

/**
 * Created by Geert on 21/05/2014.
 */
public interface IMovementEventListener
{

    /**
     * If the {@link MovementEvent} is fired this function is called if subscribed to the event.
     * @param movements movements the movements to send to the listeners.
     */
    public void receive(List<Movement> movements);
}
