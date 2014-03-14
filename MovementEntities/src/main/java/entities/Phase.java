package entities;

import java.util.List;

/**
 * Created by Niek on 14/03/14.
 */
public class Phase {
    private int id;
    private int duration;
    private List<TrafficLightState> state;
    private int minDuration;
    private int maxDuration;



    public enum TrafficLightState {
        r,
        y,
        g,
        G
    }


}
