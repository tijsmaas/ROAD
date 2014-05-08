package road.movemententities.entities;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Niek on 14/03/14.
 * © Aidas 2014
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

    // Empty constructor for JPA
    public Edge() { }

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

    public String getId() {
        return edgeIdentifier;
    }

    public String getEdgeIdentifier()
    {
        return edgeIdentifier;
    }

    public void setEdgeIdentifier(String edgeIdentifier)
    {
        this.edgeIdentifier = edgeIdentifier;
    }

    public EdgeFunction getFunction()
    {
        return function;
    }

    public void setFunction(EdgeFunction function)
    {
        this.function = function;
    }

    public String getType()
    {
        return type;
    }

    public void setType(String type)
    {
        this.type = type;
    }

    public List<Lane> getLanes()
    {
        return lanes;
    }

    public void setLanes(List<Lane> lanes)
    {
        this.lanes = lanes;
    }

    public City getFrom()
    {
        return from;
    }

    public void setFrom(City from)
    {
        this.from = from;
    }

    public City getTo()
    {
        return to;
    }

    public void setTo(City to)
    {
        this.to = to;
    }

    public Integer getPriority()
    {
        return priority;
    }

    public void setPriority(Integer priority)
    {
        this.priority = priority;
    }

    public List<Connection> getConnections()
    {
        return connections;
    }

    public void setConnections(List<Connection> connections)
    {
        this.connections = connections;
    }    

    public void addConnection(Connection connection)
    {
        this.connections.add(connection);
    }

    public void addLane(Lane lane){
        this.lanes.add(lane);
    }

}
