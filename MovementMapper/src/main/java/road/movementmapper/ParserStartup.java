package road.movementmapper;

import java.io.File;
import java.io.FilenameFilter;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.annotation.PostConstruct;
import javax.ejb.Singleton;
import javax.ejb.Startup;
import javax.inject.Inject;
import movementParser.MovementParser;
import org.xml.sax.SAXException;
import test.TestSumoParser;

@Singleton
@Startup
public class ParserStartup {
    /* Initial map files */
    private static final String INPUTOSMFILE = "/home/tijs/Development/java/ROAD/MovementParser/res/PTS-ESD-2.osm";
    /* SUMO file should be generated from the osm file */
    private static final String INPUTSUMOFILE = "/home/tijs/Development/java/ROAD/MovementParser/res/PTS-ESD-2.net.xml";    
    /* Path to directory with initial movements */
    private static final String DIR = "movements";
    
    @Inject
    private MovementMapper parser;
    
    @Inject
    private MovementParser movementParser;
    
    @PostConstruct
    public void init() {
        initialiseMap();
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
        System.out.println("[SCHEDULE] Parsing new movements in directory "+new File(DIR).getAbsolutePath());
        File[] files = finder(DIR);
        for(File file : files) {
            System.out.println("Parsing file " + file.getName());
            movementParser.parseChanges(file);
            //System.out.println("Deleting file " + file.getName());
            //file.delete();
        }
    }

    
    /**
     * Helper function to find all XML files in a directory
     * @param dirName
     * @return 
     */
    private File[] finder(String dirName) {
        File dir = new File(dirName);
        if(!dir.isDirectory())
            System.err.println("Cannot read movements directory");

        return dir.listFiles(new FilenameFilter() {
            @Override
            public boolean accept(File dir, String filename) {
                return filename.endsWith(".xml");
            }
        });
    }

}
