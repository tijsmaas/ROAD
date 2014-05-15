package road.movemententities.entities;

import javax.persistence.*;
import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.List;

/**
 * The VehicleOwnership class is used to specify the current and previous owner for a vehicle
 *
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

    /**
     * Specifies if the user wants the car movements to be used in the traffic jam calculations.
     */
    private boolean contributeGPSData;

    private Calendar registrationdate;

    private Calendar registrationExperationDate;

    @OneToMany(mappedBy = "id")
    private List<VehicleInvoice> vehicleInvoices;

    // Empty constructor for JPA
    public VehicleOwnership() { }

    public VehicleOwnership(Vehicle vehicle, int userID, Calendar registrationdate, Calendar registrationExperationDate) {
        this.vehicle = vehicle;
        this.userID = userID;
        this.registrationdate = registrationdate;
        this.registrationExperationDate = registrationExperationDate;
    }

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

    public boolean getContributeGPSData() { return contributeGPSData; }

    public void setContributeGPSData(boolean contributeGPSData) { this.contributeGPSData = contributeGPSData; }
    //endregion
}
