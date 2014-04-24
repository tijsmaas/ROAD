package test;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.lang.reflect.Method;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.annotation.PostConstruct;
import javax.ejb.Singleton;
import javax.ejb.Startup;
import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import movementParser.MovementParser;
//import static org.junit.Assert.assertTrue;
import org.xml.sax.SAXException;
import parser.dao.ConnectionDAO;
import parser.dao.EdgeDAO;
import parser.dao.JunctionDAO;
import parser.dao.LaneDAO;
import parser.dao.MovementDAO;
import parser.dao.VehicleDAO;

@Startup
@Singleton
public class TestSumoParser {

    @PersistenceContext(unitName = "MovementPU")
    private EntityManager em;

    /**
     * Inject DAO's from MovementEntityAccess (Injection does not work for unit
     * tests yet)
     */
    @Inject
    private EdgeDAO edgeDAO;

    @Inject
    private LaneDAO laneDAO;

    @Inject
    private ConnectionDAO connectionDAO;

    @Inject
    private JunctionDAO junctionDAO;

    @Inject
    private MovementDAO movementDAO;

    @Inject
    private VehicleDAO vehicleDAO;

    @Inject
    private MovementParser parser;

    /**
     * Truncate all database tables TODO FIX: MYSQL ONLY!
     */
    private void clearDatabase() {
        String[] tables = {"Connection", "Edge", "Edge_Connection", "Edge_Lane", "Junction", "JunctionRequest", "Junction_JunctionRequest", "Junction_Lane", "Lane", "Movement", "MovementVehicle", "Movement_MovementVehicle", "Vehicle", "VehicleOwnership", "Vehicle_VehicleOwnership"};

        em.createNativeQuery("SET FOREIGN_KEY_CHECKS=0").executeUpdate();
        for (String table : tables) {
            em.createNativeQuery("truncate table " + table).executeUpdate();
        }
        em.createNativeQuery("SET FOREIGN_KEY_CHECKS=1").executeUpdate();
        em.flush();
    }

    //@PostConstruct
    public void setup() {        
        // <editor-fold defaultstate="collapsed" desc="Call all test methods in this class">
        for (Method testmethod : TestSumoParser.class.getMethods()) {
            try {
                if (testmethod.getName().startsWith("test")) {
                    testmethod.invoke(this);
                    System.out.println("Test " + testmethod.getName() + " succeed");
                }
            } catch (Exception e) {
                System.err.println("Test " + testmethod.getName() + " failed with error: ");
                e.printStackTrace();
            }
        }
// </editor-fold>
    }

    private void assertEqualNumber(String assertname, int number1, int number2) throws Exception {
        if (number1 != number2) {
            throw new Exception("Error in assert " + assertname + " with equals " + number1 + " == " + number2);
        }
    }

    /**
     * Test if all basic elements in SUMO are parsed to the database.
     *
     * @throws Exception
     */
    public void testParserQuantities() throws Exception {
        clearDatabase();
        
        File inputSUMO = new File("/home/tijs/Development/java/ROAD/MovementParser/res/PTS-ESD-2.net.xml");

        System.out.println("PARSING FILE TROUGH SINGLETON TEST");
        try {
            parser.parseSUMO(inputSUMO);
            System.out.println("DONE PARSING");
        } catch (SAXException ex) {
            Logger.getLogger(TestSumoParser.class.getName()).log(Level.SEVERE, null, ex);
            throw new Exception("Error with parsing file");
        }

        // Count edges, lanes, connections, and junctions of test file
        int edges = 0, lanes = 0, connections = 0, junctions = 0;

        try (BufferedReader br = new BufferedReader(new FileReader(inputSUMO))) {
            StringBuilder sb = new StringBuilder();
            String line = br.readLine();

            while (line != null) {
                if (line.contains("<edge")) {
                    edges++;
                }
                if (line.contains("<lane")) {
                    lanes++;
                }
                if (line.contains("<connection")) {
                    connections++;
                }
                if (line.contains("<junction")) {
                    junctions++;
                }

                sb.append(line);
                sb.append(System.lineSeparator());
                line = br.readLine();
            }
        }

        try {
            System.out.println("Calculating elements...");
            Thread.sleep(3000);
            assertEqualNumber("edges", edgeDAO.count(), edges);
            assertEqualNumber("lanes", laneDAO.count(), lanes);
            assertEqualNumber("connections", connectionDAO.count(), connections);
            assertEqualNumber("junctions", junctionDAO.count(), junctions);
        }catch (javax.persistence.PersistenceException pe) {
            pe.printStackTrace();
        }
    }

}
