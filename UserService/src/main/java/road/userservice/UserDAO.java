/*
 * Copyright by AIDaS.
 */

package road.userservice;

import road.userservice.dto.Right;
import road.userservice.dto.UserDto;
import road.userservice.exceptions.UserSystemException;

/**
 * This interface represents the manager which handles all the functions which 
 * can be handled by the UserSystem.
 * 
 * @author Geert
 */
public interface UserDAO {
    
    /**
     * Check if the current {@link UserDto} has the provided right.
     * @param authId the authentication identifier provided by the 
     * {@link #login(java.lang.String, java.lang.String)} function.
     * @param right the right which is expected of the current {@link UserDto}.
     * 
     * @throws road.userservice.exceptions.UserSystemException when failed the
     * exception will be thrown containing a message with the details of the 
     * failure.
     * 
     * Also see {@link #verifyRight(String, road.userservice.dto.Right)}.
     */
    void demandRight(String authId, Right right) throws UserSystemException;
    
    /**
     * Login to the system by verifying the identity of the user.
     * @param username the {@link road.userservice.entities.UserEntity#username} which the user has entered
     * to verify his identity.
     * @param password the {@link road.userservice.entities.UserEntity#password} which the user has entered
     * to verify his identity. 
     * @return the authenticated user or null if the authentication failed.
     */
    UserDto login(String username, String password);
    
    /**
     * Register a new user.
     * @param username the {@link road.userservice.entities.UserEntity#username} of the user.
     * @param password the {@link road.userservice.entities.UserEntity#password} of the user.
     * 
     * @throws road.userservice.exceptions.UserSystemException when failed the
     * exception will be thrown containing a message with the details of the 
     * failure.
     */
    UserDto register(String username, String password) throws UserSystemException;
    
    /**
     * Check if the current {@link UserDto} has the provided right.
     * @param username the username of the user.
     * @param right the right which is expected of the current {@link UserDto}.
     * @return true if the user has the provided {@link Right} assigned to the 
     * user itself or provided by a {@link road.userservice.dto.RoleDto} to which the {@link UserDto}
     * is assigned. Otherwise false.
     * 
     * Also see {@link #demandRight(String, road.userservice.dto.Right)}.
     */
    boolean verifyRight(String username, Right right);

    /**
     * Change the password of the provided user.
     * @param id the identifier of the user.
     * @param oldPassword the current password of the user.
     * @param newPassword the new password of the user.
     * @param newPasswordValidate the new password of the user again to validate if they are the same.
     * @return null if the change was successful, otherwise return the error.
     */
    String changePassword(int id, String oldPassword, String newPassword, String newPasswordValidate);

    /**
     * Change the details of the provided user.
     * @param id the identifier of the user.
     * @param name the name of the user.
     * @param street the street in which the user lives.
     * @param houseNumber the number of the house in the street in which the user lives.
     * @param postalCode the postal code in which the user lives.
     * @param city the city in which the user lives.
     * @return true if changing the details was successful, otherwise false.
     */
    boolean changeDetails(int id, String name, String street, String houseNumber, String postalCode, String city);
}