package entities;

import java.util.Date;

/**
 * Created by Niek on 14/03/14.
 */
public class VehicleOwnership {
    private int id;
    private Vehicle vehicle;

    //TODO: Make a connection to the user? by id?
    private String user;

    private Date registrationdate;
    private Date registrationExperationDate;

}
