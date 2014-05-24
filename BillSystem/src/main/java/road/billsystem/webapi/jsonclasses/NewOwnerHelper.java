package road.billsystem.webapi.jsonclasses;

/**
 * Created by Niek on 24/05/14.
 * © Aidas 2014
 */
public class NewOwnerHelper
{
    private int vehicleID;
    private int newOwnerID;

    public NewOwnerHelper()
    {
    }



    public int getVehicleID()
    {
        return vehicleID;
    }

    public void setVehicleID(int vehicleID)
    {
        this.vehicleID = vehicleID;
    }

    public int getNewOwnerID()
    {
        return newOwnerID;
    }

    public void setNewOwnerID(int newOwnerID)
    {
        this.newOwnerID = newOwnerID;
    }
}
