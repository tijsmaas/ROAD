package road.movementdtos.dtos;

import java.util.Calendar;

/**
 * Created by Mitch on 21-5-2014.
 */
public class VehicleOwnerDto {
    private MovementUserDto user;
    private Calendar registrationdate;
    private Calendar registrationExperationDate;

    public VehicleOwnerDto() {
    }

    public VehicleOwnerDto(MovementUserDto user, Calendar registrationdate, Calendar registrationExperationDate) {
        this.user = user;
        this.registrationdate = registrationdate;
        this.registrationExperationDate = registrationExperationDate;
    }

    public MovementUserDto getUser() {
        return user;
    }

    public Calendar getRegistrationdate() {
        return registrationdate;
    }

    public Calendar getRegistrationExperationDate() {
        return registrationExperationDate;
    }
}
