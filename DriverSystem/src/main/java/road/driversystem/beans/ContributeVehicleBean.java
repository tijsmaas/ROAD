/*
 * Copyright by AIDaS.
 */

package road.driversystem.beans;

import road.driversystem.domain.dts.DriverService;
import road.movementdtos.dtos.VehicleDto;

import javax.annotation.PostConstruct;
import javax.enterprise.context.SessionScoped;
import javax.inject.Inject;
import javax.inject.Named;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/**
 * This bean represents all functionalities related to the vehicle management of the current user.
 *
 * @author Geert
 */
@SessionScoped
@Named("contributeVehicleBean")
public class ContributeVehicleBean implements Serializable
{

    @Inject
    private UserBean userSession;

    @Inject
    private DriverService driverService;

    /**
     * The vehicles linked to the current user.
     */
    private List<VehicleDto> vehicles;

    /**
     * The vehicle which is currently edited, or null if no vehicle is currently being edit.
     */
    private VehicleDto curEditVehicle;

    /**
     * The value indicating if the {@link #curEditVehicle} has the contribute GPS selected or not.
     */
    private boolean curEditVehicleContributeGPS;

    /**
     * Create a new instance of the {@link ContributeVehicleBean} bean.
     */
    public ContributeVehicleBean() { }

    @PostConstruct
    public void initContributeCarBean()
    {
        this.vehicles = driverService.getVehicles(this.userSession.getLoggedinUser().getId());
    }

    /**
     * Get the vehicles of the current user.
     * @return the vehicles.
     */
    public List<VehicleDto> getVehicles()
    {
        return this.vehicles;
    }

    /**
     * Check if the provided vehicle is being edited.
     * @param vehicle the vehicle to check.
     * @return true if the provided vehicle is being edited, otherwise false.
     */
    public boolean isVehicleEdit(VehicleDto vehicle)
    {
        return this.curEditVehicle == vehicle;
    }

    /**
     * Change the vehicle which is being edited.
     * @param vehicle the vehicle to be edited or null if none is to be selected.
     */
    public void changeVehicleEdit(VehicleDto vehicle)
    {
        this.curEditVehicle = vehicle;
        this.curEditVehicleContributeGPS =  vehicle != null ? vehicle.getContributeGPSData() : null;
    }

    /**
     * Get if the current edit vehicle will contribute its GPS data.
     * @return if the current edit vehicle will contribute its GPS data.
     */
    public boolean isCurEditVehicleContributeGPS()
    {
        return this.curEditVehicleContributeGPS;
    }

    /**
     * Set if the current edit vehicle will contribute its GPS data.
     * @param vehicleContributeGPS if the current edit vehicle will contribute its GPS data.
     */
    public void setCurEditVehicleContributeGPS(boolean vehicleContributeGPS)
    {
        this.curEditVehicleContributeGPS = vehicleContributeGPS;
    }

    /**
     * Save the {@link #curEditVehicle}.
     */
    public void saveEditVehicle()
    {
        if (this.curEditVehicle != null)
        {
            this.curEditVehicle.setContributeGPSData(this.curEditVehicleContributeGPS);
            this.driverService.updateVehicle(this.curEditVehicle);
        }

        this.changeVehicleEdit(null);
    }
}
