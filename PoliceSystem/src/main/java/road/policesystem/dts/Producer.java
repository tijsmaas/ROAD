/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package road.policesystem.dts;

import road.policedts.connections.PoliceClient;

import javax.enterprise.inject.Produces;
import road.policesystem.qualifier.ProducerQualifier;

/**
 *
 * @author Mitch
 */
public class Producer {
    @Produces @ProducerQualifier
    public PoliceClient policeClientConnectionProducer(){
        return new PoliceClient();
    }
}
