package road.movemententityaccess.dao;

import road.movemententities.entities.MovementUser;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Query;
import java.util.List;

/**
 * Created by geh on 20-5-14.
 */
public class LoginDAOImpl implements LoginDAO
{
    private EntityManager em;

    public LoginDAOImpl(EntityManagerFactory emf)
    {
        this.em = emf.createEntityManager();
    }

    @Override
    public MovementUser register(String userName, String email)
    {
        Query query = this.em.createQuery("select count(user) from movementusers user where user.username = :name");
        query.setParameter("name", userName);
        if((Long)query.getSingleResult() == 0)
        {
            MovementUser user = new MovementUser(userName, email);

            this.em.getTransaction().begin();
            this.em.persist(user);
            this.em.getTransaction().commit();

            return user;
        }
        else
        {
            Query qUser = this.em.createQuery("select user from movementusers user where user.username = :name");
            qUser.setParameter("name", userName);

            return (MovementUser)qUser.getSingleResult();
        }
    }

    @Override
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

    @Override
    public MovementUser getUser(int id)
    {
        MovementUser user = this.em.find(MovementUser.class, id);
        this.em.refresh(user);
        return user;
    }

    @Override
    public MovementUser update(Integer id, String name, String street, String houseNumber, String postalCode, String city, Boolean invoiceNotification)
    {
        MovementUser user = this.em.find(MovementUser.class, id);

        if (user == null)
        {
            return null;
        }

        user.setName(name);
        user.setStreet(street);
        user.setHouseNumber(houseNumber);
        user.setPostalCode(postalCode);
        user.setCity(city);
        user.setInvoiceMail(invoiceNotification);

        this.em.getTransaction().begin();
        this.em.merge(user);
        this.em.getTransaction().commit();

        return user;
    }
}
