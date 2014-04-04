/*
 * Copyright by AIDaS.
 */

package aidas.usersystem;

import aidas.usersystem.dto.Right;

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
     * @throws SecurityException if the current {@link UserDto} does not have the provided 
     * {@link Right} assigned to the user itself or provided by a {@link RoleDto} to 
     * which the {@link UserDto} is assigned.
     * 
     * @see verifyRight
     */
    void demandRight(String authId, Right right) throws SecurityException;
    
    /**
     * Login to the system by verifing the identity of the user.
     * @param username the {@link UserDto.username} which the user has entered 
     * to verify his identity.
     * @param password the {@link UserDto.password} which the user has entered 
     * to verify his identity. 
     * @return the authentication identifier identifing the logged in user. Will 
     * return null when the authentication failed.
     */
    String login(String username, String password);
    
    /**
     * Register a new user.
     * @param username the {@link UserDto.username} of the user.
     * @param password the {@link UserDto.password} of the user.
     */
    void Register(String username, String password) throws IllegalArgumentException;
    
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
