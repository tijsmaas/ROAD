package entities;

/**
 * Created by Niek on 14/03/14.
 */
public class Connection {
    private Edge from;
    private Edge to;
    private Lane fromLane;
    private Lane toLane;
    private Lane via;
    private TrafficLightProgram trafficLight;
    private int linkIndex;
    private ConnectionDirection direction;
    private ConnectionState state;

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

}
