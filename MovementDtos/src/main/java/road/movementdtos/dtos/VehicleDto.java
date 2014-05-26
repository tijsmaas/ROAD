/*
 * Copyright by AIDaS.
 */

package road.movementdtos.dtos;

/**
 * This class represents a vehicle.
 *
 * @author Geert
 */
public class VehicleDto {

    /**
     * Specifies if this vehicle movements are used in the traffic jam calculations.
     */
    private boolean contributeGPSData;

    /**
     * Specifies if this vehicle is stolen.
     */
    private boolean isStolen;

    /**
     * The licence plate of the vehicle.
     */
    private String licensePlate;

    /**
     * The ID of the vehicle
     */
    private int vehicleID;

    /**
     * The ID of the cartracker
     */
    private String cartrackerID;

    /**
     * Owner of the current vehicle
     */
    private VehicleOwnerDto vehicleOwner;

    /**
     * Create a new instance of the {@link VehicleDto} class.
     */
    public VehicleDto() {}

    /**
     * Check if the {@link VehicleDto} {@link #isStolen}.
     * @return Specification whether this vehicle is stolen.
     */
    public boolean isStolen() {
        return isStolen;
    }

    /**
     * Reports if the {@link VehicleDto} {@link #isStolen}.
     * @param isStolen Specification whether this vehicle is stolen.
     */
    public void setStolen(boolean isStolen) {
        this.isStolen = isStolen;
    }

    /**
     * Create a new instance of the {@link VehicleDto} class.
     * @param licensePlate the licence plate of the vehicle.
     * @param contributeGPSData if the vehicle movements are used in the traffic jam calculations.
     */
    public VehicleDto(int vehicleID, String licensePlate, boolean contributeGPSData, boolean isStolen, String cartrackerID) {
        this.licensePlate = licensePlate;
        this.contributeGPSData = contributeGPSData;
        this.isStolen = isStolen;
        this.vehicleID = vehicleID;
        this.cartrackerID = cartrackerID;
    }

    /**
     * Create a new instance of the {@link VehicleDto} class.
     * @param licensePlate the licence plate of the vehicle.
     * @param contributeGPSData if the vehicle movements are used in the traffic jam calculations.
     */
    public VehicleDto(int vehicleID, String licensePlate, boolean contributeGPSData, boolean isStolen, VehicleOwnerDto vehicleOwner) {
        this.licensePlate = licensePlate;
        this.contributeGPSData = contributeGPSData;
        this.isStolen = isStolen;
        this.vehicleID = vehicleID;
        this.vehicleOwner = vehicleOwner;
    }

    /**
     * Get the {@link #contributeGPSData} value.
     * @return if the vehicle contributes in the traffic jam calculations.
     */
    public boolean getContributeGPSData() { return this.contributeGPSData; }

    /**
     * Set the {@link #contributeGPSData} value.
     * @param contributeGPSData if the vehicle movements are used in the traffic jam calculations.
     */
    public void setContributeGPSData(boolean contributeGPSData) { this.contributeGPSData = contributeGPSData; }

    /**
     * Get the {@link #licensePlate} value.
     * @return the licence plate of the vehicle.
     */
    public String getLicensePlate() { return this.licensePlate; }

    /**
     * Set the {@link #licensePlate} value.
     * @param licensePlate the licence plate of the vehicle.
     */
    public void setLicensePlate(String licensePlate) { this.licensePlate = licensePlate; }

    /**
     * set the {@link #vehicleID} value
     * @return the ID of the vehicle
     */
    public int getVehicleID()
    {
        return vehicleID;
    }

    /**
     * set the {@link #vehicleID} value
     * @param vehicleID the new vehicleID to set
     */
    public void setVehicleID(int vehicleID)
    {
        this.vehicleID = vehicleID;
    }

    /**
     * Get the {@link #vehicleOwner} of the {@link VehicleDto}.
     * @return Owner of the current vehicle
     */
    public VehicleOwnerDto getVehicleOwner()
    {
        return vehicleOwner;
    }

    /**
     * Set the {@link #vehicleOwner} of the {@link VehicleDto}.
     * @param vehicleOwner Owner of the current vehicle
     */
    public void setVehicleOwner(VehicleOwnerDto vehicleOwner)
    {
        this.vehicleOwner = vehicleOwner;
    }

    /**
     * Get the {@link #cartrackerID} of the {@link VehicleDto}.
     * @return The ID of the cartracker
     */
    public String getCartrackerID()
    {
        return cartrackerID;
    }

    /**
     * Set the {@link #cartrackerID} of the {@link VehicleDto}.
     * @param cartrackerID The ID of the cartracker
     */
    public void setCartrackerID(String cartrackerID)
    {
        this.cartrackerID = cartrackerID;
    }

}
