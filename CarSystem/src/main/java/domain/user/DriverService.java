/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package domain.user;

import javax.ejb.Stateless;

/**
 *
 * @author Mitch
 */
@Stateless
public class DriverService {
    DriverClient driverClient;

    public UserDTO login(String username, String password) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
}
