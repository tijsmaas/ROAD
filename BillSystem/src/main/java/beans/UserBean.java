/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package beans;

import java.io.Serializable;
import javax.enterprise.context.SessionScoped;
import javax.inject.Named;

/**
 *
 * @author Mitch
 */
@Named
@SessionScoped
public class UserBean implements Serializable
{
    private Object loggedinUser;

    public void setLoggedinUser(Object loggedinUser)
    {
        this.loggedinUser = loggedinUser;
    }

    public Object getLoggedinUser()
    {
        return loggedinUser;
    }
    
    public void logout()
    {
        //TODO
    }
}
