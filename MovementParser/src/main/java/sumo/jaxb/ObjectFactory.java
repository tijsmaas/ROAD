//
// This file was generated by the JavaTM Architecture for XML Binding(JAXB) Reference Implementation, v2.2.4-2 
// See <a href="http://java.sun.com/xml/jaxb">http://java.sun.com/xml/jaxb</a> 
// Any modifications to this file will be lost upon recompilation of the source schema. 
// Generated on: 2014.03.28 at 11:15:27 AM CET 
//

package sumo.jaxb;

import javax.xml.bind.JAXBElement;
import javax.xml.bind.annotation.XmlElementDecl;
import javax.xml.bind.annotation.XmlRegistry;
import javax.xml.namespace.QName;

/**
 * This object contains factory methods for each Java content interface and Java
 * element interface generated in the test.jaxb package.
 * <p>
 * An ObjectFactory allows you to programatically construct new instances of the
 * Java representation for XML content. The Java representation of XML content
 * can consist of schema derived interfaces and classes representing the binding
 * of schema type definitions, element declarations and model groups. Factory
 * methods for each of these are provided in this class.
 * 
 */
@XmlRegistry
public class ObjectFactory
{

	private final static QName _Tazs_QNAME = new QName("", "tazs");
	private final static QName _Net_QNAME = new QName("", "net");

	/**
	 * Create a new ObjectFactory that can be used to create new instances of
	 * schema derived classes for package: test.jaxb
	 * 
	 */
	public ObjectFactory()
	{
	}

	/**
	 * Create an instance of {@link NetType }
	 * 
	 */
	public NetType createNetType()
	{
		return new NetType();
	}

	/**
	 * Create an instance of {@link TazsType }
	 * 
	 */
	public TazsType createTazsType()
	{
		return new TazsType();
	}

	/**
	 * Create an instance of {@link StrOptionType }
	 * 
	 */
	public StrOptionType createStrOptionType()
	{
		return new StrOptionType();
	}

	/**
	 * Create an instance of {@link TazType }
	 * 
	 */
	public TazType createTazType()
	{
		return new TazType();
	}

	/**
	 * Create an instance of {@link LaneType }
	 * 
	 */
	public LaneType createLaneType()
	{
		return new LaneType();
	}

	/**
	 * Create an instance of {@link LocationType }
	 * 
	 */
	public LocationType createLocationType()
	{
		return new LocationType();
	}

	/**
	 * Create an instance of {@link ParamType }
	 * 
	 */
	public ParamType createParamType()
	{
		return new ParamType();
	}

	/**
	 * Create an instance of {@link FileOptionType }
	 * 
	 */
	public FileOptionType createFileOptionType()
	{
		return new FileOptionType();
	}

	/**
	 * Create an instance of {@link RoundaboutType }
	 * 
	 */
	public RoundaboutType createRoundaboutType()
	{
		return new RoundaboutType();
	}

	/**
	 * Create an instance of {@link FloatOptionType }
	 * 
	 */
	public FloatOptionType createFloatOptionType()
	{
		return new FloatOptionType();
	}

	/**
	 * Create an instance of {@link EdgeType }
	 * 
	 */
	public EdgeType createEdgeType()
	{
		return new EdgeType();
	}

	/**
	 * Create an instance of {@link JunctionType }
	 * 
	 */
	public JunctionType createJunctionType()
	{
		return new JunctionType();
	}

	/**
	 * Create an instance of {@link IntOptionType }
	 * 
	 */
	public IntOptionType createIntOptionType()
	{
		return new IntOptionType();
	}

	/**
	 * Create an instance of {@link PhaseType }
	 * 
	 */
	public PhaseType createPhaseType()
	{
		return new PhaseType();
	}

	/**
	 * Create an instance of {@link ConnectionType }
	 * 
	 */
	public ConnectionType createConnectionType()
	{
		return new ConnectionType();
	}

	/**
	 * Create an instance of {@link TlLogicType }
	 * 
	 */
	public TlLogicType createTlLogicType()
	{
		return new TlLogicType();
	}

	/**
	 * Create an instance of {@link RequestType }
	 * 
	 */
	public RequestType createRequestType()
	{
		return new RequestType();
	}

	/**
	 * Create an instance of {@link IntArrayOptionType }
	 * 
	 */
	public IntArrayOptionType createIntArrayOptionType()
	{
		return new IntArrayOptionType();
	}

	/**
	 * Create an instance of {@link BoolOptionType }
	 * 
	 */
	public BoolOptionType createBoolOptionType()
	{
		return new BoolOptionType();
	}

	/**
	 * Create an instance of {@link TazSubType }
	 * 
	 */
	public TazSubType createTazSubType()
	{
		return new TazSubType();
	}

	/**
	 * Create an instance of {@link ProhibitionType }
	 * 
	 */
	public ProhibitionType createProhibitionType()
	{
		return new ProhibitionType();
	}

	/**
	 * Create an instance of {@link TimeOptionType }
	 * 
	 */
	public TimeOptionType createTimeOptionType()
	{
		return new TimeOptionType();
	}

	/**
	 * Create an instance of {@link JAXBElement }{@code <}{@link TazsType }
	 * {@code >}
	 * 
	 */
	@XmlElementDecl(namespace = "", name = "tazs")
	public JAXBElement<TazsType> createTazs(TazsType value)
	{
		return new JAXBElement<TazsType>(_Tazs_QNAME, TazsType.class, null, value);
	}

	/**
	 * Create an instance of {@link JAXBElement }{@code <}{@link NetType }{@code >}
	 * 
	 */
	@XmlElementDecl(namespace = "", name = "net")
	public JAXBElement<NetType> createNet(NetType value)
	{
		return new JAXBElement<NetType>(_Net_QNAME, NetType.class, null, value);
	}

}
