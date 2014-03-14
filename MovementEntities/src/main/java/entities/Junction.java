package entities;

import java.awt.*;
import java.util.List;

/**
 * Created by Niek on 14/03/14.
 */
public class Junction {
    private int id;
    private String junctionIdentifier;
    private double x;
    private double y;
    private List<Lane> incLanes;
    private List<Lane> intLanes;
    private Polygon shape;
    private List<JunctionRequest> requests;



}
