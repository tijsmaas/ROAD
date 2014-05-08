package parser.dao;

import road.movemententities.entities.MovementEntity;

public interface EntityDAO {
    
    /**
     * Count number of objects in the database.
     * @param objecttype What type of objects to count.
     * @return # of objects.
     */
    int count(Class objecttype);
    
    /**
     * Persists an entity to the database
     * @param object The entity object to persist
     */
    void create(MovementEntity object);

    /**
     * Merges the changes of a modified entity
     * @param object The entity to update
     * @return The JPA linked object.
     */
    Object edit(MovementEntity object);

    /**
     * Removes an object from the database
     * @param object
     */
    void remove(MovementEntity object);
    
    /**
     * Find an object by its primary key.
     * @param objecttype The (entity) class of the object.
     * @param object The primary key of the object that should be searched for.
     * @return The found and JPA linked object, or null.
     */
    Object findById(Class objecttype, Object object);
    
    /**
     * Save (data of) an object, no matter if it existed or not.
     * @param type The (entity) class of the object.
     * @param object The object that should be stored in the database.
     * @return the JPA linked object.
     */
    Object createOrEdit(Class type, MovementEntity object);
}
