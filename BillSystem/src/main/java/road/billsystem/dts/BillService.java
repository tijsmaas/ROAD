/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package road.billsystem.dts;

import aidas.userservice.dto.UserDto;
import road.billdts.connections.BillClient;
import road.movemententities.entities.City;

import javax.annotation.PostConstruct;
import javax.enterprise.context.ApplicationScoped;
import java.io.Serializable;
import java.util.Date;
import java.util.List;

/**
 *
 * @author Mitch
 */
@ApplicationScoped
public class BillService implements Serializable
{
    //@Inject @ProducerQualifier
    BillClient billClient;

    @PostConstruct
    private void init()
    {
        billClient = new BillClient();
        billClient.start();
    }
    
    public UserDto login(String username, String password)
    {
        return billClient.authenticate(username, password);
    }

    public boolean adjustKilometerRate(City city, Date addDate, String price)
    {
        return billClient.adjustKilometerRate(city, addDate, price);
    }

    public List<City> getCities() {
        return billClient.getCities();
    }
}
