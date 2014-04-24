package servers;

import aidas.usersystem.dto.UserDto;
import connections.IDriverQuery;
import connections.MovementConnection;
import connections.ServerConnection;
import dao.ConnectionDAO;
import dao.EdgeDAO;
import dao.LaneDAO;
import data.annotations.ProducerQualifier;

import javax.annotation.PostConstruct;
import javax.faces.bean.ApplicationScoped;
import javax.faces.bean.ManagedBean;
import javax.inject.Inject;
import javax.persistence.*;

/**
 * Created by geh on 22-4-14.
 */
@ApplicationScoped
@ManagedBean(eager = true)
public class DriverServer extends ServerConnection implements IDriverQuery
{
    //@Inject
    //private IUserManager userManager;
    @Inject //@ProducerQualifier
    private EdgeDAO edgeDAO;
    @Inject //@ProducerQualifier
    private LaneDAO laneDAO;
    @Inject //@ProducerQualifier
    private ConnectionDAO connectionDAO;
    private EntityManager em;

    public DriverServer()
    {
        super(MovementConnection.FactoryName, MovementConnection.DriverSystemQueue);
    }

    @PostConstruct
    public void init()
    {
        super.initRpc(IDriverQuery.class, this);
        this.start();

        this.em = (EntityManager) Persistence.createEntityManagerFactory("MovementPU").createEntityManager();
    }

    @Override
    public UserDto authenticate(String user, String password)
    {
        return new UserDto(1, user + " @ driver system");
    }

    @Override
    public Integer getLaneCount()
    {
        Query query = em.createQuery("Select count(edge) from Edge edge");
        return ((Long)query.getSingleResult()).intValue();
        //return this.laneDAO.count();
    }
}
