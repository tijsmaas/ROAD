/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package road.driversystem.domain.dts;

import aidas.userservice.dto.UserDto;
import road.driverdts.connections.DriverClient;
import road.movementdtos.dtos.InvoiceDto;
import road.movementdtos.dtos.VehicleDto;

import javax.annotation.PostConstruct;
import javax.ejb.Singleton;
import javax.ejb.Startup;
import java.io.Serializable;
import java.util.List;

/**
 *
 * @author Mitch
 */
@Singleton @Startup
public class DriverService implements Serializable
{
    private DriverClient driverQueries;

    public DriverService()
    {

    }

    @PostConstruct
    private void init()
    {
        this.driverQueries = new DriverClient();
        this.driverQueries.start();
    }

    public UserDto login(String username, String password)
    {
        UserDto result = driverQueries.authenticate(username, password);
        return result;
    }

    /**
     * Get the vehicles linked to the specified user.
     * @param userId the identifier of the user.
     * @return the vehicles linked to the specified user.
     */
    public List<VehicleDto> getVehicles(Integer userId) {
        List<VehicleDto> result = driverQueries.getVehicles(userId);
        return result;
    }

    /**
     * Update the provided vehicle.
     * @param vehicleDto the vehicle to be updated.
     * @return if the function was successful.
     */
    public boolean updateVehicle(VehicleDto vehicleDto) {
        Boolean result = driverQueries.updateVehicle(vehicleDto);
        return result != null ? result.booleanValue() : false;
    }

    public List<InvoiceDto> getUserInvoices(int userID){
        return driverQueries.getUserInvoices(userID);
    }
}
