package road.movementmapper.dao;

import road.movemententities.entities.MovementEntity;
import javax.enterprise.context.ApplicationScoped;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

/**
 * Created by Tijs
 */
@ApplicationScoped
public class EntityDAOImpl implements EntityDAO {

    @PersistenceContext(unitName = "MovementPU")
    private EntityManager em;


    @Override
    public int count(Class objecttype) {
        Query q = em.createQuery("select count(x) from "+objecttype.getName()+" x");
        return ((Long) q.getSingleResult()).intValue();
    }
    
    /**
     * {@inheritDoc}
     */
    @Override
    public void create(MovementEntity object) {
        em.persist(object);
    }

    /**
     * {@inheritDoc}
     *
     * @param object The modified object
     * @return The JPA linked object, USE THIS RETURN VALUE FOR FURTHER BINDINGS
     */
    @Override
    public Object edit(MovementEntity object) {
        return em.merge(object);
    }

    /**
     * {@inheritDoc}
     *
     * @param object The object object to remove
     */
    @Override
    public void remove(MovementEntity object) {
        em.remove(object);
    }
        
    /**
     * {@inheritDoc}
     *
     * @param type Entity class type
     * @param id primary key
     * @return the found object, or null if not found
     */
    @Override
    public MovementEntity findById(Class type, Object id) {
        try {
            MovementEntity obj = (MovementEntity) em.find(type, id);
            return obj;
        } catch (Exception iae){
            iae.printStackTrace();
        }
        return null;
    }
    
    /**
     * {@inheritDoc}
     *
     * @param type Entity class type
     * @param object The object to store in the database
     * @return The JPA linked object, USE THIS RETURN VALUE FOR FURTHER BINDINGS
     */
    @Override
    public Object createOrEdit(Class type, MovementEntity object) {
        Object dbobject = em.find(type, object.getId());

        // Create a new object if none exists, otherwise just update its definition
        if (dbobject == null) {
            em.persist(object);
        }
        
        return em.merge(object);
    }
}
