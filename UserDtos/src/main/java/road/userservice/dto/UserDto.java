/*
 * Copyright by AIDaS.
 */

package road.userservice.dto;

/**
 * This class represents a user.
 * 
 * @author Geert
 */
public class UserDto
{
    /**
     * The identifier of the user.
     */
    private int id;
    
    /**
     * The username of the user. Cannot be an empty {@link String}.
     */
    private String username;

    /**
     * The email of the user. Cannot be an empty {@link String}.
     */
    private String email;

    /**
     * The name of the user.
     */
    private String name;

    /**
     * The street where the user lives.
     */
    private String street;

    /**
     * The number of the house in the street in which the user lives (with a possible extension like 'a').
     */
    private String houseNumber;

    /**
     * The postal code in which the user lives.
     */
    private String postalCode;

    /**
     * The city in which the user lives.
     */
    private String city;

    /**
     * Creates a new instance of the {@link UserDto} class.
     */
    public UserDto() {}

    /**
     * Creates a new instance of the {@link UserDto} class.
     * @param id the identifier of the user.
     * @param username the username of the user. Cannot be an empty {@link String}.
     */
    public UserDto(int id, String username, String email)
    {
        this();

        if (username == null || username.isEmpty())
        {
            throw new IllegalArgumentException("UserDto: username cannot be empty.");
        }

        this.id = id;
        this.username = username;
        this.email = email;
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

    /**
     * Get the {@link #email} of the {@link UserDto}.
     * @return the email of the user
     */
    public String getEmail()
    {
        return email;
    }

    /**
     * Set the {@link #email} of the {@link UserDto}.
     */
    public void setEmail(String email)
    {
        this.email = email;
    }

    /**
     * Get the {@link #name} of the {@link UserDto}.
     * @return the name of the user.
     */
    public String getName() { return this.name; }

    /**
     * Set the {@link #name} of the {@link UserDto}.
     * @param name the name of the user.
     */
    public void setName(String name) { this.name = name; }

    /**
     * Get the {@link #street} of the {@link UserDto}.
     * @return the street in which the user lives.
     */
    public String getStreet() { return this.street; }

    /**
     * Set the {@link #street} of the {@link UserDto}.
     * @param street the street in which the user lives.
     */
    public void setStreet(String street) { this.street = street; }

    /**
     * Get the {@link #houseNumber} of the {@link UserDto}.
     * @return he number of the house in the street in which the user lives.
     */
    public String getHouseNumber() { return this.houseNumber; }

    /**
     * Set the {@link #houseNumber} of the {@link UserDto}.
     * @param houseNumber the number of the house in the street in which the user lives.
     */
    public void setHouseNumber(String houseNumber) { this.houseNumber = houseNumber; }

    /**
     * Get the {@link #postalCode} of the {@link UserDto}.
     * @return the postal code in which the user lives.
     */
    public String getPostalCode() { return this.postalCode; }

    /**
     * Set the {@link #postalCode} of the {@link UserDto}.
     * @param postalCode the postal code in which the user lives.
     */
    public void setPostalCode(String postalCode) { this.postalCode = postalCode; }

    /**
     * Get the {@link #city} of the {@link UserDto}.
     * @return the city in which the user lives.
     */
    public String getCity() { return this.city; }

    /**
     * Get the {@link #city} of the {@link UserDto}.
     * @return the city in which the user lives.
     */
    public void setCity(String city) { this.city = city; }
}
