/*
 * Copyright by AIDaS.
 */

package aidas.userservice.entities;

import aidas.userservice.dto.Right;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import javax.persistence.Column;
import javax.persistence.ElementCollection;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;

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
    @ManyToMany
    @JoinTable(name = "USER_RIGHTS", joinColumns = @JoinColumn(name = "UserId"))
    @Enumerated(EnumType.STRING)
    @Column(name="RoleName")
    private List<Right> rights;
    
    /**
     * The roles to which the user is assigned.
     */
    @ManyToMany
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
     * Set the {@link #password} of the {@link UserEntity}.
     * @param password the new password.
     */
    public void setPassword(String password) {
        this.password = password;
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
     * Set the {@link #username} of the {@link UserEntity}.
     * @param username the new username.
     */
    public void setUsername(String username) {
        
    }
    
    /**
     * Get the {@link #rights} of the {@link UserEntity}.
     * @return the rights of the user.
     */
    public List<Right> getRights() {
        return this.rights;
    }
    
    /**
     * Set the {@link #rights} of the {@link UserEntity}.
     * @param rights the new rights.
     */
    public void setRights(List<Right> rights) {
        this.rights = rights;
    }
    
    /**
     * Get the {@link #roles} to which the {@link UserEntity} is assigned.
     * @return the roles to which the user is assigned.
     */
    public List<RoleEntity> getRoles() {
        return this.roles;
    }
    
    /**
     * Set the {@link #roles} of the {@link UserEntity}.
     * @param roles the new roles.
     */
    public void setRoles(List<RoleEntity> roles) {
        this.roles = roles;
    }
    
    /**
     * Get the {@link #salt} of the {@link UserEntity} which is used to hash the {@link #password}.
     * @return the salt used to hash the password.
     */
    public byte[] getSalt() {
        return this.salt;
    }
    
    /**
     * Set the {@link #salt} of the {@link UserEntity}.
     * @param salt the new salt.
     */
    public void setSalt(byte[] salt) {
        this.salt = salt;
    }
}
