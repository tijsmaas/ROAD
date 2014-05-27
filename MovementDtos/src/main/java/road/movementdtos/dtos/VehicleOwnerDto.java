package road.movementdtos.dtos;

import java.util.Date;

/**
 * Created by Mitch on 21-5-2014.
 */
public class VehicleOwnerDto {

    /**
     * The owner.
     */
    private MovementUserDto user;

    /**
     * The date when the ownership was registered.
     */
    private Date registrationdate;

    /**
     * The date when the ownership will expire.
     */
    private Date registrationExperationDate;

    /**
     * Creates a new instance of the {@link VehicleOwnerDto} class.
     */
    public VehicleOwnerDto() {}

    /**
     * Creates a new instance of the {@link VehicleOwnerDto} class.
     * @param user The owner.
     * @param registrationdate The date when the ownership was registered.
     * @param registrationExperationDate The date when the ownership will expire.
     */
    public VehicleOwnerDto(MovementUserDto user, Date registrationdate, Date registrationExperationDate) {
        this.user = user;
        this.registrationdate = registrationdate;
        this.registrationExperationDate = registrationExperationDate;
    }

    /**
     * Get the {@link #user} of the {@link VehicleOwnerDto}.
     * @return The owner.
     */
    public MovementUserDto getUser() {
        return user;
    }

    /**
     * Get the {@link #registrationdate} of the {@link VehicleOwnerDto}.
     * @return The date when the ownership was registered.
     */
    public Date getRegistrationdate() {
        return registrationdate;
    }

    /**
     * Get the {@link #registrationExperationDate} of the {@link VehicleOwnerDto}.
     * @return The date when the ownership will expire.
     */
    public Date getRegistrationExperationDate() {
        return registrationExperationDate;
    }

    /**
     * Set the {@link #user} of the {@link VehicleOwnerDto}.
     * @param user The owner.
     */
    public void setUser(MovementUserDto user)
    {
        this.user = user;
    }

    /**
     * Set the {@link #registrationdate} of the {@link VehicleOwnerDto}.
     * @param registrationdate The date when the ownership was registered.
     */
    public void setRegistrationdate(Date registrationdate)
    {
        this.registrationdate = registrationdate;
    }

    /**
     * Set the {@link #registrationExperationDate} of the {@link VehicleOwnerDto}.
     * @param registrationExperationDate The date when the ownership will expire.
     */
    public void setRegistrationExperationDate(Date registrationExperationDate)
    {
        this.registrationExperationDate = registrationExperationDate;
    }
}
