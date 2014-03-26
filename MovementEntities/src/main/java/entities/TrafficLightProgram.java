package entities;

import javax.persistence.*;
import java.util.List;

/**
 * Created by Niek on 14/03/14.
 */
@Entity
public class TrafficLightProgram {
    @Id @GeneratedValue
    private int id;
    private int trafficLightProgramIdentifier;

    @Enumerated(EnumType.STRING)
    private TrafficLightProgramType type;

    private String programID;

    private int offset;

    @OneToMany
    private List<Phase> phases;


    public enum TrafficLightProgramType {

    }

}
