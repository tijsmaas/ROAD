package dao;

import entities.Movement;

import javax.persistence.EntityManager;
import java.util.Date;
import java.util.List;

/**
 * Created by Niek on 28/03/14.
 * © Aidas 2014
 */
public interface MovementDAO
{
    /**
     * Find a movement by ID
     * @param MovementID The ID of the movement
     * @return The found movement
     */
    Movement find(int MovementID);

    /**
     * Get all movements of a certain date
     * @param date movement date
     * @return List of found movements
     */
    List<Movement> getMovementsByDate(Date date);

    void setEntityManager(EntityManager em);
}
