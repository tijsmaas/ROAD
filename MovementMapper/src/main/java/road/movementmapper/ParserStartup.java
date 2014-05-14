package road.movementmapper;

import java.io.File;
import java.util.Calendar;
import java.util.Properties;
import java.util.logging.Level;
import java.util.logging.Logger;

import javax.annotation.PostConstruct;
import javax.batch.operations.JobOperator;
import javax.batch.runtime.BatchRuntime;
import javax.batch.runtime.JobExecution;
import javax.ejb.Startup;
import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import javax.ejb.Singleton;

import org.xml.sax.SAXException;

import road.movementparser.injectable.MovementParser;
import test.TestSumoParser;

@Startup
@Singleton
public class ParserStartup {
    /* Initial map files */
    private static final String INPUTOSMFILE = "/home/tijs/Development/java/ROAD/MovementMapper/src/main/resources/PTS-ESD-2.osm";
    /* SUMO file should be generated from the osm file */
    private static final String INPUTSUMOFILE = "/home/tijs/Development/java/ROAD/MovementMapper/src/main/resources/PTS-ESD-2.net.xml";
    /* Path to directory with initial movements */
    private static final String MOVEMENTSDIR = "/home/tijs/Downloads/verpl_systeem/";
    
    private static final String MOVEMENTSSMALLFILE = "/home/tijs/Downloads/verpl_systeem/verplaatsingen_20110209_small.xml";
    
    @Inject
    private MovementMapper parser;
    
    @Inject
    private MovementParser movementParser;
    
    @PostConstruct
    public void init() {
    	System.out.println("Doing something....");
        initialiseMap();
    	System.out.println("Parsing movements....");
        parseNewMovements();
    }
    
    public void initialiseMap() {
        // The SUMO file must be converted from the OSM!
        File inputSUMO = new File(INPUTSUMOFILE);
        File inputOSM = new File(INPUTOSMFILE);
        
        System.out.println("[PARSERSERVICE] PARSING MAP "+inputSUMO.getAbsolutePath());
        try {
            parser.parseSUMO(inputSUMO);
            System.out.println("[PARSERSERVICE] PARSING OSM");
            parser.parseOSM(inputOSM);
            System.out.println("DONE PARSING");
        } catch (SAXException ex) {
            Logger.getLogger(TestSumoParser.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    
    /**
     * Parse all xml movement files in directory DIR
     * When parsing completed, delete the files
     */
    //@Schedule(minute = "*/3", hour = "*")
    public void parseNewMovements() {
//        System.out.println("[SCHEDULE] Parsing new movements in directory "+new File(MOVEMENTSDIR).getAbsolutePath());
//        Calendar cal = Calendar.getInstance();
//        for(int dd=7;dd<7;dd++) 
//        {
//            cal.set(2011, 2, 9);
//            movementParser.parseChanges(new File(MOVEMENTSDIR+"verplaatsingen_2011020"+dd+".xml"), cal);
//        }
//        
        Calendar cal = Calendar.getInstance();
        cal.set(2011, 2, 9);
        movementParser.parseChanges(new File(MOVEMENTSSMALLFILE), cal);
    }

}
