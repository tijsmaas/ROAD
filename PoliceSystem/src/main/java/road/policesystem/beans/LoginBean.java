package road.policesystem.beans;

import road.policesystem.utils.Utilities;
import road.movementdtos.dtos.MovementUserDto;
import road.policesystem.service.PoliceService;

import javax.enterprise.context.RequestScoped;
import javax.faces.context.ExternalContext;
import javax.faces.context.FacesContext;
import javax.inject.Inject;
import javax.inject.Named;
import java.io.IOException;

/**
 * Authenticate police and judge users.
 * @author Niek
 */
@Named("loginBean") @RequestScoped
public class LoginBean
{
    // Basic authentication parameters
    private String username;
    private String password;

    // If this is true then a login attempt has failed.
    private boolean failed = false;

    @Inject
    private UserBean userBean;

    @Inject
    private PoliceService policeService;

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

    /**
     * Authenticate the user check if the login is valid and redirect automatically.
     * @throws IOException
     */
    public void login() throws IOException
    {
        ExternalContext context = FacesContext.getCurrentInstance().getExternalContext();
        try
        {
            MovementUserDto user = policeService.login(username, password);
            this.failed = (user == null);
            if (!failed)
            {
                userBean.setLoggedinUser(user);
                // redirect the user if login is successful
                context.redirect(Utilities.getHostnameAndContext() + userBean.getLoginRedirect());
            }
        } catch(Exception ex)
        {
            ex.printStackTrace();
            this.failed = true;
        }
    }

    /**
     * Redirect an authenticated user to the 'home' screen.
     */
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
