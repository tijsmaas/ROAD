package road.movementdtos.dtos;

import java.util.Calendar;
import java.util.Date;

/**
 * Created by Mitch on 21-5-2014.
 */
public class VehicleOwnerDto {
    private MovementUserDto user;
    private Date registrationdate;
    private Date registrationExperationDate;

    public VehicleOwnerDto() {
    }

    public VehicleOwnerDto(MovementUserDto user, Date registrationdate, Date registrationExperationDate) {
        this.user = user;
        this.registrationdate = registrationdate;
        this.registrationExperationDate = registrationExperationDate;
    }

    public MovementUserDto getUser() {
        return user;
    }

    public Date getRegistrationdate() {
        return registrationdate;
    }

    public Date getRegistrationExperationDate() {
        return registrationExperationDate;
    }
}
