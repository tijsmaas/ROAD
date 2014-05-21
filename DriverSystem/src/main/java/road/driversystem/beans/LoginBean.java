package road.driversystem.beans;

import road.movementdtos.dtos.MovementUserDto;
import road.userservice.dto.UserDto;
import road.driversystem.service.DriverService;
import road.driversystem.utils.Utlities;

import javax.enterprise.context.RequestScoped;
import javax.faces.context.ExternalContext;
import javax.faces.context.FacesContext;
import javax.inject.Inject;
import javax.inject.Named;
import java.io.IOException;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author Mitch
 */
@Named("loginBean") @RequestScoped
public class LoginBean
{
    private String username;
    private String password;
    private boolean failed = false;

    @Inject
    private UserBean userBean;

    @Inject
    private DriverService driverService;

    public void setUsername(String username)
    {
        this.username = username;
    }
    
    public String getUsername()
    {
        return username;
    }

    public void setPassword(String password)
    {
        this.password = password;
    }

    public String getPassword()
    {
        return password;
    }

    public boolean isFailed()
    {
        return failed;
    }

    public void setFailed(boolean failed)
    {
        this.failed = failed;
    }

    public void login() throws IOException
    {
        ExternalContext context = FacesContext.getCurrentInstance().getExternalContext();
        try
        {
            MovementUserDto user = driverService.login(username, password);
            this.failed = (user == null);
            if (!failed)
            {
                userBean.setLoggedinUser(user);
                context.redirect(Utlities.getHostnameAndContext() + userBean.getLoginRedirect());
            }
        } catch(Exception ex)
        {
            ex.printStackTrace();
            this.failed = true;
        }
    }

    public void redirectIfLoggedIn()
    {
        ExternalContext context = FacesContext.getCurrentInstance().getExternalContext();

        try
        {
            if(this.userBean.getLoggedinUser() != null)
            {
                context.redirect(userBean.getLoginRedirect());
            }
        } catch (IOException e)
        {
            e.printStackTrace();
        }

    }
}
