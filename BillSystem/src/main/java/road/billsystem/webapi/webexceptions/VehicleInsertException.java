package road.billsystem.webapi.webexceptions;

/**
 * Created by Niek on 24/05/14.
 * © Aidas 2014
 */
public class VehicleInsertException extends Exception
{
    public VehicleInsertException(String error){
        super(error);
    }
}
