package entities;

import java.util.List;

/**
 * Created by Niek on 14/03/14.
 */
public class TrafficLightProgram {
    private int id;
    private int trafficLightProgramIdentifier;
    private TrafficLightProgramType type;
    private String programID;
    private int offset;
    private List<Phase> phases;


    public enum TrafficLightProgramType {

    }

}
