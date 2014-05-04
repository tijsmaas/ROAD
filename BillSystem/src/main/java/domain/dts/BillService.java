/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package domain.dts;

import aidas.userservice.dto.UserDto;
import road.billdts.connections.BillClientConnection;

import javax.annotation.PostConstruct;
import javax.enterprise.context.ApplicationScoped;

/**
 *
 * @author Mitch
 */
@ApplicationScoped
public class BillService
{
    //@Inject @ProducerQualifier
    BillClientConnection billClient;

    @PostConstruct
    private void init()
    {
        billClient = new BillClientConnection();
        billClient.start();
    }
    
    public UserDto login(String username, String password)
    {
        return billClient.authenticate(username, password);
    }
}
