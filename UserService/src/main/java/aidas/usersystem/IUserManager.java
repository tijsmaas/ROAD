/*
 * Copyright by AIDaS.
 */

package aidas.usersystem;

import aidas.usersystem.dto.Right;
import aidas.usersystem.exceptions.UserSystemException;

/**
 * This interface represents the manager which handles all the functions which 
 * can be handled by the UserSystem.
 * 
 * @author Geert
 */
public interface IUserManager {
    
    /**
     * Check if the current {@link UserDto} has the provided right.
     * @param authId the authentication identifier provided by the 
     * {@link #login(java.lang.String, java.lang.String)} function.
     * @param right the right which is expected of the current {@link UserDto}.
     * 
     * @throws aidas.usersystem.exceptions.UserSystemException when failed the 
     * exception will be thrown containing a message with the details of the 
     * failure.
     * 
     * @see verifyRight
     */
    void demandRight(String authId, Right right) throws UserSystemException;
    
    /**
     * Login to the system by verifing the identity of the user.
     * @param username the {@link UserDto.username} which the user has entered 
     * to verify his identity.
     * @param password the {@link UserDto.password} which the user has entered 
     * to verify his identity. 
     * @return the authentication identifier identifing the logged in user. Will 
     * return null when the authentication failed.
     * 
     * @throws aidas.usersystem.exceptions.UserSystemException when failed the 
     * exception will be thrown containing a message with the details of the 
     * failure.
     */
    String login(String username, String password) throws UserSystemException;
    
    /**
     * Register a new user.
     * @param username the {@link UserDto.username} of the user.
     * @param password the {@link UserDto.password} of the user.
     * 
     * @throws aidas.usersystem.exceptions.UserSystemException when failed the 
     * exception will be thrown containing a message with the details of the 
     * failure.
     */
    void Register(String username, String password) throws UserSystemException;
    
    /**
     * Check if the current {@link UserDto} has the provided right.
     * @param authId the authentication identifier provided by the 
     * {@link #login(java.lang.String, java.lang.String)} function.
     * @param right the right which is expected of the current {@link UserDto}.
     * @return true if the user has the provided {@link Right} assigned to the 
     * user itself or provided by a {@link RoleDto} to which the {@link UserDto} 
     * is assigned. Otherwise false.
     * 
     * @see demandRight
     */
    boolean verifyRight(String authId, Right right);
}