package entities;

/**
 * Each entity should implement this interface.
 * The EntityDAO uses this to search for entities.
 * @author tijs
 * @param <TYPE> 
 */
public interface MovementEntity<TYPE> {
    /**
     * @return JPA @Id field
     */
    TYPE getId();
}
