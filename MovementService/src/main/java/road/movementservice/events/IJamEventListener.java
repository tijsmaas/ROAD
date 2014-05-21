package road.movementservice.events;

import road.movemententities.entities.Movement;

import java.util.List;

/**
 * Created by Geert on 21/05/2014.
 */
public interface IJamEventListener {

    /**
     *
     * @param movements
     */
    public void receive(List<Movement> movements);
}
