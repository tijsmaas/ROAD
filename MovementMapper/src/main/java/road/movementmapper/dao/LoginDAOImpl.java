package road.movementmapper.dao;

import road.movemententities.entities.MovementUser;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Query;
import java.util.List;

/**
 * Created by geh on 20-5-14.
 */
public class LoginDAOImpl
{
    private EntityManager em;

    public LoginDAOImpl(EntityManager em)
    {
        this.em = em;
    }

    public MovementUser register(String userName, String email)
    {
        MovementUser user = new MovementUser(userName, email);
        this.em.persist(user);
        return user;
    }

    public MovementUser getUser(String userName)
    {
        Query query = this.em.createQuery("select user from movementusers user where user.username = :name");
        query.setParameter("name", userName);

        List<MovementUser> users = query.getResultList();
        if(users.isEmpty())
        {
            return null;
        }
        else
        {
            return users.get(0);
        }
    }
}
