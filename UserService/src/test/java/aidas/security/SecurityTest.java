/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package aidas.security;

import java.util.HashMap;
import java.util.Map;
import org.testng.Assert;
import org.testng.annotations.Test;

/**
 *
 * @author Geert
 */
public class SecurityTest {
    
    /**
     * Test the {@link Security.generateSalt} function.
     */
    @Test
    public void testSaltGeneration() {
        int numberOfIterations = 100000;
        
        Map<byte[], String> salts = new HashMap<>();
        
        for (int i = 0; i < numberOfIterations; i++) {
            salts.put(Security.generateSalt(), null);
        }
        
        // Check if not to many of the same salts are generated.
        Assert.assertEquals(salts.size(), numberOfIterations);
    }
    
    /**
     * Test the {@link Security.processPassword} function.
     */
    @Test
    public void testPasswordProcess() {
        byte[] salt = Security.generateSalt();
        String username = "Bert";
        String password = "9jDNGuLjV7aUc8x3cvYfWksY";
        
        String processedPassword1 = Security.processPassword(password, username, salt);
        Assert.assertNotNull(processedPassword1); // Check if the processed password is not null.
        String processedPassword2 = Security.processPassword(password, username, salt);
        Assert.assertNotNull(processedPassword2); // Check if the processed password is not null.
        
        // Check if the password is processed in the same matter on providing the same parameters.
        Assert.assertEquals(processedPassword2, processedPassword1);
        
        String username2 = "Henk";
        String processedPassword3 = Security.processPassword(password, username2, salt);
        Assert.assertNotNull(processedPassword3); // Check if the processed password is not null.
        
        // Check if the processed password is different when changing the username parameter.
        Assert.assertNotEquals(processedPassword3, processedPassword1);
        
        String password2 = "QNUh3fdgAbkFaSevdHdsGear";
        String processedPassword4 = Security.processPassword(password2, username, salt);
        Assert.assertNotNull(processedPassword4); // Check if the processed password is not null.
        
        // Check if the processed password is different when changing the password parameter.
        Assert.assertNotEquals(processedPassword4, processedPassword1);
        
        byte[] salt2 = Security.generateSalt();
        // Chekc if the generated salts are not equal.
        Assert.assertNotEquals(salt, salt2);
        
        String processedPassword5 = Security.processPassword(password, username, salt2);
        Assert.assertNotNull(processedPassword5); // Check if the processed password is not null.
        
        // Check if the processed password is different when changing the salt parameter.
        Assert.assertNotEquals(processedPassword5, processedPassword1);
    }
}
