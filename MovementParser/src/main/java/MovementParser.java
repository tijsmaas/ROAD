import java.io.File;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBElement;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Unmarshaller;

import osm.jaxb.OsmType;
import sumo.jaxb.NetType;
import sumo.movements.jaxb.SumoNetstateType;


public class MovementParser {
	
	public static void main(String [] args) {
		MovementParser mp = new MovementParser();
		mp.parseSUMO(new File("res/PTS-ESD-2.net.xml"));
		mp.parseChanges(new File("/home/tijs/Downloads/verpl_systeem/verplaatsingen_20110208.xml"));
	}
	
	/**
	 * 1. parse edges
	 * 2. skip traffic lights and junctions
	 * 3. parse connections
	 * @param map
	 */
	public void parseSUMO(File map) {
		long startTime = System.nanoTime();
		JAXBElement<NetType> root = (JAXBElement<NetType>) parse(map, "sumo.jaxb");
		if(root != null) {
			System.out.println("Parsed "+map.getName()+" in "+(System.nanoTime()-startTime)+"ns");
			// TODO persist
		}
	}
	
	/**
	 * 1. convert to SUMO (with netconvert)
	 * 2. parse as SUMO
	 * 3. Add extra information such as road names(ways) and place names (nodes).
	 * @param map
	 */
	public void parseOSM(File map) {
		long startTime = System.nanoTime();
		JAXBElement<OsmType> root = (JAXBElement<OsmType>) parse(map, "osm.jaxb");
		if(root != null) {
			System.out.println("Parsed "+map.getName()+" in "+(System.nanoTime()-startTime)+"ns");
			// TODO persist
		}
	}

	/**
	 * 1. Parse positions of all vehicles
	 * 2. Insert into database
	 * @param changes
	 */
	public void parseChanges(File changes) {
		long startTime = System.nanoTime();
		@SuppressWarnings("unchecked")
		JAXBElement<SumoNetstateType> root = (JAXBElement<SumoNetstateType>) parse(changes, "osm.jaxb");
		if(root != null) {
			System.out.println("Parsed "+changes.getName()+" in "+(System.nanoTime()-startTime)+"ns");
			// TODO persist
		}
	}
	
	@SuppressWarnings("rawtypes")
	private JAXBElement parse(File file, String classpath) {
        JAXBContext jc;
		try
		{
			/** Read XML(JAXB) annotations from the classes in this package **/
			jc = JAXBContext.newInstance(classpath);
	        Unmarshaller unmarshaller = jc.createUnmarshaller();
			return (JAXBElement) unmarshaller.unmarshal(file);
		} catch (JAXBException e)
		{
			System.err.println("Parsing of "+file.getName()+" failed");
			e.printStackTrace();
		}
		return null;
	}
}
