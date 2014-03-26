package entities;

import javax.persistence.*;
import java.util.List;

/**
 * Created by Niek on 14/03/14.
 */
@Entity
public class Phase {

    @Id @GeneratedValue
    private int id;
    private int duration;

    @ElementCollection(targetClass = TrafficLightState.class)
    @JoinTable(name = "trafficlightStates", joinColumns = @JoinColumn(name = "id"))
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
