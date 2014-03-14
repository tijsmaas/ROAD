package entities;

import java.util.List;

/**
 * Created by Niek on 14/03/14.
 */
public abstract class Edge {
    private int id;
    private String edgeIdentifier;
    private EdgeFunction type;
    private List<Lane> lanes;
    private String from;
    private String to;
    private int priority;
    private List<Connection> connections;

    public enum EdgeFunction {
        normal,
        internal,
        connector,
    }

}
