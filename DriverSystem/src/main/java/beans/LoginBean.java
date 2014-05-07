package beans;

import aidas.userservice.dto.UserDto;
import domain.dts.IDriverService;

import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;
import javax.inject.Named;

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
    private boolean failed;
    @Inject
    private UserBean userBean;
    @Inject
    private IDriverService driverService;

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

    public void login()
    {
        System.out.println("Authenticating " + username);
        UserDto user = driverService.login(username, password);
        this.failed = (user == null);
        if(!failed) {
            userBean.setLoggedinUser(user);
        }
    }
}
