package road.billsystem.beans;

import road.billsystem.service.BillService;
import road.movementdtos.dtos.VehicleDto;
import road.movementdtos.dtos.VehicleOwnerDto;

import javax.annotation.PostConstruct;
import javax.faces.bean.ViewScoped;
import javax.faces.model.SelectItem;
import javax.inject.Inject;
import javax.inject.Named;
import java.util.List;

/**
 * Created by Niek on 21/05/14.
 * © Aidas 2014
 */

@Named
@ViewScoped
public class CarTrackerBean
{
    @Inject
    private BillService service;

    @Inject
    private UserBean usersession;

    private VehicleDto selectedVehicle;
    private List<VehicleDto> availableVehicles;

    private List<VehicleOwnerDto> currentVehicleOwnerships;


    @PostConstruct
    public void init()
    {
        this.availableVehicles = service.getExistingVehicles();
    }


    public SelectItem[] getVehicles()
    {

        SelectItem[] items = new SelectItem[this.availableVehicles.size()];


        for (int i = 0; i < this.availableVehicles.size(); i++)
        {
            items[i] = new SelectItem(this.availableVehicles.get(i), this.availableVehicles.get(i).getLicensePlate());
        }

        return items;

    }


    public VehicleDto getSelectedVehicle()
    {
        return selectedVehicle;
    }

    public void setSelectedVehicle(VehicleDto selectedVehicle)
    {
        this.selectedVehicle = selectedVehicle;
    }

    public void vehicleChanged()
    {
        this.currentVehicleOwnerships = this.service.getVehicleOwnerships(this.selectedVehicle.getVehicleID());
    }
}
