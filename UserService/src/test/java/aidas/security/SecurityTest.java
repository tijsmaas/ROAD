/*
 * Copyright by AIDaS.
 */

package aidas.security;

import java.util.HashMap;
import java.util.Map;
import org.testng.Assert;
import org.testng.annotations.Test;

/**
 * This class provides the tests for the functions in the {@link Security} 
 * class.
 * 
 * @author Geert
 */
public class SecurityTest {
    
    /**
     * Test the {@link Security#generateSalt()} function.
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
     * Test the {@link Security#processPassword(java.lang.String, 
     * java.lang.String, byte[])} function.
     * 
     * @throws java.lang.Exception when a function called in the test function 
     * throws an unexpected exception.
     */
    @Test
    public void testPasswordProcess() throws Exception {
        byte[] salt = Security.generateSalt();
        String username = "Bert";
        String password = "9jDNGuLjV7aUc8x3cvYfWksY";
        
        String processedPassword1 = Security.processPassword(password, username, salt);
        Assert.assertNotNull(processedPassword1);
        String processedPassword2 = Security.processPassword(password, username, salt);
        Assert.assertNotNull(processedPassword2);
        
        // Check if the password is processed in the same matter on providing the same parameters.
        Assert.assertEquals(processedPassword2, processedPassword1);
        
        String username2 = "Henk";
        String processedPassword3 = Security.processPassword(password, username2, salt);
        Assert.assertNotNull(processedPassword3);
        
        // Check if the processed password is different when changing the username parameter.
        Assert.assertNotEquals(processedPassword3, processedPassword1);
        
        String password2 = "QNUh3fdgAbkFaSevdHdsGear";
        String processedPassword4 = Security.processPassword(password2, username, salt);
        Assert.assertNotNull(processedPassword4);
        
        // Check if the processed password is different when changing the password parameter.
        Assert.assertNotEquals(processedPassword4, processedPassword1);
        
        byte[] salt2 = Security.generateSalt();
        // Chekc if the generated salts are not equal.
        Assert.assertNotEquals(salt, salt2);
        
        String processedPassword5 = Security.processPassword(password, username, salt2);
        Assert.assertNotNull(processedPassword5);
        
        // Check if the processed password is different when changing the salt parameter.
        Assert.assertNotEquals(processedPassword5, processedPassword1);
    }
    
    /**
     * Test the {@link Security#hash(java.lang.String, byte[])} function.
     * 
     * @throws java.lang.Exception when a function called in the test function 
     * throws an unexpected exception.
     */
    @Test
    public void testHash() throws Exception {
        byte[] salt1 = Security.generateSalt();
        String stringToHash1 = "password";
        
        String hashedString1 = Security.hash(stringToHash1, salt1);
        Assert.assertNotNull(hashedString1);
        String hashedString2 = Security.hash(stringToHash1, salt1);
        Assert.assertNotNull(hashedString2);
        
        // Check if the string is hashed in the same way on using the same salt.
        Assert.assertEquals(hashedString1, hashedString2);
        
        byte[] salt2 = Security.generateSalt();
        String hashedString3 = Security.hash(stringToHash1, salt2);
        Assert.assertNotNull(hashedString3);
        Assert.assertNotEquals(salt1, salt2);
        
        // Check if the string is hashed differently using another salt.
        Assert.assertNotEquals(hashedString1, hashedString3);
        
        String stringToHash2 = "password2";
        
        String hashedString4 = Security.hash(stringToHash2, salt2);
        Assert.assertNotNull(hashedString4);
        
        // Check if the hash is different if using another string.
        Assert.assertNotEquals(hashedString3, hashedString4);
    }
    
    /**
     * Test the {@link Security#encrypt(java.lang.String, java.lang.String)} 
     * function.
     * 
     * @throws java.lang.Exception when a function called in the test function 
     * throws an unexpected exception.
     */
    @Test
    public void testEncrypt() throws Exception {
        String stringToEncrypt1 = "password";
        String key1 = "SomeKey";
        
        String encrypted1 = Security.encrypt(stringToEncrypt1, key1);
        Assert.assertNotNull(encrypted1);
        String encrypted2 = Security.encrypt(stringToEncrypt1, key1);
        Assert.assertNotNull(encrypted1);
        
        // Check if the encrypted string is processed the same mater when using the same string and key.
        Assert.assertEquals(encrypted1, encrypted2);
        
        String stringToEncrypt2 = "password123";
        String encrypted3 = Security.encrypt(stringToEncrypt2, key1);
        Assert.assertNotNull(encrypted1);
        
        // Check if the encrypted string is different when using a different string.
        Assert.assertNotEquals(encrypted1, encrypted3);
        
        String key2 = "AnotherKey";
        String encrypted4 = Security.encrypt(stringToEncrypt2, key2);
        Assert.assertNotNull(encrypted4);
        
        // Check if the encrypted string is different when using a different key.
        Assert.assertNotEquals(encrypted3, encrypted4);
    }
}