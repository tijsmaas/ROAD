package road.billsystem.service;

import road.billdts.connections.BillClient;
import road.billdts.dto.InvoiceSearchQuery;
import road.billsystem.webapi.webexceptions.ChangeOwnerFailedException;
import road.movementdtos.dtos.*;
import road.movementdtos.dtos.enumerations.PaymentStatus;

import javax.annotation.PostConstruct;
import javax.ejb.Schedule;
import javax.ejb.Singleton;
import javax.ejb.Startup;
import javax.ejb.Timer;
import java.io.Serializable;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

/**
 * Created by Niek on 14/05/14.
 * © Aidas 2014
 */
@Singleton
@Startup
public class BillService implements Serializable
{
    private BillClient billClient;
    private boolean didSchedule = false;

    @PostConstruct
    private void init()
    {
        this.billClient = new BillClient();
        billClient.start();
    }


    /**
     * Timer for generating the monthly invoices after each month, automatically.
     * @param t
     */
    @Schedule(month="*", info="Generate monthly invoices") //The scheduling goes off every month
    public void generateMonthlyInvoice(Timer t)
    {
        Calendar cal = Calendar.getInstance();
        int year = cal.get(Calendar.YEAR);
        int month = cal.get(Calendar.MONTH);
        this.generateMonthlyInvoices(month, year);
    }


    /**
     * Generate the monthly invoices for the month and year specified
     * @param month Month for which you want to generate invoices
     * @param year Year for which you want to generate invoices
     * @return The number of invoices generated
     */
    public int generateMonthlyInvoices(int month, int year)
    {
        Integer result = billClient.generateMonthlyInvoices(month, year);
        System.out.println("result: " + result);
        return result;
    }

    public MovementUserDto login(String username, String password)
    {
        return billClient.authenticate(username, password);
    }

    public boolean adjustKilometerRate(CityDto city, Date addDate, String price)
    {
        return billClient.adjustKilometerRate(city, addDate, price);
    }

    /**x
     * Get a list of all available cities
     * @return List of CityDTOS
     */
    public List<CityDto> getCities() {
        return billClient.getCities();
    }

    /**
     * Get a list of invoices for user based on the search query
     * @param searchDetails
     * @return
     */
    public List<InvoiceDto> getInvoicesForSearchQuery(InvoiceSearchQuery searchDetails){
        return billClient.getInvoicesForSearch(searchDetails);
    }

    /**
     * get the City movements for a vehicleInvoice
     * @param vehicleInvoiceID the vehicleInvoiceID
     * @return
     */
    public List<CityDistanceDto> getCityMovements(int vehicleInvoiceID){
        return billClient.getCityDistances(vehicleInvoiceID);
    }

    /**
     * Get an invoiceDTO containing the details of an invoice
     * @param invoiceID
     * @return
     */
    public InvoiceDto getInvoiceWithDetails(int invoiceID){
        return billClient.getInvoiceDetails(invoiceID);
    }

    /**
     * Update the status of invoice with given ID to a new PaymentStatus
     * @param invoiceID The ID of the invoice to update
     * @param newStatus The newStatus
     * @return Success or unsuccessful
     */
    public boolean updateInvoiceStatus(int invoiceID, PaymentStatus newStatus)
    {
        return billClient.updateInvoicePaymentStatus(invoiceID, newStatus);
    }

    /**
     * Get all the current vehicles in the database
     * @return list of found Vehicle DTOs
     */
    public List<VehicleDto> getExistingVehicles(){
        return billClient.getAllVehicles();
    }


    /**
     * Get all current users existing in the database
     * @return
     */
    public List<MovementUserDto> getAllUsers(){
        return billClient.getAllUsers();
    }

    public VehicleDto addVehicle(String carTrackerID, String licensePlate, int movementUserID){
        return billClient.addNewVehicle(carTrackerID, licensePlate, movementUserID);
    }

    /**
     * Get the details of the vehicle selected
     * @param vehicleID the ID of the vehicle to get the details of
     * @return Pair containing the vehicleDTo and the current owner data.
     */
    public VehicleDto getVehicleDetails(int vehicleID){
        return billClient.getVehicleDetails(vehicleID);
    }

    /**
     * Change the owner of a vehicle to the new owner ID
     * @param vehicleID the ID of the vehicle to change the owner of
     * @param newOwnerID the ID of the new owner
     * @return The new ownership DTO object
     */
    public VehicleOwnerDto changeVehicleOwner(int vehicleID, int newOwnerID) throws ChangeOwnerFailedException
    {
         VehicleOwnerDto newOwner =  billClient.changeVehicleOwner(vehicleID, newOwnerID);
        if(newOwner == null){
            throw new ChangeOwnerFailedException("An error occurred whilst changing the owner for this vehicle");
        }

        return newOwner;
    }
}
