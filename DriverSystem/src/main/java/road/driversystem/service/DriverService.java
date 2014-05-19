/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package road.driversystem.service;

import aidas.userservice.dto.UserDto;
import road.driverdts.connections.DriverClient;
import road.movementdtos.dtos.CityDistanceDto;
import road.movementdtos.dtos.InvoiceDto;
import road.movementdtos.dtos.VehicleDto;
import road.movementdtos.dtos.enumerations.PaymentStatus;

import javax.annotation.PostConstruct;
import javax.ejb.Singleton;
import javax.ejb.Startup;
import java.io.Serializable;
import java.util.ArrayList;
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

    public InvoiceDto getInvoiceWithDetails(int invoiceID){
        try
        {
            return driverQueries.getInvoiceDetails(invoiceID);
        } catch(Exception ex){
            ex.printStackTrace();
            return null;
        }
    }

    /**
     * Update the status of invoice with given ID to a new PaymentStatus
     * @param invoiceID The ID of the invoice to update
     * @param newStatus The newStatus
     * @return Success or unsuccessful
     */
    public boolean updateInvoiceStatus(int invoiceID, PaymentStatus newStatus)
    {
        return driverQueries.updateInvoicePaymentStatus(invoiceID, newStatus);
    }

    /**
     * get the City movements for a vehicleInvoice
     * @param vehicleInvoiceID the vehicleInvoiceID
     * @return
     */
    public List<CityDistanceDto> getCityMovements(int vehicleInvoiceID){
        //TODO: Implement this method
        List<CityDistanceDto> cityMovements = new ArrayList<>();
        cityMovements.add(new CityDistanceDto(1, "Eindhoven", 300, 0.16));
        cityMovements.add(new CityDistanceDto(2, "Helmond", 3200, 0.18));
        cityMovements.add(new CityDistanceDto(3, "Tilburg", 9000, 0.19));
        cityMovements.add(new CityDistanceDto(4, "Veldhoven", 1200, 0.12));


        return cityMovements;
    }

    /**
     * Change the password of the provided user.
     * @param id the identifier of the user.
     * @param oldPassword the current password of the user.
     * @param newPassword the new password of the user.
     * @param newPasswordValidate the new password of the user again to validate if they are the same.
     * @return null if the change was successful, otherwise return the error.
     */
    public String changePassword(int id, String oldPassword, String newPassword, String newPasswordValidate) {
        return driverQueries.changePassword(id, oldPassword, newPassword, newPasswordValidate);
    }

    /**
     * Change the details of the provided user.
     * @param id the identifier of the user.
     * @param name the name of the user.
     * @param street the street in which the user lives.
     * @param houseNumber the number of the house in the street in which the user lives.
     * @param postalCode the postal code in which the user lives.
     * @param city the city in which the user lives.
     * @return true if changing the details was successful, otherwise false.
     */
    public boolean changeDetails(int id, String name, String street, String houseNumber, String postalCode, String city) {
        Boolean result = driverQueries.changeDetails(id, name, street, houseNumber, postalCode, city);
        return result != null ? result.booleanValue() : false;
    }
}
