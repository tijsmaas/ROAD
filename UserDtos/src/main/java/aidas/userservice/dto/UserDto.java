/*
 * Copyright by AIDaS.
 */

package aidas.userservice.dto;

/**
 * This class represents a user.
 * 
 * @author Geert
 */
public class UserDto {
    /**
     * The identifier of the user.
     */
    private int id;
    
    /**
     * The username of the user. Cannot be an empty {@link String}.
     */
    private String username;

    /**
     * Creates a new instance of the {@link UserDto} class.
     */
    public UserDto() {}

    /**
     * Creates a new instance of the {@link UserDto} class.
     * @param id the identifier of the user.
     * @param username the username of the user. Cannot be an empty {@link String}.
     */
    public UserDto(int id, String username)
    {
        this.id = id;
        this.username = username;
    }
    
    /**
     * Get the {@link #id} of the {@link UserDto}.
     * @return the identifier of the user.
     */
    public int getId()
    {
        return this.id;
    }

	/**
	 * Set the {@link #id} of the {@link UserDto}.
	 */
    public void setId(int id)
    {
        this.id = id;
    }
    
    /**
     * Get the {@link #username} of the {@link UserDto}. Cannot be an empty {@link String}.
     * @return the username of the user.
     */
    public String getUsername()
    {
        return this.username;
    }

	/**
	 * Set the {@link #username} of the {@link UserDto}.
	 */
    public void setUsername(String username)
    {
        this.username = username;
    }
}
