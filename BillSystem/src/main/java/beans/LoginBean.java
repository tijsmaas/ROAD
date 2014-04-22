package beans;

import domain.user.BillService;
import javax.ejb.Stateless;
import javax.faces.bean.ManagedProperty;
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
@Named
@Stateless
public class LoginBean
{
    private String username;
    private String password;
    @ManagedProperty(value="#{userBean}")
    private UserBean userBean;
    @Inject
    private BillService driverService;

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
    
    public void login()
    {
        userBean.setLoggedinUser(driverService.login(username, password));
    }
}
