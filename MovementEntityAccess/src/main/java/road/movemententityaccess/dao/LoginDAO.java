package road.movemententityaccess.dao;

import road.movemententities.entities.MovementUser;

/**
 * Created by geh on 20-5-14.
 */
public interface LoginDAO
{
    public MovementUser register(String userName, String email);

    public MovementUser getUser(String userName);

    public MovementUser getUser(int id);

    public MovementUser update(Integer id, String name, String street, String houseNumber, String postalCode, String city, Boolean invoiceNotification);
}
