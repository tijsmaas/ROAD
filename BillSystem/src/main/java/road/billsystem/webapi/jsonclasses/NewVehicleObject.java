package road.billsystem.webapi.jsonclasses;

/**
 * Created by Niek on 24/05/14.
 *  Aidas 2014
 */
public class NewVehicleObject
{
    private String cartrackerID;
    private String licensePlate;
    private int movementUserID;

    public NewVehicleObject(){};

    public int getMovementUserID()
    {
        return movementUserID;
    }

    public void setMovementUserID(int movementUserID)
    {
        this.movementUserID = movementUserID;
    }

    public String getLicensePlate()
    {
        return licensePlate;
    }

    public void setLicensePlate(String licensePlate)
    {
        this.licensePlate = licensePlate;
    }

    public String getCartrackerID()
    {
        return cartrackerID;
    }

    public void setCartrackerID(String cartrackerID)
    {
        this.cartrackerID = cartrackerID;
    }
}
