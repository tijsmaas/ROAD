/*
 * Copyright by AIDaS.
 */

package road.userservice;

import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import javax.persistence.EntityManagerFactory;

/**
 * This class provides the tests for the functions in the {@link UserDAOImpl}
 * class.
 * 
 * @author Geert
 */
public class UserDAOTest {

    private UserDAO um;

    private EntityManagerFactory emf;

    /**
     * Function which is called before any test cases are tested.
     */
    @BeforeClass
    public void beforeClass() {
        //this.emf = Persistence.createEntityManagerFactory("UserServicePU");
        //this.um = new UserDAOImpl(emf);
    }

    /**
     * Function which is called after all test cases are tested.
     */
    @AfterClass
    public void afterClass() {
        //this.emf.close();
    }

    /**
     * Test the {@link UserDAOImpl#register(java.lang.String, java.lang.String)}
     * function.
     * 
     * @throws java.lang.Exception when a function called in the test function 
     * throws an unexpected exception.
     */
    @Test
    public void testRegister() throws Exception {
        //this.um.register("admin", "admin");
    }
}