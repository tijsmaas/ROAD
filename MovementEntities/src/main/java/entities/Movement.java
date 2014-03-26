package entities;

import javax.persistence.*;
import java.util.Date;
import java.util.List;

/**
 * Created by Niek on 14/03/14.
 */
@Entity
public class Movement {
    @Id @GeneratedValue
    private int id;

    @Temporal(TemporalType.DATE)
    private Date movementDate;

    @ManyToOne
    private Lane lane;

    @ManyToOne
    private Edge edge;

    @OneToMany
    private List<MovementVehicle> movementVehicles;
}
