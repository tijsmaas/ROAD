/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package road.driversystem.domain.dts;

import aidas.userservice.dto.UserDto;
import road.driverdts.connections.DriverClient;

import javax.annotation.PostConstruct;
import javax.ejb.Singleton;
import javax.ejb.Startup;

/**
 *
 * @author Mitch
 */
@Singleton @Startup
public class DriverService
{
    private DriverClient driverQueries;

    public DriverService()
    {

    }

    @PostConstruct
    private void init()
    {
        this.driverQueries = new DriverClient();
        this.driverQueries.start();
    }

    public UserDto login(String username, String password)
    {
        UserDto result = driverQueries.authenticate(username, password);
        return result;
    }
}
