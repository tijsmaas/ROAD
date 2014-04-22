/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package domain.user;

import javax.annotation.PostConstruct;
import javax.ejb.Stateless;
import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;

/**
 *
 * @author Mitch
 */
@ApplicationScoped
public class BillService
{
    @PostConstruct
    private void init()
    {
        //driverClient.start();
    }
    
    public Object login(String username, String password)
    {
        return null;//driverClient.authenticate(username, password);
    }
}
