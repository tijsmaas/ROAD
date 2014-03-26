package entities;

import javax.persistence.*;
import java.util.Date;

/**
 * Created by Niek on 14/03/14.
 */
@Entity
public class VehicleOwnership {
    @Id @GeneratedValue
    private int id;

    @ManyToOne
    private Vehicle vehicle;

    //TODO: Make a connection to the user? by id?
    private String user;


    @Temporal(TemporalType.DATE)
    private Date registrationdate;

    @Temporal(TemporalType.DATE)
    private Date registrationExperationDate;

}
