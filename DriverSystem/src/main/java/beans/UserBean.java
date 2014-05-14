/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package beans;

import aidas.userservice.dto.UserDto;
import domain.infoobjects.PaymentSession;
import utils.Utlities;

import javax.ejb.SessionBean;
import javax.enterprise.context.SessionScoped;
import javax.faces.context.ExternalContext;
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
@Named("userBean") @SessionScoped
public class UserBean implements Serializable
{
    private UserDto loggedinUser;

    private String loginRedirect = " ";
    private PaymentSession paymentSession;


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
        try {
            ExternalContext ec = FacesContext.getCurrentInstance().getExternalContext();
            ec.invalidateSession();
            ec.redirect(Utlities.getContextRoot() + "/login/");

        } catch (IOException e) {
            Logger.getLogger(SessionBean.class.getName()).log(Level.SEVERE, null, e);
        }
    }

    public String getLoginRedirect()
    {
        return loginRedirect;
    }

    public void redirectIfNotLoggedIn()
    {

       ExternalContext context = FacesContext.getCurrentInstance().getExternalContext();
        if(this.loggedinUser == null){
            try
            {
                this.loginRedirect = Utlities.getContextRoot();
                context.redirect(Utlities.getContextRoot() + "/login/");

            } catch (IOException e)
            {
                e.printStackTrace();
            }
        }
    }

    public void setPaymentSession(PaymentSession session){
        this.paymentSession = session;
    }

    public PaymentSession getAndClearPaymentSession(){
        PaymentSession toReturn = this.paymentSession;
        this.paymentSession = null;
        return toReturn;
    }
}
