package road.policesystem.beans;

import road.movementdtos.dtos.VehicleDto;
import road.policesystem.service.PoliceService;

import javax.enterprise.context.SessionScoped;
import javax.inject.Inject;
import javax.inject.Named;
import java.io.Serializable;

/**
 * Created by Mitch on 21-5-2014.
 */
@Named
@SessionScoped
public class JudgeBean implements Serializable
{
    @Inject
    private PoliceService policeService;

    private String licensePlate;
    private VehicleDto vehicle;
    private boolean isStolen;

    public void getVehicle()
    {
        vehicle = policeService.getVehicleByLicensePlate(licensePlate);
    }

    public void SetStolen()
    {
        isStolen = policeService.setStolen(vehicle, isStolen);
    }
}
