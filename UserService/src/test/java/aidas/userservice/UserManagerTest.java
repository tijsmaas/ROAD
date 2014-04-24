/*
 * Copyright by AIDaS.
 */

package aidas.userservice;

import javax.inject.Inject;
import org.jboss.arquillian.container.test.api.Deployment;
import org.jboss.arquillian.testng.Arquillian;
import org.jboss.shrinkwrap.api.Archive;
import org.jboss.shrinkwrap.api.ShrinkWrap;
import org.jboss.shrinkwrap.api.asset.EmptyAsset;
import org.jboss.shrinkwrap.api.spec.WebArchive;
import org.testng.annotations.Test;

/**
 * This class provides the tests for the functions in the {@link UserManager} 
 * class.
 * 
 * @author Geert
 */
public class UserManagerTest extends Arquillian {
    
    @Inject
    private IUserManager um;
    
    @Deployment
    public static Archive<?> createDeployment() {
        Archive<?> archive = ShrinkWrap.create(WebArchive.class, "UserManagerTest.war")
                .addPackage("aidas.utils")
                .addPackage("aidas.security")
                .addPackage("aidas.userservice")
                .addAsResource("META-INF/persistence.xml", "META-INF/persistence.xml")
                .addAsWebInfResource(EmptyAsset.INSTANCE, "beans.xml");
        
        System.out.println(archive.toString(true));
        return archive;
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
        this.um.login("admin", "admin");
    }
}