package producer;

import dao.EdgeDAO;
import dao.EdgeDAOImpl;
import qualifier.ProducerQualifier;

import javax.enterprise.inject.Produces;

/**
 * Created by Niek on 10/04/14.
 * © Aidas 2014
 */
public class DAOProducer
{
    @Produces
    @ProducerQualifier
    public EdgeDAO getEdgeDAO(){
        return new EdgeDAOImpl();
    }
}
