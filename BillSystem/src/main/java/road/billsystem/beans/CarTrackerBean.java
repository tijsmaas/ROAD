package road.billsystem.beans;

import road.billsystem.service.BillService;
import road.movementdtos.dtos.VehicleDto;
import road.movementdtos.dtos.VehicleOwnerDto;

import javax.annotation.PostConstruct;
import javax.faces.bean.RequestScoped;
import javax.faces.model.SelectItem;
import javax.inject.Inject;
import javax.inject.Named;
import java.util.List;

/**
 * Bean for the Cartracker manager
 *
 * Created by Niek on 21/05/14.
 * © Aidas 2014
 */

@Named
@RequestScoped
public class CarTrackerBean
{
    @Inject
    private BillService service;

    @Inject
    private UserBean usersession;

    private List<VehicleDto> availableVehicles;

    private List<VehicleOwnerDto> currentVehicleOwnerships;


    /**
     * Get all the existing vehicles to fill the dropdowns with
     */
    @PostConstruct
    public void init()
    {
        this.availableVehicles = service.getExistingVehicles();
    }


    /**
     * Create an array of select items from the current vehicles
     * @return
     */
    public SelectItem[] getVehicles()
    {

        SelectItem[] items = new SelectItem[this.availableVehicles.size()];


        for (int i = 0; i < this.availableVehicles.size(); i++)
        {
            items[i] = new SelectItem(this.availableVehicles.get(i).getVehicleID(), this.availableVehicles.get(i).getLicensePlate());
        }

        return items;

    }
}
