import org.junit.*;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.Query;

/**
 * Created by Niek on 28/03/14.
 * © Aidas 2014
 */
public class PUTest
{
    protected static EntityManagerFactory emf;

    protected EntityManager em;

    @BeforeClass
    public static void createEMF(){
        emf = Persistence.createEntityManagerFactory("MovementPU");
    }

    @AfterClass
    public static void closeEntityManagerFactory(){
        emf.close();
    }

    @Before
    public void beginTransaction(){
        em = emf.createEntityManager();
        em.getTransaction().begin();
    }

    @After
    public void rollBackTransaction(){
        if (em.getTransaction().isActive()) {
            em.getTransaction().rollback();
        }

        if (em.isOpen()) {
            em.close();
        }
    }

    @Test
    public void TestSimpleQuery() throws InterruptedException
    {
       Query q = em.createQuery("select count(movement) From Movement movement");

       Long resultCount = (Long)q.getSingleResult();

       Assert.assertEquals(new Long(0), resultCount);
    }


}
