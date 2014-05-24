package road.movementservice;

import road.movemententities.entities.MovementUser;
import road.movemententities.entities.Vehicle;
import road.movemententities.entities.VehicleOwnership;
import road.movemententityaccess.dao.*;
import road.movementparser.daos.ParserDAOImpl;
import road.movementservice.mapper.DtoMapper;
import road.movementservice.servers.*;
import road.userservice.UserDAO;
import road.userservice.UserDAOImpl;
import road.userservice.dto.UserDto;
import road.userservice.exceptions.UserSystemException;

import javax.mail.Session;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import java.util.GregorianCalendar;

/**
 * Created by geh on 8-5-14.
 */
public class Server
{
    private DriverServer driverServer;
    private BillServer billServer;
    private PoliceServer policeServer;
    private CarServer carServer;
    private JamServer jamServer;

    private DtoMapper dtoMapper;
    private Session mailSession;

    /**
     * this method is used to initialize all the different services.
     */
    public void init()
    {
        EntityManagerFactory emfUserService = Persistence.createEntityManagerFactory("UserServicePUMS");
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("MovementPUNonJTA");

        try
        {
            this.mailSession = (Session) new InitialContext().lookup("roadmail");
        } catch (NamingException ex)
        {
            ex.printStackTrace();
        }

        this.dtoMapper = new DtoMapper();

        this.driverServer = new DriverServer(new UserDAOImpl(emfUserService),
                new LoginDAOImpl(emf),
                new LaneDAOImpl(emf),
                new ConnectionDAOImpl(emf),
                new EdgeDAOImpl(emf),
                new VehicleDAOImpl(emf),
                new InvoiceDAOImpl(emf),
                this.dtoMapper);
        this.driverServer.init();

        this.billServer = new BillServer(new InvoiceDAOImpl(emf),
                new LoginDAOImpl(emf),
                new UserDAOImpl(emfUserService),
                new MovementDAOImpl(emf),
                new CityDAOImpl(emf),
                this.dtoMapper,
                new VehicleDAOImpl(emf),
                this.mailSession);
        this.billServer.init();

        this.policeServer = new PoliceServer(new LoginDAOImpl(emf),
                new UserDAOImpl(emfUserService),
                new PoliceDAOImpl(emf),
                new VehicleDAOImpl(emf),
                this.dtoMapper);
        this.policeServer.init();

        this.carServer = new CarServer(new EntityDAOImpl(emf), new VehicleDAOImpl(emf), new ParserDAOImpl(emf));
        this.carServer.init();

        this.jamServer = new JamServer(this.dtoMapper);
        this.jamServer.init();

        this.fillDatabase(emf, emfUserService);
    }

    /**
     * Function to fill the database with test data.
     *
     * @param movementEmf the entity manager factory used for getting the {@link EntityManager}.
     */
    private void fillDatabase(EntityManagerFactory movementEmf, EntityManagerFactory userEmf)
    {
        EntityManager em = movementEmf.createEntityManager();

        // Create a user for debugging.
        try
        {
            UserDAO userDAO = new UserDAOImpl(userEmf);
            LoginDAO loginDAO = new LoginDAOImpl(movementEmf);

            UserDto userDto = userDAO.register("admin", "aidas123", "tijs.maas@student.fontys.nl");
            MovementUser mUser = loginDAO.register(userDto.getUsername(), userDto.getEmail());

            Vehicle v = new Vehicle();
            v.setLicensePlate("AA-12-BB");
            VehicleOwnership vo = new VehicleOwnership(v, mUser, new GregorianCalendar(), null);

            em.persist(v);
            em.persist(vo);
        } catch (UserSystemException e)
        {
            e.printStackTrace();
        }
    }
}
