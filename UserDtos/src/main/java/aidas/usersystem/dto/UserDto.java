/*
 * Copyright by AIDaS.
 */

package aidas.usersystem.dto;

/**
 * This class represents a user.
 * 
 * @author Geert
 */
public class UserDto {
    /**
     * The identifier of the user.
     */
    private final int id;
    
    /**
     * The username of the user. Cannot be an empty {@link String}.
     */
    private final String username;
    
    /**
     * Creates a new instance of the {@link UserDto} class.
     * @param id the identifier of the user.
     * @param username the username of the user. Cannot be an empty {@link String}.
     */
    public UserDto(int id, String username) {
        this.id = id;
        this.username = username;
    }
    
    /**
     * Get the {@link #id} of the {@link UserDto}.
     * @return the identifier of the user.
     */
    public int getId() {
        return this.id;
    }
    
    /**
     * Get the {@link #username} of the {@link UserDto}. Cannot be an empty {@link String}.
     * @return the username of the user.
     */
    public String getUsername() {
        return this.username;
    }
}
