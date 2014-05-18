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
     * The licence plate of the vehicle.
     */
    private String licensePlate;

    /**
     * Create a new instance of the {@link VehicleDto} class.
     * @param licensePlate the licence plate of the vehicle.
     * @param contributeGPSData if the vehicle movements are used in the traffic jam calculations.
     */
    public VehicleDto(String licensePlate, boolean contributeGPSData) {
        this.licensePlate = licensePlate;
        this.contributeGPSData = contributeGPSData;
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
}
