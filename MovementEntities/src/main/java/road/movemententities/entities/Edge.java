package road.movemententities.entities;

import road.movemententities.entities.enumerations.EdgeFunction;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

/**
 * The Edge Entity is used internally for constructing the SUMO map and linking movements to it.
 *
 * Created by Niek on 14/03/14.
 *  Aidas 2014
 */
@Entity
public class Edge implements MovementEntity<String>
{

    // example id: ":-38_5"
    @Id
    private String edgeIdentifier;

    @Enumerated(EnumType.STRING)
    private EdgeFunction function;

    @Column(name = "EdgeType")
    private String type; // highway.primary, highway.secondary, etc

    @OneToMany(cascade = {CascadeType.ALL})
    private List<Lane> lanes;

    @ManyToOne(cascade = {CascadeType.ALL})
    @JoinColumn(name="FromEdge")
    private City from;

    @ManyToOne(cascade = {CascadeType.ALL})
    @JoinColumn(name="ToEdge")
    private City to;
    
    // Importance of the road, might be useful for calculations
    private Integer priority;

    //@OneToMany(cascade = CascadeType.REMOVE)
    @Transient
    private List<Connection> connections;

    /**
     * Empty JPA constructor
     */
    public Edge() { }

    /**
     * Create a new instance of the Edge object
     * @param id The ID of the edge
     * @param function The function of the edge
     * @param type The Type of the edge
     * @param from The {@link City} it came from
     * @param to The {@link City} it goes to
     * @param priority The priority for this edge
     */
    public Edge(String id, String function, String type, City from, City to, Integer priority)
    {
        if (id == null || id.isEmpty()) throw new IllegalArgumentException("Edge ID cannot be empty");
        this.edgeIdentifier = id;
        this.function = EdgeFunction.fromString(function);
        this.type = type;
        this.from = from;
        this.to = to;
        this.priority = priority;
        this.lanes = new ArrayList();
        this.connections = new ArrayList();        
    }
    
    //region Properties

    /**
     * Get the ID of this
     * @return the ID of this
     */
    public String getId() {
        return edgeIdentifier;
    }

    /**
     * Get the Edge Identifier
     * @return the edge identifier
     */
    public String getEdgeIdentifier()
    {
        return edgeIdentifier;
    }

    /**
     * Set the edge Identifier
     * @param edgeIdentifier to set
     */
    public void setEdgeIdentifier(String edgeIdentifier)
    {
        this.edgeIdentifier = edgeIdentifier;
    }

    /**
     * Get the function for this edge
     * @return The function
     */
    public EdgeFunction getFunction()
    {
        return function;
    }

    /**
     * Set the function for this edge
     * @param function the new function value
     */
    public void setFunction(EdgeFunction function)
    {
        this.function = function;
    }

    /**
     * Get the type of this edge
     * @return the type of tis edge
     */
    public String getType()
    {
        return type;
    }

    /**
     * Set the type for this edge
     * @param type
     */
    public void setType(String type)
    {
        this.type = type;
    }

    /**
     * get the list of {@link Lane lanes} of this edge
     * @return list of {@link Lane}
     */
    public List<Lane> getLanes()
    {
        return lanes;
    }

    /**
     * Set the list of {@link Lane lanes} of this edge
     * @param lanes the list to set
     */
    public void setLanes(List<Lane> lanes)
    {
        this.lanes = lanes;
    }

    /**
     * Get the {@link City} from
     * @return the City it came from
     */
    public City getFrom()
    {
        return from;
    }

    /**
     * Set the {@link} City this edge came from
     * @param from the {@link City} this edge came from
     */
    public void setFrom(City from)
    {
        this.from = from;
    }

    /**
     * Get the {@link City} to
     * @return the {@link City} to link
     */
    public City getTo()
    {
        return to;
    }

    /**
     * Set the {@link City} to
     * @param to the {@link City} to set
     */
    public void setTo(City to)
    {
        this.to = to;
    }

    /**
     * Get the priority of this edge
     * @return the priority of this edge
     */
    public Integer getPriority()
    {
        return priority;
    }

    /**
     * Set the priority of this edge
     * @param priority the priority of this edge
     */
    public void setPriority(Integer priority)
    {
        this.priority = priority;
    }

    /**
     * Get a list of connections
     * @return the list of {@link Connection connections{}}
     */
    public List<Connection> getConnections()
    {
        return connections;
    }

    /**
     * Set the Connections belonging to this edge
     * @param connections The connections to set
     */
    public void setConnections(List<Connection> connections)
    {
        this.connections = connections;
    }

    /**
     * Add a {@link Connection} to the list
     * @param connection the @{link Collection} to add
     */
    public void addConnection(Connection connection)
    {
        this.connections.add(connection);
    }

    /**
     * Add a {@link Lane} to this edge
     * @param lane the {@link Lane} to add.
     */
    public void addLane(Lane lane){
        this.lanes.add(lane);
    }

}
