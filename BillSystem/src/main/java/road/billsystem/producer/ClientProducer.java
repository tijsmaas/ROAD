package road.billsystem.producer;

import road.billdts.connections.BillClient;

import javax.enterprise.context.ApplicationScoped;
import javax.enterprise.inject.Produces;

/**
 * Created by Niek on 22/04/14.
 * © Aidas 2014
 */
@ApplicationScoped
public class ClientProducer
{
    @Produces @ProducerQualifier
    public BillClient getBillClientConnection(){
        return new BillClient();
    }

}
