package entities;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Niek on 14/03/14.
 * © Aidas 2014
 */
@Entity
public class Edge
{

    @Id
    @GeneratedValue
    private int id; // example id: ":-38_5"
    private String edgeIdentifier;

    @Enumerated(EnumType.STRING)
    private EdgeFunction function;

    @Column(name = "EdgeType")
    private String type; // highway.primary, highway.secondary, etc

    @OneToMany
    private List<Lane> lanes;

    @Column(name = "fromEdge")
    private String from;// points to edge or junction

    @Column(name = "ToEdge")
    private String to; // points to edge or junction
    private Integer priority; // changed to Integer because it can also be NULL

    @OneToMany
    private List<Connection> connections;

    //region Properties
    public int getId()
    {
        return id;
    }

    public void setId(int id)
    {
        this.id = id;
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

    public String getFrom()
    {
        return from;
    }

    public void setFrom(String from)
    {
        this.from = from;
    }

    public String getTo()
    {
        return to;
    }

    public void setTo(String to)
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
    //endregion


    public Edge()
    {
    }

    public enum EdgeFunction
    {
        normal,
        internal,
        connector;
        
        public static EdgeFunction fromString(String s) {
        	if("normal".equals(s)) {
        		return EdgeFunction.normal;
        	}else if("internal".equals(s)) {
        		return EdgeFunction.internal;
        	}else if("connector".equals(s)) {
        		return EdgeFunction.connector;
        	}else{
        		return null;
        	}
        };
    }

    public Edge(String id, String function, String type, String from, String to, Integer priority)
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


    public void addConnection(Connection connection)
    {
        this.connections.add(connection);
    }

    public void addLane(Lane lane){
        this.lanes.add(lane);
    }

}
