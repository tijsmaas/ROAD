package road.movementservice.events;

import road.movemententities.entities.Movement;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Geert on 21/05/2014.
 */
public class JamEvent {
    /**
     * The list containing all listeners to the event.
     */
    private static List<IJamEventListener> listeners = new ArrayList();

    /**
     * Subscribe to the traffic jam events.
     * @param listener the listener to subscribe.
     */
    public static void subscribe(IJamEventListener listener)
    {
        JamEvent.listeners.add(listener);
    }

    /**
     * Unsubscribe to the traffic jam events.
     * @param listener the listener to unsubscribe.
     */
    public static void unsubscribe(IJamEventListener listener)
    {
        JamEvent.listeners.remove(listener);
    }

    /**
     * Fire a new traffic jam event.
     * @param movements the movments to send to the listeners.
     */
    public static void fire(List<Movement> movements)
    {
        for (IJamEventListener listener : JamEvent.listeners)
        {
            listener.receive(movements);
        }
    }
}
