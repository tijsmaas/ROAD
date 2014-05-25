package road.movementmapper;

import org.primefaces.model.UploadedFile;
import org.xml.sax.SAXException;
import osm.jaxb.OsmType;
import road.movementparser.injectable.GenericParser;
import sumo.jaxb.NetType;

import javax.inject.Inject;
import javax.xml.bind.JAXBElement;
import java.io.File;

public class MovementMapper
{
    private static final String SUMOJAXBPACKAGE = "sumo.jaxb";
    private static final String OSMJAXBPACKAGE = "osm.jaxb";

    @Inject
    private MapParser mapParser;

    @Inject
    private GenericParser genericParser;

    private int numberOfMovementParses = 0;

    /**
     * Parse the SUMO version of the net map.
     *
     * @param map This sumo map should be generated from OSM, see
     *            http://sumo-sim.org/userdoc/Networks/Import/OpenStreetMap.html for more
     *            info.
     * @throws SAXException
     */
    public void parseSUMO(UploadedFile map) throws SAXException
    {
        long startTime = System.nanoTime();
        JAXBElement<NetType> root = (JAXBElement<NetType>) genericParser.parse(map, SUMOJAXBPACKAGE);
        mapParser.parseMap(root);
        System.out.println("Parsed " + map.getFileName() + " in " + (System.nanoTime() - startTime) + "ns");
    }

    /**
     * Enrich the parsed SUMO net map.
     * Add information such as place names.
     *
     * @param map Open Street map file
     *            (Tested with OSM version='0.6' generator='JOSM')
     */
    public void parseOSM(UploadedFile map)
    {
        long startTime = System.nanoTime();
        JAXBElement<OsmType> root = (JAXBElement<OsmType>) genericParser.parse(map, OSMJAXBPACKAGE);
        mapParser.parseCities(root);
        System.out.println("Parsed " + map.getFileName() + " in " + (System.nanoTime() - startTime) + "ns");
    }
}
