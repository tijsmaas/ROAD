package entities;

import java.util.Date;
import java.util.List;

/**
 * Created by Niek on 14/03/14.
 */
public class Movement {
    private int id;
    private Date movementDate;
    private Lane lane;
    private Edge edge;
    private List<MovementVehicle> movementVehicles;
}
