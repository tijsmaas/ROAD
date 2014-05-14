/*
 * Copyright by AIDaS.
 */

package aidas.userservice;

import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

/**
 * This class provides the tests for the functions in the {@link UserManager} 
 * class.
 * 
 * @author Geert
 */
public class UserManagerTest {

    private IUserManager um;

    private EntityManagerFactory emf;

    /**
     * Function which is called before any test cases are tested.
     */
    @BeforeClass
    public void beforeClass() {
        //this.emf = Persistence.createEntityManagerFactory("UserServicePU");
        //this.um = new UserManager(emf);
    }

    /**
     * Function which is called after all test cases are tested.
     */
    @AfterClass
    public void afterClass() {
        //this.emf.close();
    }

    /**
     * Test the {@link UserManager#register(java.lang.String, java.lang.String)}
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