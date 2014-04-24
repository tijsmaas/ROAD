/*
 * Copyright by AIDaS.
 */

package aidas.userservice.exceptions;

/**
 * This class represents the exceptions which can be thrown by the UserSystem.
 * @author Geert
 */
public class UserSystemException extends Exception {
    
    /**
     * Initializes a new instance of the {@link UserSystemException} class.
     * @param message the message containing the details of the exception.
     */
    public UserSystemException(String message) {
        super(message);
    }
}
