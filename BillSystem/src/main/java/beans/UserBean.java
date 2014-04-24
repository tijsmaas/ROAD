/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package beans;

import aidas.usersystem.dto.UserDto;

import javax.enterprise.context.SessionScoped;
import javax.faces.context.FacesContext;
import javax.inject.Named;
import java.io.IOException;
import java.io.Serializable;
import java.util.logging.Level;
import java.util.logging.Logger;

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
        redirect("welcome.xhtml");
    }

    public UserDto getLoggedinUser()
    {
        return loggedinUser;
    }
    
    private void redirect(String url)
    {
        try {
            FacesContext.getCurrentInstance().getExternalContext().redirect("/BillSystem/" + url);
        } catch (IOException ex) {
            Logger.getLogger(UserBean.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    public void logout()
    {
        //TODO
    }
}
