/*
 * Copyright by AIDaS.
 */

package road.driversystem.beans;

import road.driversystem.service.DriverService;
import road.movementdtos.dtos.MovementUserDto;

import javax.annotation.PostConstruct;
import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;
import javax.inject.Named;
import java.io.Serializable;

/**
 * This bean represents all functionalities related to the account management of the current user.
 *
 * @author Geert
 */
@Named("profileBean")
@RequestScoped
public class ProfileBean implements Serializable
{

    @Inject
    private UserBean userSession;

    @Inject
    private DriverService driverService;

    private boolean passwordSuccessful;
    private String passwordErrorMessage;
    private String oldPassword;
    private String newPassword1;
    private String newPassword2;

    private boolean detailsSuccessful;
    private String detailErrorMessage;
    private String fullName;
    private String street;
    private String houseNumber;
    private String postalCode;
    private String city;
    private boolean invoiceNotification;

    public ProfileBean()
    {
    }

    /**
     * The function executed after constructing the bean.
     */
    @PostConstruct
    public void init()
    {
        MovementUserDto user = this.userSession.getLoggedinUser();

        this.fullName = user.name();
        this.street = user.street();
        this.houseNumber = user.houseNumber();
        this.postalCode = user.postalCode();
        this.city = user.city();
        this.invoiceNotification = user.invoiceMail();
    }

    /**
     * Change the details of the current user.
     */
    public void changeDetails()
    {
        MovementUserDto user = this.driverService.changeDetails(
                this.userSession.getLoggedinUser().id(),
                this.fullName,
                this.street,
                this.houseNumber,
                this.postalCode,
                this.city,
                this.invoiceNotification);

        if(user == null)
        {
            this.detailsSuccessful = false;
            this.detailErrorMessage = "Failed to change details. Please try again.";
        }
        else
        {
            this.detailsSuccessful = true;
            this.detailErrorMessage = null;
            this.userSession.setLoggedinUser(user);
        }
    }

    /**
     * Change the password of the current user.
     */
    public void changePassword()
    {
        String message = this.driverService.changePassword(
                this.userSession.getLoggedinUser().id(),
                this.oldPassword,
                this.newPassword1,
                this.newPassword2);

        this.passwordErrorMessage = message;
        this.passwordSuccessful = message == null;
    }

    public String getOldPassword()
    {
        return oldPassword;
    }

    public void setOldPassword(String oldPassword)
    {
        this.oldPassword = oldPassword;
    }

    public String getNewPassword1()
    {
        return newPassword1;
    }

    public void setNewPassword1(String newPassword1)
    {
        this.newPassword1 = newPassword1;
    }

    public String getNewPassword2()
    {
        return newPassword2;
    }

    public void setNewPassword2(String newPassword2)
    {
        this.newPassword2 = newPassword2;
    }

    public String getFullName()
    {
        return fullName;
    }

    public void setFullName(String fullName)
    {
        this.fullName = fullName;
    }

    public String getStreet()
    {
        return street;
    }

    public void setStreet(String street)
    {
        this.street = street;
    }

    public String getHouseNumber()
    {
        return houseNumber;
    }

    public void setHouseNumber(String houseNumber)
    {
        this.houseNumber = houseNumber;
    }

    public String getPostalCode()
    {
        return postalCode;
    }

    public void setPostalCode(String postalCode)
    {
        this.postalCode = postalCode;
    }

    public String getCity()
    {
        return city;
    }

    public void setCity(String city)
    {
        this.city = city;
    }

    public boolean isInvoiceNotification()
    {
        return invoiceNotification;
    }

    public void setInvoiceNotification(boolean invoiceNotification)
    {
        this.invoiceNotification = invoiceNotification;
    }

    public boolean isPasswordSuccessful()
    {
        return passwordSuccessful;
    }

    public boolean isDetailsSuccessful()
    {
        return detailsSuccessful;
    }

    public String getPasswordErrorMessage()
    {
        return passwordErrorMessage;
    }

    public String getDetailErrorMessage()
    {
        return detailErrorMessage;
    }
}
