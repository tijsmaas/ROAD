/*
 * Copyright by AIDaS.
 */

package road.userservice.entities;

import road.security.Security;
import road.userservice.dto.Right;

import javax.persistence.*;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/**
 * @author Geert
 */
@Entity(name = "USERS")
public class UserEntity implements Serializable
{

    /**
     * The identifier of the user.
     */
    @Id
    @GeneratedValue
    @Column(name = "Id")
    private int id;

    /**
     * The username of the user. The username has to be unique.
     * <p/>
     * Cannot be an empty {@link String}.
     */
    @Column(name = "Username", length = 28, unique = true, nullable = false)
    private String username;

    /**
     * The username of the user. The username has to be unique.
     * <p/>
     * Cannot be an empty {@link String}.
     */
    @Column(name = "Email", length = 512, nullable = false)
    private String email;

    /**
     * The password of the user.
     * <p/>
     * Cannot be an empty {@link String}.
     */
    @Column(name = "Password", length = 4000, nullable = false)
    private String password;

    /**
     * The salt used to hash the {@link #password}.
     */
    @Column(name = "Salt", length = Security.SALT_BYTE_SIZE, nullable = false)
    private byte[] salt;

    /**
     * The name of the user.
     */
    @Column(name = "Name")
    private String name;

    /**
     * The street where the user lives.
     */
    @Column(name = "Street")
    private String street;

    /**
     * The number of the house in the street in which the user lives (with a possible extension like 'a').
     */
    @Column(name = "HouseNumber")
    private String houseNumber;

    /**
     * The postal code in which the user lives.
     */
    @Column(name = "PostalCode")
    private String postalCode;

    /**
     * The city in which the user lives.
     */
    @Column(name = "City")
    private String city;

    /**
     * The rights of the user.
     */
    @ElementCollection(targetClass = Right.class)
    @JoinTable(name = "USER_RIGHTS", joinColumns = @JoinColumn(name = "UserId"))
    @Enumerated(EnumType.STRING)
    @Column(name = "RightName")
    private List<Right> rights;

    /**
     * The roles to which the user is assigned.
     */
    @ManyToMany
    @JoinTable(name = "USER_ROLES",
            joinColumns = {@JoinColumn(name = "UserId", referencedColumnName = "id")},
            inverseJoinColumns = {@JoinColumn(name = "RoleId", referencedColumnName = "id")})
    private List<RoleEntity> roles;

    /**
     * Creates a new instance of the {@link UserEntity} class.
     */
    public UserEntity()
    {
        this.rights = new ArrayList<>();
        this.roles = new ArrayList<>();
    }

    /**
     * Creates a new instance of the {@link UserEntity} class.
     *
     * @param username the {@link #username} of the {@link UserEntity}. Cannot be an empty {@link String}.
     * @param password the {@link #password} of the {@link UserEntity}. Cannot be an empty {@link String}.
     * @param salt     the {@link #salt} of the {@link UserEntity}. Cannot be an empty {@link String}.
     */
    public UserEntity(String username, String password, byte[] salt, String email)
    {
        this();

        if (username == null || username.isEmpty())
        {
            throw new IllegalArgumentException("UserEntity: Username cannot be empty.");
        }
        else if (password == null || password.isEmpty())
        {
            throw new IllegalArgumentException("UserEntity: Password cannot be empty.");
        }
        else if (salt.length == 0)
        {
            throw new IllegalArgumentException("UserEntity: Salt cannot be empty.");
        }
        else if(email == null || email.isEmpty())
        {
            throw new IllegalArgumentException("UserEntity: Email may not be empty or null");
        }

        this.username = username;
        this.password = password;
        this.salt = salt;
        this.email = email;
    }

    /**
     * Creates a new instance of the {@link UserEntity} class.
     *
     * @param username the {@link #username} of the {@link UserEntity}. Cannot be an empty {@link String}.
     * @param password the {@link #password} of the {@link UserEntity}. Cannot be an empty {@link String}.
     * @param salt     the {@link #salt} of the {@link UserEntity}. Cannot be an empty {@link String}.
     * @param name     the {@link #name} of the {@link UserEntity}.
     */
    public UserEntity(String username, String password, byte[] salt, String email, String name, String street, String houseNumber, String postalCode, String city)
    {
        this(username, password, salt, email);
        this.name = name;
        this.street = street;
        this.houseNumber = houseNumber;
        this.postalCode = postalCode;
        this.city = city;
    }

    /**
     * Get the {@link #id} of the {@link UserEntity}.
     *
     * @return the identifier of the user.
     */
    public int getId()
    {
        return this.id;
    }

