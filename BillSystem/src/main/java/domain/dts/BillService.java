/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package domain.dts;

import aidas.usersystem.dto.UserDto;
import connections.BillClientConnection;
import producer.ProducerQualifier;

import javax.annotation.PostConstruct;
import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;

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