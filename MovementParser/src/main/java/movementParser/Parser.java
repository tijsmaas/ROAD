package movementParser;

import java.io.ByteArrayInputStream;
import java.io.File;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.inject.Inject;
import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBElement;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Unmarshaller;
import javax.xml.stream.XMLInputFactory;
import javax.xml.stream.XMLStreamException;
import org.xml.sax.SAXException;
import osm.jaxb.OsmType;
import sumo.jaxb.NetType;
import sumo.movements.jaxb.SumoNetstateType;

public class Parser {
    
    @Inject
    private MapParser mapParser;
    
    @Inject
    private MovementParser movementParser;
    
    private int numberOfMovementParses = 0;
    
    /**
     * Parse the SUMO version of the net map.
     *
     * @param map This sumo map should be generated from OSM, see
     * http://sumo-sim.org/userdoc/Networks/Import/OpenStreetMap.html for more
     * info.
     * @throws SAXException
     */
    public void parseSUMO(File map) throws SAXException {
        long startTime = System.nanoTime();
        JAXBElement<NetType> root = (JAXBElement<NetType>) parse(map, "sumo.jaxb");
        mapParser.parseMap(root);
        System.out.println("Parsed " + map.getName() + " in " + (System.nanoTime() - startTime) + "ns");
    }
    
    /**
     * Enrich the parsed SUMO net map.
     * Add information such as place names.
     * 
     * @param map Open Street map file 
     * (Tested with OSM version='0.6' generator='JOSM')
     */
    public void parseOSM(File map) {
        long startTime = System.nanoTime();
        JAXBElement<OsmType> root = (JAXBElement<OsmType>) parse(map, "osm.jaxb");
        mapParser.parseCities(root);
        System.out.println("Parsed " + map.getName() + " in " + (System.nanoTime() - startTime) + "ns");
    }
    
    /**
     * Parse movements from a file
     * @param changes 
     */
    public void parseChanges(File changes) {        
        long startTime = System.nanoTime();
        @SuppressWarnings("unchecked")
        JAXBElement<SumoNetstateType> root = (JAXBElement<SumoNetstateType>) parse(changes, "sumo.movements.jaxb");
        movementParser.parseTimesteps(root);
        System.out.println("Parsed " + changes.getName() + " in " + (System.nanoTime() - startTime) + "ns");
    }
    
    /**
     * Parse movements from a string
     * @param changes 
     */
    public void parseChanges(String changes, int sequencenr) {
        if(numberOfMovementParses != sequencenr)
            throw new IllegalArgumentException("Tried to add Movements with serial number "+sequencenr+", but counter was at "+numberOfMovementParses);
        numberOfMovementParses++;
        
        long startTime = System.nanoTime();
        @SuppressWarnings("unchecked")
        JAXBElement<SumoNetstateType> root = (JAXBElement<SumoNetstateType>) parse(changes, "sumo.movements.jaxb");
        movementParser.parseTimesteps(root);
        System.out.println("Parsed changes in " + (System.nanoTime() - startTime) + "ns");
    }
    
    
    /**
     * Parse XML from file with classes in classpath
     * @param file
     * @param classpath
     * @return JAXB root element
     */
    @SuppressWarnings("rawtypes")
    protected JAXBElement parse(File file, String classpath) {
        JAXBContext jc;
        try {
            /**
             * Read XML(JAXB) annotations from the classes in this package *
             */
            jc = JAXBContext.newInstance(classpath);
            Unmarshaller unmarshaller = jc.createUnmarshaller();
            return (JAXBElement) unmarshaller.unmarshal(file);
        } catch (JAXBException e) {
            System.err.println("Parsing of " + file.getName() + " failed");
            e.printStackTrace();
        }
        return null;
    }
    
    
    /**
     * Parse XML from string movements with classes in classpath
     * @param movements
     * @param classpath
     * @return JAXB root element
     */
    protected JAXBElement parse(String movements, String classpath) {
        JAXBContext jc;
        try {
            /**
             * Read XML(JAXB) annotations from the classes in this package *
             */
            jc = JAXBContext.newInstance(classpath);
            Unmarshaller unmarshaller = jc.createUnmarshaller();
            return (JAXBElement) unmarshaller.unmarshal(
                    XMLInputFactory.newFactory().createXMLEventReader(
                            new ByteArrayInputStream(movements.getBytes())));
        } catch (JAXBException e) {
            System.err.println("Parsing of string failed");
            e.printStackTrace();
        } catch (XMLStreamException ex) {
            Logger.getLogger(MovementParser.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }
}
