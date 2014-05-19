/*
 * Copyright by AIDaS.
 */

package road.driversystem.beans;

import aidas.userservice.dto.UserDto;
import road.driversystem.service.DriverService;

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
public class ProfileBean implements Serializable {

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

    public ProfileBean() {}

    /**
     * The function executed after constructing the bean.
     */
    @PostConstruct
    public void init() {
        UserDto user = this.userSession.getLoggedinUser();

        this.fullName = user.getName();
        this.street = user.getStreet();
        this.houseNumber = user.getHouseNumber();
        this.postalCode = user.getPostalCode();
        this.city = user.getCity();
    }

    /**
     * Change the details of the current user.
     */
    public void changeDetails() {
        boolean isSuccessful = this.driverService.changeDetails(
                this.userSession.getLoggedinUser().getId(),
                this.fullName,
                this.street,
                this.houseNumber,
                this.postalCode,
                this.city);

        this.detailErrorMessage = !isSuccessful ? "Failed to change you details. Please try again." : null;
        this.detailsSuccessful = isSuccessful;
    }

    /**
     * Change the password of the current user.
     */
    public void changePassword() {
        String message = this.driverService.changePassword(
                this.userSession.getLoggedinUser().getId(),
                this.oldPassword,
                this.newPassword1,
                this.newPassword2);

        this.passwordErrorMessage = message;
        this.passwordSuccessful = message == null;
    }

    public String getOldPassword() {
        return oldPassword;
    }

    public void setOldPassword(String oldPassword) {
        this.oldPassword = oldPassword;
    }

    public String getNewPassword1() {
        return newPassword1;
    }

    public void setNewPassword1(String newPassword1) {
        this.newPassword1 = newPassword1;
    }

    public String getNewPassword2() {
        return newPassword2;
    }

    public void setNewPassword2(String newPassword2) {
        this.newPassword2 = newPassword2;
    }

    public String getFullName() { return fullName; }

    public void setFullName(String fullName) {
        this.fullName = fullName;
    }

    public String getStreet() {
        return street;
    }

    public void setStreet(String street) {
        this.street = street;
    }

    public String getHouseNumber() {
        return houseNumber;
    }

    public void setHouseNumber(String houseNumber) {
        this.houseNumber = houseNumber;
    }

    public String getPostalCode() {
        return postalCode;
    }

    public void setPostalCode(String postalCode) {
        this.postalCode = postalCode;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public boolean isPasswordSuccessful() {
        return passwordSuccessful;
    }

    public boolean isDetailsSuccessful() {
        return detailsSuccessful;
    }

    public String getPasswordErrorMessage() {
        return passwordErrorMessage;
    }

    public String getDetailErrorMessage() {
        return detailErrorMessage;
    }
}
