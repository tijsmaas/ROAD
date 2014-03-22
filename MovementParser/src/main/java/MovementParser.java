import java.io.File;
import java.io.IOException;

import javax.xml.parsers.ParserConfigurationException;
import javax.xml.parsers.SAXParser;
import javax.xml.parsers.SAXParserFactory;

import org.xml.sax.SAXException;


public class MovementParser {
	
	public static void main(String [] args) {
		new MovementParser().parseSUMO(new File("res/PTS-ESD-2.net.xml"));
	}
	
	/**
	 * 1. parse edges
	 * 2. skip traffic lights and junctions
	 * 3. parse connections
	 * @param map
	 */
	public boolean parseSUMO(File map) {
		SAXParserFactory spfac = SAXParserFactory.newInstance();

        //Now use the parser factory to create a SAXParser object
        SAXParser sp = null;
		try
		{
			sp = spfac.newSAXParser();
		} catch (ParserConfigurationException e)
		{
			e.printStackTrace();
			// We can't fix this if it occurs
			return false;
		} catch (SAXException e)
		{
			e.printStackTrace();
			// Should not happen on initialisation
			return false;
		}

        //Create an instance of this class; it defines all the handler methods
        SUMOParser handler = new SUMOParser();

        //Finally, tell the parser to parse the input and notify the handler
        try
		{
			sp.parse(map, handler);
		} catch (SAXException e)
		{
			e.printStackTrace();
		} catch (IOException e)
		{
			e.printStackTrace();
			// @TODO: Report to someone that the XML file is inaccesible
			return false;
		}
       
        handler.readEdges();
        handler.readConnections();
        
        return true;
	}
	
	/**
	 * 1. convert to SUMO (with netconvert)
	 * 2. parse as SUMO
	 * 3. Add extra information such as road names(ways) and place names (nodes).
	 * @param map
	 */
	public void parseOSM(File map) {
		
	}

	/**
	 * 1. Parse positions of all vehicles
	 * 2. Insert into database
	 * @param changes
	 */
	public void parseChanges(File changes) {
		
	}
}
