/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package beans;

import aidas.usersystem.dto.UserDto;
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
    private UserDto loggedinUser;

    public void setLoggedinUser(UserDto loggedinUser)
    {
        this.loggedinUser = loggedinUser;
    }

    public UserDto getLoggedinUser()
    {
        return loggedinUser;
    }
    
    public void logout()
    {
        //TODO
    }
}
