package entities;

/**
 * Created by Niek on 14/03/14.
 */
public class Connection {
	// required
	private Edge from;
    private Edge to;
    private Lane fromLane;
    private Lane toLane;
    private ConnectionDirection direction;
    private ConnectionState state;
    
    // optional
    private Lane via;
    private TrafficLightProgram trafficLight; // we won't use this
    private int linkIndex; // we won't use this

    public enum ConnectionDirection {
        STRAIGHT,
        TURN,
        LEFT,
        RIGHT,
        PARTIALLY_LEFT,
        PARTIALLY_RIGHT,
        INVALID

    }

    public enum ConnectionState {
        DEAD_END,
        EQUAL,
        MINOR_LINK,
        MAJOR_LINK,
        CONTROLLER_OFF,
        YELLOW_FLASHING,
        YELLOW_MINOR_LINK,
        YELLOW_MAJOR_LINK,
        RED,
        GREEN_MINOR,
        GREEN_MAJOR
    }
    
    public Connection(Edge from, Edge to, Lane fromLane, Lane toLane, String direction, String state) {
    	if(from == null || to == null || fromLane == null || toLane == null) throw new IllegalArgumentException("Required connection attributes are empty");
    	this.from = from;
    	this.to = to;
    	this.fromLane = fromLane;
    	this.toLane = toLane;
    	
    	// TODO convert direction and state, also add to exception above
    }

	public void setVia(Lane via)
	{
		this.via = via;
	}
    
    

}
