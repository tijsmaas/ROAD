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
public class ContributeVehicleBean implements Serializable {

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
    private VehicleDto curVehicleEdit;

    /**
     * The backup value of the vehicle data that is being edited or null if there is no backup.
     */
    private Boolean editVehicleBackup;

    /**
     * Create a new instance of the {@link ContributeVehicleBean} bean.
     */
    public ContributeVehicleBean() { }

    @PostConstruct
    public void initContributeCarBean() {
        Integer userId = new Integer(this.userSession.getLoggedinUser().getId());
        this.vehicles = driverService.getVehicles(userId);

        // TODO: Remove, testing only.
        this.vehicles = new ArrayList();
        this.vehicles.add(new VehicleDto("AA-11-BB", true));
        this.vehicles.add(new VehicleDto("12-CD-AE", true));
    }

    /**
     * Get the vehicles of the current user.
     * @return the vehicles.
     */
    public List<VehicleDto> getVehicles() {
        return this.vehicles;
    }

    /**
     * Check if the provided vehicle is being edited.
     * @param vehicle the vehicle to check.
     * @return true if the provided vehicle is being edited, otherwise false.
     */
    public boolean isVehicleEdit(VehicleDto vehicle) {
        return this.curVehicleEdit == vehicle;
    }

    /**
     * Change the vehicle which is being edited.
     * @param vehicle the vehicle to be edited or null if none is to be selected.
     */
    public void changeVehicleEdit(VehicleDto vehicle) {
        this.curVehicleEdit = vehicle;
        this.editVehicleBackup =  vehicle != null ? vehicle.getContributeGPSData() : null;
    }

    /**
     * Save the {@link #curVehicleEdit}.
     */
    public void saveEditVehicle() {
        if (this.curVehicleEdit != null) {
            this.driverService.updateVehicle(this.curVehicleEdit);
        }

        this.changeVehicleEdit(null);
    }

    /**
     *
     */
    public void cancelEdit() {
        if (this.editVehicleBackup != null) {
            this.curVehicleEdit.setContributeGPSData(this.editVehicleBackup.booleanValue());
        }

        this.changeVehicleEdit(null);
    }
}
