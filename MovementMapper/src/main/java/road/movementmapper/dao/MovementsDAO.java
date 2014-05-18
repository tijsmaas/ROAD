/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package road.movementmapper.dao;

import road.movemententities.entities.Lane;
import road.movemententities.entities.Vehicle;

/**
 *
 * @author tijs
 */
public interface MovementsDAO
{
    Lane getLaneById(String laneId);
    Vehicle getOrCreateVehicleById(String licensePlate);
}
