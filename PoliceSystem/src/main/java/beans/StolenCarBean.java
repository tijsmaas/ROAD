package beans;

import road.movementdtos.dtos.StolenCarDto;
import road.policesystem.dts.PoliceService;

import javax.enterprise.context.SessionScoped;
import javax.inject.Inject;
import javax.inject.Named;
import java.io.Serializable;
import java.util.List;

/**
 * Created by Mitch on 21-5-2014.
 */
@Named
@SessionScoped
public class StolenCarBean implements Serializable{
    @Inject
    private PoliceService policeService;

    private String licensePlate;

    public String getLicensePlate() {
        return licensePlate;
    }

    public void setLicensePlate(String licensePlate) {
        this.licensePlate = licensePlate;
    }
    
    public List<StolenCarDto> getStolenCars() {
        return policeService.getAllStolenCars();
    }

    public StolenCarDto getStolenCarByLicensePlate()
    {
        return policeService.getStolenCarByLicensePlate(licensePlate);
    }
}
