package road.movementservice.events;

import road.movemententities.entities.Movement;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Geert on 21/05/2014.
 */
public class MovementEvent
{
    /**
     * The list containing all listeners to the event.
     */
    private static List<IMovementEventListener> listeners = new ArrayList();

    /**
     * Subscribe to the traffic jam events.
     * @param listener the listener to subscribe.
     */
    public static void subscribe(IMovementEventListener listener)
    {
        MovementEvent.listeners.add(listener);
    }

    /**
     * Unsubscribe to the traffic jam events.
     * @param listener the listener to unsubscribe.
     */
    public static void unsubscribe(IMovementEventListener listener)
    {
        MovementEvent.listeners.remove(listener);
    }

    /**
     * Fire a new traffic jam event.
     * @param movements movements the movements to send to the listeners.
     */
    public static void fire(List<Movement> movements)
    {
        for (IMovementEventListener listener : MovementEvent.listeners)
        {
            listener.receive(movements);
        }
    }
}
