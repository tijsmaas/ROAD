package entities;

import javax.persistence.*;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;

/**
 * Created by Niek on 14/03/14.
 * © Aidas 2014
 */
@Entity
public class VehicleOwnership implements MovementEntity<Integer>
{
    @Id
    @GeneratedValue
    private int id;

    @ManyToOne(cascade = {CascadeType.ALL})
    private Vehicle vehicle;

    //TODO: Make a connection to the user? by id?
    private int userID;

    private Calendar registrationdate;

    private Calendar registrationExperationDate;

    // Empty constructor for JPA
    public VehicleOwnership() { }

    //region Properties
    public Integer getId()
    {
        return id;
    }

    public void setId(int id)
    {
        this.id = id;
    }

    public Vehicle getVehicle()
    {
        return vehicle;
    }

    public void setVehicle(Vehicle vehicle)
    {
        this.vehicle = vehicle;
    }

    public int getUserID()
    {
        return userID;
    }

    public void setUserID(int userID)
    {
        this.userID = userID;
    }

    public Calendar getRegistrationdate()
    {
        return registrationdate;
    }

    public void setRegistrationdate(GregorianCalendar registrationdate)
    {
        this.registrationdate = registrationdate;
    }

    public Calendar getRegistrationExperationDate()
    {
        return registrationExperationDate;
    }

    public void setRegistrationExperationDate(GregorianCalendar registrationExperationDate)
    {
        this.registrationExperationDate = registrationExperationDate;
    }
    //endregion
}
