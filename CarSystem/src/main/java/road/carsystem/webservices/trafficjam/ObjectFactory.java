
package road.carsystem.webservices.trafficjam;

import javax.xml.bind.JAXBElement;
import javax.xml.bind.annotation.XmlElementDecl;
import javax.xml.bind.annotation.XmlRegistry;
import javax.xml.namespace.QName;


/**
 * This object contains factory methods for each 
 * Java content interface and Java element interface 
 * generated in the road.carsystem.webservices.trafficjam package. 
 * <p>An ObjectFactory allows you to programatically 
 * construct new instances of the Java representation 
 * for XML content. The Java representation of XML 
 * content can consist of schema derived interfaces 
 * and classes representing the binding of schema 
 * type definitions, element declarations and model 
 * groups.  Factory methods for each of these are 
 * provided in this class.
 * 
 */
@XmlRegistry
public class ObjectFactory {

    private final static QName _GetTrafficJamsResponse_QNAME = new QName("http://services.jamsystem.road/", "getTrafficJamsResponse");
    private final static QName _GetTrafficJams_QNAME = new QName("http://services.jamsystem.road/", "getTrafficJams");
    private final static QName _JamException_QNAME = new QName("http://services.jamsystem.road/", "JamException");

    /**
     * Create a new ObjectFactory that can be used to create new instances of schema derived classes for package: road.carsystem.webservices.trafficjam
     * 
     */
    public ObjectFactory() {
    }

    /**
     * Create an instance of {@link GetTrafficJams }
     * 
     */
    public GetTrafficJams createGetTrafficJams() {
        return new GetTrafficJams();
    }

    /**
     * Create an instance of {@link JamException }
     * 
     */
    public JamException createJamException() {
        return new JamException();
    }

    /**
     * Create an instance of {@link GetTrafficJamsResponse }
     * 
     */
    public GetTrafficJamsResponse createGetTrafficJamsResponse() {
        return new GetTrafficJamsResponse();
    }

    /**
     * Create an instance of {@link LaneDto }
     * 
     */
    public LaneDto createLaneDto() {
        return new LaneDto();
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link GetTrafficJamsResponse }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://services.jamsystem.road/", name = "getTrafficJamsResponse")
    public JAXBElement<GetTrafficJamsResponse> createGetTrafficJamsResponse(GetTrafficJamsResponse value) {
        return new JAXBElement<GetTrafficJamsResponse>(_GetTrafficJamsResponse_QNAME, GetTrafficJamsResponse.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link GetTrafficJams }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://services.jamsystem.road/", name = "getTrafficJams")
    public JAXBElement<GetTrafficJams> createGetTrafficJams(GetTrafficJams value) {
        return new JAXBElement<GetTrafficJams>(_GetTrafficJams_QNAME, GetTrafficJams.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link JamException }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://services.jamsystem.road/", name = "JamException")
    public JAXBElement<JamException> createJamException(JamException value) {
        return new JAXBElement<JamException>(_JamException_QNAME, JamException.class, null, value);
    }

}
