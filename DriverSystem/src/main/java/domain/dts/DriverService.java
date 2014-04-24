/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package domain.dts;

import aidas.usersystem.dto.UserDto;
import connections.DriverClientConnection;
import javax.annotation.PostConstruct;
import javax.ejb.Stateless;
import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import javax.inject.Named;
import qualifier.ProducerQualifier;

/**
 *
 * @author Mitch
 */
@Named
@ApplicationScoped
public class DriverService
{
    //@Inject @ProducerQualifier
    DriverClientConnection driverClient;

    @PostConstruct
    private void init()
    {
        driverClient = new DriverClientConnection();
        driverClient.start();
    }
    
    public UserDto login(String username, String password)
    {
        Integer x = driverClient.getLaneCount();
        return driverClient.authenticate(username, password);
    }
}
