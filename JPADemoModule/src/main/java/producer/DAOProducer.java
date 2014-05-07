package producer;

import road.movemententityaccess.dao.EdgeDAO;
import road.movemententityaccess.dao.EdgeDAOImpl;
import qualifier.ProducerQualifier;

import javax.enterprise.inject.Produces;

/**
 * Created by Niek on 10/04/14.
 * © Aidas 2014
 *
 * Producer class that produces DAOs so that they're available for injection
 */
public class DAOProducer
{
    @Produces //The produces tag tells the server to make this available for CDI
    @ProducerQualifier //The producerQualifier is a custom qualifier to identify between producer and actual DAO, see the qualifier package
    public EdgeDAO getEdgeDao(){

        //Create a new implementation of the edgeDAO, from now on we can @Inject the EdgeDAO
        return new EdgeDAOImpl();
    }

}
