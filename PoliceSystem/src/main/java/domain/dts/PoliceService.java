/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package domain.dts;

import aidas.usersystem.dto.UserDto;
import connections.PoliceClientConnection;
import javax.annotation.PostConstruct;
import javax.ejb.Stateless;
import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import qualifier.ProducerQualifier;

/**
 *
 * @author Mitch
 */
@ApplicationScoped
public class PoliceService
{
    @Inject @ProducerQualifier
    PoliceClientConnection policeClient;
    
    @PostConstruct
    private void init()
    {
        policeClient.start();
    }
    
    public UserDto login(String username, String password)
    {
        return policeClient.authenticate(username, password);
    }
}
