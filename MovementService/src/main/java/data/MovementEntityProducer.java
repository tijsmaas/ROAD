package data;

import dao.*;
import data.annotations.ProducerQualifier;

import javax.enterprise.inject.Produces;
import javax.faces.bean.ApplicationScoped;
import javax.faces.bean.ManagedBean;

@ManagedBean @ApplicationScoped
public class MovementEntityProducer
{
//    @Produces @ProducerQualifier
//    public EdgeDAO getEdgeDao()
//    {
//        return new EdgeDAOImpl();
//    }
//
//    @Produces @ProducerQualifier
//    public LaneDAO getLaneDAO()
//    {
//        return new LaneDAOImpl();
//    }
//
//    @Produces
//    @ProducerQualifier
//    public ConnectionDAO getConnectionDAO()
//    {
//        return new ConnectionDAOImpl();
//    }
}
