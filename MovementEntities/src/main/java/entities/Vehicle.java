package entities;

import java.util.List;

/**
 * Created by Niek on 14/03/14.
 */
public class Vehicle {
    private int id;
    private String licensePlate;
    private boolean isStolen;
    private List<VehicleOwnership> vehicleOwners;
}
