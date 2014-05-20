/*
 * Copyright by AIDaS.
 */

package road.userservice.dto;

/**
 * This class represents a role to which an {@link UserDto} can be assigned. For 
 * example 'Administrator', 'Police officer' or 'Judge'. These roles have a group 
 * of {@link Right} which every {@link UserDto} assigned to this role has.
 * 
 * @author Geert
 */
public class RoleDto {
    /**
     * The description of the role. Can be an empty {@link String}.
     */
    private final String description;
    
    /**
     * The identifier of the role.
     */
    private final int id;
    
    /**
     * The name of the role. Cannot be an empty {@link String}.
     */
    private final String name;
    
    /**
     * Creates a new instance of the {@link RoleDto} class.
     * @param id the identifier of the role.
     * @param name the name of the role. Cannot be an empty {@link String}.
     * @param description the description of the role. Can be an empty {@link String}.
     */
    public RoleDto(int id, String name, String description) {
        this.description = description;
        this.id = id;
        this.name = name;
    }
    
    /**
     * Get the {@link #description} of the {@link RoleDto}.
     * @return the description of the role.
     */
    public String getDescription() {
        return this.description;
    }
    
    /**
     * Get the {@link #id} of the {@link RoleDto}.
     * @return the identifier of the role.
     */
    public int getId() {
        return this.id;
    }
    
    /**
     * Get the {@link name} of the {@link RoleDto}.
     * @return the name of the role.
     */
    public String getName() {
        return this.name;
    }
}
