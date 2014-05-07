package movementParser;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

import javax.ejb.Singleton;
import javax.inject.Inject;
import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBElement;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Unmarshaller;

import osm.jaxb.OsmType;
import parser.dao.ConnectionDAO;
import parser.dao.EdgeDAO;
import parser.dao.JunctionDAO;
import parser.dao.LaneDAO;
import sumo.jaxb.ConnectionType;
import sumo.jaxb.EdgeType;
import sumo.jaxb.JunctionType;
import sumo.jaxb.LaneType;
import sumo.jaxb.NetType;
import sumo.movements.jaxb.SumoNetstateType;
import road.movemententities.entities.Connection;
import road.movemententities.entities.Edge;
import road.movemententities.entities.Junction;
import road.movemententities.entities.Lane;
import org.xml.sax.SAXException;

@Singleton
public class MovementParser {

    @Inject
    private JunctionDAO junctionDAO;

    @Inject
    private EdgeDAO edgeDAO;

    @Inject
    private LaneDAO laneDAO;

    @Inject
    private ConnectionDAO connectionDAO;

    /**
     * 1. parse edges 2. skip traffic lights and junctions 3. parse connections
     *
     * @param map
     */
    public void parseSUMO(File map) throws SAXException {
        long startTime = System.nanoTime();
        JAXBElement<NetType> root = (JAXBElement<NetType>) parse(map, "sumo.jaxb");
        if (root != null) {

            /**
             * Persist edges, convert xmltypes to JPA road.movemententities.entities
             */
            List<Edge> edges = new ArrayList();
            for (EdgeType xmlEdge : root.getValue().getEdge()) {
                int prio = xmlEdge.getPriority() != null ? xmlEdge.getPriority().intValue() : 0;
                Edge edge = new Edge(xmlEdge.getId(), xmlEdge.getFunction(), xmlEdge.getType(), xmlEdge.getFrom(), xmlEdge.getTo(), prio);
                edgeDAO.create(edge);
                for (LaneType xmlLane : xmlEdge.getLane()) {
                    Lane lane = new Lane(edge, xmlLane.getId(), xmlLane.getIndex().intValue(), xmlLane.getSpeed(), xmlLane.getLength());
                    edge.addLane(lane);
                    laneDAO.create(lane);
                }
                edgeDAO.edit(edge);
                edges.add(edge);
            }

            List<Connection> connections = new ArrayList();
            for (ConnectionType xmlConn : root.getValue().getConnection()) {

                Connection connection = new Connection();

                /**
                 * Search for relations *
                 */
                String from_str = xmlConn.getFrom();
                String to_str = xmlConn.getTo();
                Integer fromLane_int = xmlConn.getFromLane().intValue();
                Integer toLane_int = xmlConn.getToLane().intValue();
                String via_str = xmlConn.getVia();
                String direction_str = xmlConn.getDir();
                String state_str = xmlConn.getState();

                Edge from = null, to = null;
                Lane fromLane = null, toLane = null, via = null;

                for (Edge e : edges) {

                    if (from == null && e.getEdgeIdentifier().equals(from_str)) {
                        from = e;
                    }

                    if (to == null && e.getEdgeIdentifier().equals(to_str)) {
                        to = e;
                    }

                    for (Lane l : e.getLanes()) {
                        if (fromLane == null && l.getIndex() == fromLane_int) {
                            fromLane = l;
                        }

                        if (toLane == null && l.getIndex() == toLane_int) {
                            toLane = l;
                        }

                        if (via == null && l.getLaneIdentifier() != null && l.getLaneIdentifier().equals(via_str)) {
                            via = l;
                        }
                    }
                }

                /**
                 * Add links(FK's) in edge from and edge to.
                 */
                if(from == null || to == null) throw new SAXException("ERROR: File "+map.getAbsolutePath()+" contains SUMO syntax error: connection from or to cannot be found");
                connection = new Connection(from, to, fromLane, toLane, direction_str, state_str);
                connectionDAO.create(connection);
                connection.setVia(via);
                connections.add(connection);
                from.addConnection(connection);
                to.addConnection(connection);
                edgeDAO.edit(from);
                edgeDAO.edit(to);
                connectionDAO.edit(connection);
            }
            

            List<Junction> junctions = new ArrayList<Junction>();
            for (JunctionType xmlJunc : root.getValue().getJunction()) {
                Junction junction = new Junction(xmlJunc.getId(), xmlJunc.getX(), xmlJunc.getY());
                junctions.add(junction);
                junctionDAO.create(junction);
            }

            System.out.println("Parsed " + map.getName() + " in " + (System.nanoTime() - startTime) + "ns");
        }
    }

    /**
     * 1. convert to SUMO (with netconvert) 2. parse as SUMO 3. Add extra
     * information such as road names(ways) and place names (nodes).
     *
     * @param map
     */
    public void parseOSM(File map) {
        long startTime = System.nanoTime();
        JAXBElement<OsmType> root = (JAXBElement<OsmType>) parse(map, "osm.jaxb");
        if (root != null) {
            System.out.println("Parsed " + map.getName() + " in " + (System.nanoTime() - startTime) + "ns");
            // TODO persist
        }
    }

    /**
     * 1. Parse positions of all vehicles 2. Insert into database
     *
     * @param changes
     */
    public void parseChanges(File changes) {
        long startTime = System.nanoTime();
        @SuppressWarnings("unchecked")
        JAXBElement<SumoNetstateType> root = (JAXBElement<SumoNetstateType>) parse(changes, "osm.jaxb");
        if (root != null) {
            System.out.println("Parsed " + changes.getName() + " in " + (System.nanoTime() - startTime) + "ns");
			// TODO persist

        }
    }

    @SuppressWarnings("rawtypes")
    private JAXBElement parse(File file, String classpath) {
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
}
