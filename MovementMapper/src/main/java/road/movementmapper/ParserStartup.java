package road.movementmapper;

import java.io.File;
import java.net.URL;
import java.util.Calendar;
import java.util.logging.Level;
import java.util.logging.Logger;

import javax.annotation.PostConstruct;
import javax.ejb.Startup;
import javax.inject.Inject;
import javax.ejb.Singleton;

import org.xml.sax.SAXException;

import road.movementparser.injectable.MovementParser;
import test.TestSumoParser;

@Startup
@Singleton
public class ParserStartup {
    /* Initial map files */
    private static final String INPUTOSMFILE = "PTS-ESD-2.osm";
    
    /* SUMO file should be generated from the osm file */
    private static final String INPUTSUMOFILE = "PTS-ESD-2.net.xml";
    
    /* Path to directory with initial movements */
//    private static final String MOVEMENTSDIR = "/home/tijs/Downloads/verpl_systeem/";
    
    /* SUMO movements file, containing the vehicle movements over time */
    private static final String MOVEMENTSSMALLFILE = "verplaatsingen_20110209_small.xml";
    
    @Inject
    private MovementMapper mapParser;
    
    @Inject
    private MovementParser movementParser;
    
    @PostConstruct
    public void init() {
        initialiseMap();
        parseNewMovements();
    }
    
    /**
     * Parse the initial map of roads and cities.
     */
    public void initialiseMap() {
        // The SUMO file must be converted from the OSM!
        File inputSUMO = getResourceFile(INPUTSUMOFILE);
        File inputOSM = getResourceFile(INPUTOSMFILE);
        
        System.out.println("[PARSERSERVICE] Parsing map "+inputSUMO.getAbsolutePath());
        try {
            mapParser.parseSUMO(inputSUMO);
            mapParser.parseOSM(inputOSM);
            System.out.println("[PARSERSERVICE] Finished parsing initial map");
        } catch (SAXException ex) {
            Logger.getLogger(TestSumoParser.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    
    private File getResourceFile(String filename) {
        URL url = this.getClass().getResource("/"+filename);
        System.out.println("url: "+url.getPath());
        return new File(url.getFile());
    }
    
    
    /**
     * Parse all xml movement files in directory DIR
     * When parsing completed, delete the files
     */
    //@Schedule(minute = "*/3", hour = "*")
    public void parseNewMovements() {
    	System.out.println("[PARSERSERVICE] Parsing movements...");
//        CODE TO LOAD INITIAL MOVEMENTS (TOO HEAVY FOR NOW)
//        System.out.println("[SCHEDULE] Parsing new movements in directory "+new File(MOVEMENTSDIR).getAbsolutePath());
//        Calendar cal = Calendar.getInstance();
//        for(int dd=7;dd<7;dd++) 
//        {
//            cal.set(2011, 2, 9);
//            movementParser.parseChanges(new File(MOVEMENTSDIR+"verplaatsingen_2011020"+dd+".xml"), cal);
//        }
//      
        // Set parsing time to midnight
        Calendar cal = Calendar.getInstance();
        cal.set(2011, 2, 9, 0, 0, 0);
        movementParser.parseChanges(new File(MOVEMENTSSMALLFILE), cal);
    }

}
