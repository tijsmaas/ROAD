import entities.Connection;
import entities.Edge;
import entities.Edge.EdgeFunction;
import entities.Lane;
import org.xml.sax.Attributes;
import org.xml.sax.Locator;
import org.xml.sax.SAXException;
import org.xml.sax.SAXParseException;
import org.xml.sax.helpers.DefaultHandler;
import utils.TypeConversion;

import java.util.ArrayList;
import java.util.List;
import java.util.Stack;

public class SUMOParser extends DefaultHandler
{
	/** SAX stuff **/
	private Locator locator;

	private enum element
	{
		NET, EDGE, LANE, LANES, CONNECTION
	};

	// lists currently open elements
	private Stack<element> domStack;

	/** temporary entities and lists **/
	private Edge edge;
	private Lane lane;
	private Connection connection;
	private List<Edge> edgeList;
	private List<Connection> connectionList;

	public SUMOParser()
	{
		domStack = new Stack();
		edgeList = new ArrayList();
		connectionList = new ArrayList();
	}

	@Override
	public void startElement(String uri, String localName, String qName, Attributes attributes) throws SAXException
	{
		/** Connection element, inside Root **/
		if ("connection".equalsIgnoreCase(qName))
		{
			// Safety check: can edge node be placed here in the XML DOM?
			if (!domStack.peek().equals(element.NET))
				this.fatalError(new SAXParseException("Connection cannot be nested", this.locator));

			domStack.push(element.CONNECTION);

			/** Search for relations **/
			String from_str = attributes.getValue("from");
			String to_str = attributes.getValue("to");
			Integer fromLane_int = TypeConversion.toInteger(attributes.getValue("fromLane"));
			Integer toLane_int = TypeConversion.toInteger(attributes.getValue("toLane"));
			String via_str = attributes.getValue("via");
			String direction_str = attributes.getValue("dir");
			String state_str = attributes.getValue("state");

			Edge from = null;
			Edge to = null;
			Lane fromLane = null;
			Lane toLane = null;
			Lane via = null;

			for (Edge e : edgeList)
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
			connectionList.add(connection);
			from.addConnection(connection);
			to.addConnection(connection);			

			/** Edge element, inside Root **/
		} else if ("edge".equalsIgnoreCase(qName))
		{
			// Safety check: can edge node be placed here in the XML DOM?
			if (!domStack.peek().equals(element.NET))
				this.fatalError(new SAXParseException("Edge cannot be nested", this.locator));

			domStack.push(element.EDGE);
			edge = new Edge();
			/**
			 * Based on value of attribute edge.function requires more or less
			 * edge attributes are required. (See the XSD here:
			 * http://sumo.sf.net/xsd/netconvertConfiguration.xsd)
			 */
			String function = attributes.getValue("function");
			if (function == null || "normal".equalsIgnoreCase(function))
			{
				edge.setFunction(EdgeFunction.normal);
				edge.setFrom(attributes.getValue("from"));
				edge.setTo(attributes.getValue("to"));
				edge.setPriority(TypeConversion.toInteger(attributes.getValue("priority")));
				edge.setType(attributes.getValue("type"));
			} else if ("internal".equalsIgnoreCase(function))
			{
				edge.setFunction(EdgeFunction.internal);
			} else if ("connector".equalsIgnoreCase(function))
			{
				this.warning(new SAXParseException(
						"Edge.function=connector has not been implemented, this element will be ignored", this.locator));
				edge.setFunction(EdgeFunction.connector);
			}

			/** Lane element, inside edge **/
		} else if ("lane".equalsIgnoreCase(qName))
		{
			// Safety check: can lane node be placed here in the XML DOM?
			if (!domStack.peek().equals(element.EDGE) && !domStack.peek().equals(element.LANES))
				this.fatalError(new SAXParseException("Lane needs to be nested inside an edge", this.locator));

			domStack.push(element.LANE);

			lane = new Lane(attributes.getValue("id"), TypeConversion.toInteger(attributes.getValue("index")));
			lane.setSpeed(TypeConversion.toFloat(attributes.getValue("speed")));
			lane.setLength(TypeConversion.toFloat(attributes.getValue("length")));
			edge.addLane(lane);

			/** Root element **/
		} else if ("net".equalsIgnoreCase(qName))
		{
			// Safety check: can root node be placed here in the XML DOM?
			if (!domStack.isEmpty())
				this.fatalError(new SAXParseException("Element <net> is nested but should be the root element",
						this.locator));

			domStack.push(element.NET);

		} else if ("lanes".equalsIgnoreCase(qName))
		{
			// Safety check: can lanes node be placed here in the XML DOM?
			if (!domStack.peek().equals(element.EDGE))
				this.fatalError(new SAXParseException("Lanes placeholder needs to be nested inside an edge",
						this.locator));

			domStack.push(element.LANES);

		} else
		{
			/**
			 * Restrictive safety check: other elements should not be inside
			 * edge or lane
			 **/
			element nodetype = domStack.peek();
			switch (nodetype)
			{
			case EDGE:
			case LANE:
			case LANES:
			case CONNECTION:
				this.error(new SAXParseException("Element has invalid parent element " + nodetype, this.locator));
			}

		}
	}

	@Override
	public void endElement(String uri, String localName, String qName) throws SAXException
	{
		super.endElement(uri, localName, qName);

		/**
		 * Only elements that are really opened and closed. We also do not close
		 * the root element, because if we do then we can open a second root
		 * element.
		 **/
		if ("edge".equalsIgnoreCase(qName))
		{
			// Safety check: do we actually close an opened edge?
			if (domStack.pop().equals(element.EDGE))
			{
				edgeList.add(edge);
			} else
			{
				this.fatalError(new SAXParseException(
						"Edge element not closed properly, mismatching open/close elements", this.locator));
			}

			// Safety check: do we actually close an opened lane?
		} else if ("lane".equalsIgnoreCase(qName) && !domStack.pop().equals(element.LANE))
		{
			this.fatalError(new SAXParseException("Lane element not closed properly, mismatching open/close elements",
					this.locator));

			// Safety check: do we actually close an opened lanes element?
		} else if ("lanes".equalsIgnoreCase(qName) && !domStack.pop().equals(element.LANES))
		{
			this.fatalError(new SAXParseException("Lanes element not closed properly, mismatching open/close elements",
					this.locator));

			// Safety check: do we actually close an opened connection element?
		} else if ("connection".equalsIgnoreCase(qName) && !domStack.pop().equals(element.CONNECTION))
		{
			this.fatalError(new SAXParseException(
					"Connection element not closed properly, mismatching open/close elements", this.locator));
		}
	}

	/**
	 * The location of the SAX parser is needed when something goes wrong, so
	 * that an error can show the line number of the problem in the XML file.
	 */
	@Override
	public void setDocumentLocator(Locator locator)
	{
		super.setDocumentLocator(locator);
		this.locator = locator;
	}

	public List<Edge> readEdges()
	{
		return this.edgeList;
	}
	
	public List<Connection> readConnections()
	{
		return this.connectionList;
	}

}
