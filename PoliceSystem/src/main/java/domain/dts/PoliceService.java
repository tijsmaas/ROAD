/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package domain.dts;

import aidas.userservice.dto.UserDto;
import road.policedts.connections.PoliceClientConnection;
import javax.annotation.PostConstruct;
import javax.enterprise.context.ApplicationScoped;

/**
 *
 * @author Mitch
 */
@ApplicationScoped
public class PoliceService
{
    //@Inject @ProducerQualifier
    PoliceClientConnection policeClient;
    
    @PostConstruct
    private void init()
    {
        policeClient = new PoliceClientConnection();
        policeClient.start();
    }
    
    public UserDto login(String username, String password)
    {
        return policeClient.authenticate(username, password);
    }
}
