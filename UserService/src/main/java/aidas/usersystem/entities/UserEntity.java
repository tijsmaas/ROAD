/*
 * Copyright by AIDaS.
 */

package aidas.usersystem.entities;

import aidas.usersystem.dto.Right;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.OneToMany;

/**
 *
 * @author Geert
 */
@Entity(name="USERS")
public class UserEntity implements Serializable {
    
    /**
     * The identifier of the user.
     */
    @Id @GeneratedValue @Column(name="Id")
    private int id;
    
    /**
     * The username of the user. The username has to be unique.
     * 
     * Cannot be an empty {@link String}.
     */
    @Column(name="Username", length=28, unique=true, nullable=false)
    private String username;
    
    /**
     * The password of the user.
     * 
     * Cannot be an empty {@link String}.
     */
    @Column(name="Password", length=4000, nullable=false)
    private String password;
    
    /**
     * The salt used to hash the {@link #password}.
     */
    @Column(name="Salt", nullable=false)
    private byte[] salt;
    
    /**
     * The rights of the user.
     */
    @OneToMany
    @JoinTable(name="USER_RIGHTS",
        joinColumns= {@JoinColumn(name="UserId", referencedColumnName="id")},
        inverseJoinColumns= {@JoinColumn(name="UserId", referencedColumnName="id")})
    private List<Right> rights;
    
    /**
     * The roles to which the user is assigned.
     */
    @OneToMany
    @JoinTable(name="USER_ROLES",
        joinColumns= {@JoinColumn(name="RoleId", referencedColumnName="id")},
        inverseJoinColumns= {@JoinColumn(name="UserId", referencedColumnName="id")})
    private List<RoleEntity> roles;
    
    /**
     * Creates a new instance of the {@link UserEntity} class.
     */
    public UserEntity() {
        this.rights = new ArrayList<>();
        this.roles = new ArrayList<>();
    }
    
    /**
     * Creates a new instance of the {@link UserEntity} class.
     * @param username the {@link #username} of the {@link UserEntity}. Cannot be an empty {@link String}.
     * @param password the {@link #password} of the {@link UserEntity}. Cannot be an empty {@link String}.
     * @param salt the {@link #salt} of the {@link UserEntity}..
     */
    public UserEntity(String username, String password, byte[] salt) {
        this.username = username;
        this.password = password;
        this.salt = salt;
    }
    
    /**
     * Get the {@link #password} of the {@link UserEntity}.
     * Cannot be an empty {@link String}. 
     * @return the password of the user.
     */
    public String getPassword() {
        return this.password;
    }
    
    /**
     * Get the {@link #username} of the {@link UserEntity}.
     * Cannot be an empty {@link String}. 
     * @return the username of the user.
     */
    public String getUsername() {
        return this.username;
    }
    
    /**
     * Get the {@link #rights} of the {@link UserEntity}.
     * @return the rights of the user.
     */
    public List<Right> getRights() {
        return this.rights;
    }
    
    /**
     * Get the {@link #roles} to which the {@link UserEntity} is assigned.
     * @return the roles to which the user is assigned.
     */
    public List<RoleEntity> getRoles() {
        return this.roles;
    }
    
    /**
     * Get the {@link #salt} of the {@link UserEntity} which is used to hash the {@link #password}.
     * @return the salt used to hash the password.
     */
    public byte[] getSalt() {
        return this.salt;
    }
}
