package road.movementmapper;

import org.xml.sax.SAXException;
import road.movementmapper.dao.EntityDAO;
import road.movementmapper.dao.MovementsDAO;
import road.movementparser.injectable.MovementParser;

import javax.annotation.PostConstruct;
import javax.ejb.Singleton;
import javax.ejb.Startup;
import javax.inject.Inject;
import java.io.File;
import java.net.URL;
import java.util.logging.Level;
import java.util.logging.Logger;

@Startup
@Singleton
public class ParserStartup
{
    /* Initial map files */

    private static final String INPUTOSMFILE = "PTS-ESD-2.osm";

    /* SUMO file should be generated from the osm file */
    private static final String INPUTSUMOFILE = "PTS-ESD-2.net.xml";

    @Inject
    private MovementMapper mapParser;

    @Inject
    private MovementParser movementParser;

    @PostConstruct
    public void init()
    {
        initialiseMap();
    }

    /**
     * Parse the initial map of roads and cities.
     */
    public void initialiseMap()
    {
        // The SUMO file must be converted from the OSM!
        File inputSUMO = getResourceFile(INPUTSUMOFILE);
        File inputOSM = getResourceFile(INPUTOSMFILE);

        System.out.println("[PARSERSERVICE] Parsing map " + inputSUMO.getAbsolutePath());
        try
        {
            mapParser.parseSUMO(inputSUMO);
            mapParser.parseOSM(inputOSM);
            System.out.println("[PARSERSERVICE] Finished parsing initial map");
        } catch (SAXException ex)
        {
            Logger.getLogger(this.getClass().getName()).log(Level.SEVERE, null, ex);
        }
    }

    private File getResourceFile(String filename)
    {
        URL url = this.getClass().getResource("/" + filename);
        System.out.println("url: " + url.getPath());
        return new File(url.getFile());
    }

    @Inject
    private EntityDAO entityDAO;

    @Inject
    private MovementsDAO movementsDAO;


}
