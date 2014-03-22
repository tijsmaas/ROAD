package entities;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Niek on 14/03/14.
 */
public class Edge {

	private String id; // example id: ":-38_5"
    private String edgeIdentifier;
    private EdgeFunction function;
    private String type; // highway.primary, highway.secondary, etc
    private List<Lane> lanes;
    private String from; // points to edge or junction
    private String to; // points to edge or junction
    private Integer priority; // changed to Integer because it can also be NULL
    private List<Connection> connections;

    public enum EdgeFunction {
        normal,
        internal,
        connector,
    }
    
    public Edge(String id) {
    	if(id == null || id.isEmpty()) throw new IllegalArgumentException("Edge ID cannot be empty");
		this.id = id;
		this.lanes = new ArrayList();
		this.connections = new ArrayList();
	}
    
	public String getId()
	{
		return id;
	}

	public void setFunction(EdgeFunction function) {
		this.function = function;
	}

	public void setType(String type) {
		this.type = type;
	}
	
	public void addLane(Lane lane) {
		this.lanes.add(lane);
	}

	public void setLanes(List<Lane> lanes) {
		this.lanes = lanes;
	}

	public List<Lane> getLanes()
	{
		return lanes;
	}

	public void setFrom(String from) {
		this.from = from;
	}

	public void setTo(String to) {
		this.to = to;
	}

	public void setPriority(Integer priority) {
		this.priority = priority;
	}

	public List<Connection> getConnections()
	{
		return connections;
	}
	
	public void addConnection(Connection connection) {
		this.connections.add(connection);
	}

}