    /**
     * Set the {@link #id} of the {@link UserEntity}.
     *
     * @param id the identifier of the user.
     */
    public void setId(int id)
    {
        this.id = id;
    }

    /**
     * Get the {@link #password} of the {@link UserEntity}.
     * Cannot be an empty {@link String}.
     *
     * @return the password of the user.
     */
    public String getPassword()
    {
        return this.password;
    }

    /**
     * Set the {@link #password} of the {@link UserEntity}.
     *
     * @param password the new password.
     */
    public void setPassword(String password)
    {
        this.password = password;
    }

    /**
     * Get the {@link #username} of the {@link UserEntity}.
     * Cannot be an empty {@link String}.
     *
     * @return the username of the user.
     */
    public String getUsername()
    {
        return this.username;
    }

    /**
     * Set the {@link #username} of the {@link UserEntity}.
     *
     * @param username the new username.
     */
    public void setUsername(String username)
    {
        this.username = username;
    }

    /**
     * Get the {@link #email} of the {@link UserEntity}
     * @return
     */
    public String getEmail()
    {
        return email;
    }

    /**
     * Set the {@link #email} of the {@link UserEntity}
     * @return
     */
    public void setEmail(String email)
    {
        this.email = email;
    }

    /**
     * Get the {@link #rights} of the {@link UserEntity}.
     *
     * @return the rights of the user.
     */
    public List<Right> getRights()
    {
        return this.rights;
    }

    /**
     * Set the {@link #rights} of the {@link UserEntity}.
     *
     * @param rights the new rights.
     */
    public void setRights(List<Right> rights)
    {
        this.rights = rights;
    }

    /**
     * Get the {@link #roles} to which the {@link UserEntity} is assigned.
     *
     * @return the roles to which the user is assigned.
     */
    public List<RoleEntity> getRoles()
    {
        return this.roles;
    }

    /**
     * Set the {@link #roles} of the {@link UserEntity}.
     *
     * @param roles the new roles.
     */
    public void setRoles(List<RoleEntity> roles)
    {
        this.roles = roles;
    }

    /**
     * Get the {@link #salt} of the {@link UserEntity} which is used to hash the {@link #password}.
     *
     * @return the salt used to hash the password.
     */
    public byte[] getSalt()
    {
        return this.salt;
    }

    /**
     * Set the {@link #salt} of the {@link UserEntity}.
     *
     * @param salt the new salt.
     */
    public void setSalt(byte[] salt)
    {
        this.salt = salt;
    }

    /**
     * Get the {@link #name} of the {@link UserEntity}.
     *
     * @return the name of the user.
     */
    public String getName()
    {
        return this.name;
    }

    /**
     * Set the {@link #name} of the {@link UserEntity}.
     *
     * @param name the name of the user.
     */
    public void setName(String name)
    {
        this.name = name;
    }

    /**
     * Get the {@link #street} of the {@link UserEntity}.
     *
     * @return the street in which the user lives.
     */
    public String getStreet()
    {
        return this.street;
    }

    /**
     * Set the {@link #street} of the {@link UserEntity}.
     *
     * @param street the street in which the user lives.
     */
    public void setStreet(String street)
    {
        this.street = street;
    }

    /**
     * Get the {@link #houseNumber} of the {@link UserEntity}.
     *
     * @return he number of the house in the street in which the user lives.
     */
    public String getHouseNumber()
    {
        return this.houseNumber;
    }

    /**
     * Set the {@link #houseNumber} of the {@link UserEntity}.
     *
     * @param houseNumber the number of the house in the street in which the user lives.
     */
    public void setHouseNumber(String houseNumber)
    {
        this.houseNumber = houseNumber;
    }

    /**
     * Get the {@link #postalCode} of the {@link UserEntity}.
     *
     * @return the postal code in which the user lives.
     */
    public String getPostalCode()
    {
        return this.postalCode;
    }

    /**
     * Set the {@link #postalCode} of the {@link UserEntity}.
     *
     * @param postalCode the postal code in which the user lives.
     */
    public void setPostalCode(String postalCode)
    {
        this.postalCode = postalCode;
    }

    /**
     * Get the {@link #city} of the {@link UserEntity}.
     *
     * @return the city in which the user lives.
     */
    public String getCity()
    {
        return this.city;
    }

    /**
     * Get the {@link #city} of the {@link UserEntity}.
     *
     * @return the city in which the user lives.
     */
    public void setCity(String city)
    {
        this.city = city;
    }
}
