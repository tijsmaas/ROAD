/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package domain.user;

import aidas.usersystem.dto.UserDto;
import connections.DriverClientConnection;
import javax.annotation.PostConstruct;
import javax.ejb.Stateless;
import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;

/**
 *
 * @author Mitch
 */
@ApplicationScoped
public class DriverService
{
    @Inject
    DriverClientConnection driverClient;

    @PostConstruct
    private void init()
    {
        driverClient.start();
    }
    
    public UserDto login(String username, String password)
    {
        return driverClient.authenticate(username, password);
    }
}
