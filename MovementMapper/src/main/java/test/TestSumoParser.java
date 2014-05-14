package test;

import road.movemententities.entities.*;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.ejb.Singleton;
import javax.ejb.Startup;
import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.PersistenceContext;
import javax.persistence.PersistenceUnit;
//import static org.junit.Assert.assertTrue;
import org.xml.sax.SAXException;
import road.movementmapper.MovementMapper;
import road.movementmapper.dao.EntityDAO;

/**
 * This class exists pure for testing purposes. These tests should be converted
 * to unit tests that run inside the container.
 *
 * @author tijs
 */
@Startup
@Singleton
public class TestSumoParser {

    @PersistenceUnit
    EntityManagerFactory emf;

    @PersistenceContext(unitName = "MovementPU")
    private EntityManager em;
    
    @Inject
    private EntityDAO entityDAO;

    /**
     * Inject DAO's from MovementEntityAccess (Injection does not work for unit
     * tests yet) 
     */

    @Inject
    private MovementMapper mapper;

    //@PostConstruct
    public void setup() {
        System.out.println("SETUP");
        // <editor-fold defaultstate="collapsed" desc="Call all test methods in this class">
//        for (Method testmethod : TestSumoParser.class.getMethods()) {
//            try {
//                for(Annotation a : testmethod.getAnnotations()) {
//                    if (a.annotationType().equals(Test.class)){
//                        testmethod.invoke(this);
//                        System.out.println("Test " + testmethod.getName() + " succeed");
//                    }
//                }
//            } catch (Exception e) {
//                System.err.println("Test " + testmethod.getName() + " failed with error: ");
//                e.printStackTrace();
//            }
//        }
// </editor-fold>
        try {
            //testBatch();
            testParserQuantities();
            
        } catch (Exception ex) {
            Logger.getLogger(TestSumoParser.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    /**
     * Test if all basic elements in SUMO are parsed to the database.
     *
     * @throws Exception
     */
    public void testParserQuantities() throws Exception {
        File inputSUMO = new File("/home/tijs/Development/java/ROAD/road.movementparser.parser.MovementParser/res/PTS-ESD-2.net.xml");

        System.out.println("PARSING FILE TROUGH SINGLETON TEST");
        try {
            mapper.parseSUMO(inputSUMO);
            emf.getCache().evictAll();
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
            assertEqualNumber("edges", entityDAO.count(Edge.class), edges);
            assertEqualNumber("lanes", entityDAO.count(Lane.class), lanes);
            assertEqualNumber("connections", entityDAO.count(Connection.class), connections);
        } catch (javax.persistence.PersistenceException pe) {
            pe.printStackTrace();
        }
        
        Edge e = (Edge) entityDAO.findById(Edge.class, "-92");
        Lane l = (Lane) entityDAO.findById(Lane.class, "-92_0");
        for(Lane lane : e.getLanes()) {
            if(l.equals(lane)) {
                System.out.println("Lanes and edges persist correctly");
            }
        }    
    }
    
    
    /**
     * Test helper method.
     */
    private void assertEqualNumber(String assertname, int number1, int number2) throws Exception {
        if (number1 != number2) {
            throw new Exception("Error in assert " + assertname + " with equals " + number1 + " == " + number2);
        }else{
            System.out.println(assertname+"="+number1);
        }
    }

}
