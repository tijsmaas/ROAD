package entities;

import javax.persistence.*;
import java.util.Date;
import java.util.GregorianCalendar;

/**
 * Created by Niek on 14/03/14.
 * © Aidas 2014
 */
@Entity
public class VehicleOwnership
{
    @Id
    @GeneratedValue
    private int id;

    @ManyToOne
    private Vehicle vehicle;

    //TODO: Make a connection to the user? by id?
    private int userID;


    @Temporal(TemporalType.DATE)
    private GregorianCalendar registrationdate;

    @Temporal(TemporalType.DATE)
    private GregorianCalendar registrationExperationDate;

}
