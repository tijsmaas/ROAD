import java.io.File;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.ManyToOne;
import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBElement;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Unmarshaller;

import osm.jaxb.OsmType;
import sumo.jaxb.ConnectionType;
import sumo.jaxb.EdgeType;
import sumo.jaxb.LaneType;
import sumo.jaxb.NetType;
import sumo.movements.jaxb.SumoNetstateType;
import utils.TypeConversion;
import entities.Connection;
import entities.Edge;
import entities.Lane;
import entities.MovementMap;
import entities.Connection.ConnectionDirection;
import entities.Connection.ConnectionState;


public class MovementParser {
	
	MovementMap mm;
	
	public static void main(String [] args) {
		MovementParser mp = new MovementParser();
		mp.parseSUMO(new File("res/PTS-ESD-2.net.xml"));
		mp.parseChanges(new File("/home/tijs/Downloads/verpl_systeem/verplaatsingen_20110208.xml"));
	}
	
	public MovementParser()
	{
		mm = new MovementMap();
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
			
			// Persist edges, convert xmltypes to JPA entities
			List<Edge> edges = new ArrayList<Edge>();
			for(EdgeType xmlEdge : root.getValue().getEdge()) {
				Edge edge = new Edge(xmlEdge.getId(), xmlEdge.getFunction(), xmlEdge.getType(), xmlEdge.getFrom(), xmlEdge.getTo(), xmlEdge.getPriority().intValue());
				for(LaneType xmlLane : xmlEdge.getLane()) {
					Lane lane = new Lane(edge, xmlLane.getId(), xmlLane.getIndex().intValue(), xmlLane.getSpeed(), xmlLane.getLength());
					edge.addLane(lane);
				}
				edges.add(edge);
			}
			mm.setEdges(edges);
			
			List<Connection> connections = new ArrayList<>();
			for(ConnectionType xmlConn : root.getValue().getConnection()) {
				
				Connection connection = new Connection();
				
				/** Search for relations **/
				String from_str = xmlConn.getFrom();
				String to_str = xmlConn.getTo();
				Integer fromLane_int = xmlConn.getFromLane().intValue();
				Integer toLane_int = xmlConn.getToLane().intValue();
				String via_str = xmlConn.getVia();
				String direction_str = xmlConn.getDir();
				String state_str = xmlConn.getState();

				Edge from, to = null;
				Lane fromLane, toLane, via = null;

				for (Edge e : edges)
				{

					if (from == null && e.getEdgeIdentifier().equals(from_str))
						from = e;

					if (to == null && e.getEdgeIdentifier().equals(to_str))
						to = e;

					for (Lane l : e.getLanes())
					{
						if (fromLane == null && l.getIndex() == fromLane_int)
							fromLane = l;

						if (toLane == null && l.getIndex() == toLane_int)
							toLane = l;

						if (via == null && l.getLaneIdentifier().equals(via_str))
							via = l;
					}
				}

				connection = new Connection(from, to, fromLane, toLane, direction_str, state_str);
				connection.setVia(via);
				connections.add(connection);
				from.addConnection(connection);
				to.addConnection(connection);
			}
			
			System.out.println("Parsed "+map.getName()+" in "+(System.nanoTime()-startTime)+"ns");
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
