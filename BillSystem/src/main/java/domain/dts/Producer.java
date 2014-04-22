/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package domain.dts;

import connections.BillClientConnection;
import javax.enterprise.inject.Produces;
import qualifier.ProducerQualifier;

/**
 *
 * @author Mitch
 */
public class Producer {
    @Produces @ProducerQualifier
    public BillClientConnection billClientConnectionProducer(){
        return new BillClientConnection();
    }
}
