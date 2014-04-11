/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package beans;

import domain.user.UserDTO;
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
    private UserDTO loggedinUser;

    public void setLoggedinUser(UserDTO loggedinUser)
    {
        this.loggedinUser = loggedinUser;
    }

    public UserDTO getLoggedinUser()
    {
        return loggedinUser;
    }
    
    public void logout()
    {
        //TODO
    }
}
