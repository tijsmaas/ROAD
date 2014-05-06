/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package domain.dts;

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
public class DriverService implements IDriverService
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

    @Override
    public UserDto login(String username, String password)
    {
        Long x = driverQueries.getLaneCount();
        UserDto result = driverQueries.authenticate(username, password);
        return result;
    }
}
