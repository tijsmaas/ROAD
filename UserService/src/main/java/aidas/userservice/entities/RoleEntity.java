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
 * This entity represents a role to which an {@link UserEntity} can be assigned.
 * These roles have a group of {@link Right} which every {@link UserEntity} 
 * assigned to this role has.
 * 
 * @author Geert
 */
@Entity(name="ROLES")
public class RoleEntity implements Serializable {
    
    /**
     * The identifier of the role. 
     */
    @Id @GeneratedValue @Column(name= "Id")
    private int id;
    
    /**
     * The name of the role.
     * For example 'Administrator', 'Police officer' or 'Judge'.
     * 
     * Cannot be an empty {@link String}.
     */
    @Column(name= "Name", length=128, nullable = false)
    private String name;
    
    /**
     * The description describing this role.
     * 
     * Can be an empty {@link String}.
     */
    @Column(name="Description", length=4000)
    private String description;
    
    /**
     * The rights which are assigned to the {@link UserEntity} when the 
     * {@link UserEntity} is assigned to this role.
     */
    @ElementCollection(targetClass = Right.class)
    @JoinTable(name="ROLE_RIGHTS", joinColumns = @JoinColumn(name = "RoleId"))
    @Enumerated(EnumType.STRING)
    @Column(name="RightName")
    private List<Right> rights;
    
    /**
     * Creates a new instance of the {@link RoleEntity} class.
     */
    public RoleEntity() {
        this.rights = new ArrayList<>();
    }
    
    /**
     * Creates a new instance of the {@link RoleEntity} class.
     * @param name the {@link #name} of the {@link RoleEntity}. Cannot be an empty {@link String}. 
     * @param description the {@link #description} of the {@link RoleEntity}. Can be an empty {@link String}.
     * @param rights a collection of {@link Right} provided by the {@link RoleEntity}.
     */
    public RoleEntity(String name, String description, List<Right> rights) {
        this.name = name;
        this.description = description;
        this.rights = rights;
    }
    
    /**
     * Get the {@link #id} of the {@link RoleEntity}.
     * @return the id of the role.
     */
    public int getId() {
        return this.id;
    }
    
    /**
     * Get the {@link #name} of the {@link RoleEntity}.
     * Cannot be an empty {@link String}. 
     * @return the name of the role.
     */
    public String getName() {
        return this.name;
    }
    
    /**
     * Get the {@link #description} of the {@link RoleEntity}.
     * @return the identifier of the role.
     */
    public String getDescription() {
        return this.description;
    }
    
    /**
     * Get the {@link #rights} of the {@link RoleEntity}.
     * @return the rights of the role.
     */
    public List<Right> getRights() {
        return this.rights;
    }
}
