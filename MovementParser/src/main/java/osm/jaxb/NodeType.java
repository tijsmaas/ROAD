//
// This file was generated by the JavaTM Architecture for XML Binding(JAXB) Reference Implementation, v2.2.4-2 
// See <a href="http://java.sun.com/xml/jaxb">http://java.sun.com/xml/jaxb</a> 
// Any modifications to this file will be lost upon recompilation of the source schema. 
// Generated on: 2014.03.28 at 01:05:22 PM CET 
//

package osm.jaxb;

import java.util.ArrayList;
import java.util.List;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlSchemaType;
import javax.xml.bind.annotation.XmlType;
import javax.xml.datatype.XMLGregorianCalendar;

/**
 * <p>
 * Java class for nodeType complex type.
 * 
 * <p>
 * The following schema fragment specifies the expected content contained within
 * this class.
 * 
 * <pre>
 * &lt;complexType name="nodeType">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="tag" type="{}tagType" maxOccurs="unbounded" minOccurs="0"/>
 *       &lt;/sequence>
 *       &lt;attribute name="id" type="{http://www.w3.org/2001/XMLSchema}byte" />
 *       &lt;attribute name="timestamp" type="{http://www.w3.org/2001/XMLSchema}dateTime" />
 *       &lt;attribute name="visible" type="{http://www.w3.org/2001/XMLSchema}string" />
 *       &lt;attribute name="lat" type="{http://www.w3.org/2001/XMLSchema}float" />
 *       &lt;attribute name="lon" type="{http://www.w3.org/2001/XMLSchema}float" />
 *       &lt;attribute name="action" type="{http://www.w3.org/2001/XMLSchema}string" />
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "nodeType", propOrder = { "tag" })
public class NodeType
{

	protected List<TagType> tag;
	@XmlAttribute(name = "id")
	protected String id;
	@XmlAttribute(name = "timestamp")
	@XmlSchemaType(name = "dateTime")
	protected XMLGregorianCalendar timestamp;
	@XmlAttribute(name = "visible")
	protected String visible;
	@XmlAttribute(name = "lat")
	protected Float lat;
	@XmlAttribute(name = "lon")
	protected Float lon;
	@XmlAttribute(name = "action")
	protected String action;

	/**
	 * Gets the value of the tag property.
	 * 
	 * <p>
	 * This accessor method returns a reference to the live list, not a
	 * snapshot. Therefore any modification you make to the returned list will
	 * be present inside the JAXB object. This is why there is not a
	 * <CODE>set</CODE> method for the tag property.
	 * 
	 * <p>
	 * For example, to add a new item, do as follows:
	 * 
	 * <pre>
	 * getTag().add(newItem);
	 * </pre>
	 * 
	 * 
	 * <p>
	 * Objects of the following type(s) are allowed in the list {@link TagType }
	 * 
	 * 
	 */
	public List<TagType> getTag()
	{
		if (tag == null)
		{
			tag = new ArrayList<TagType>();
		}
		return this.tag;
	}

	/**
	 * Gets the value of the id property.
	 * 
	 * @return possible object is {@link Byte }
	 * 
	 */
	public String getId()
	{
		return id;
	}

	/**
	 * Sets the value of the id property.
	 * 
	 * @param value
	 *            allowed object is {@link Byte }
	 * 
	 */
	public void setId(String value)
	{
		this.id = value;
	}

	/**
	 * Gets the value of the timestamp property.
	 * 
	 * @return possible object is {@link XMLGregorianCalendar }
	 * 
	 */
	public XMLGregorianCalendar getTimestamp()
	{
		return timestamp;
	}

	/**
	 * Sets the value of the timestamp property.
	 * 
	 * @param value
	 *            allowed object is {@link XMLGregorianCalendar }
	 * 
	 */
	public void setTimestamp(XMLGregorianCalendar value)
	{
		this.timestamp = value;
	}

	/**
	 * Gets the value of the visible property.
	 * 
	 * @return possible object is {@link String }
	 * 
	 */
	public String getVisible()
	{
		return visible;
	}

	/**
	 * Sets the value of the visible property.
	 * 
	 * @param value
	 *            allowed object is {@link String }
	 * 
	 */
	public void setVisible(String value)
	{
		this.visible = value;
	}

	/**
	 * Gets the value of the lat property.
	 * 
	 * @return possible object is {@link Float }
	 * 
	 */
	public Float getLat()
	{
		return lat;
	}

	/**
	 * Sets the value of the lat property.
	 * 
	 * @param value
	 *            allowed object is {@link Float }
	 * 
	 */
	public void setLat(Float value)
	{
		this.lat = value;
	}

	/**
	 * Gets the value of the lon property.
	 * 
	 * @return possible object is {@link Float }
	 * 
	 */
	public Float getLon()
	{
		return lon;
	}

	/**
	 * Sets the value of the lon property.
	 * 
	 * @param value
	 *            allowed object is {@link Float }
	 * 
	 */
	public void setLon(Float value)
	{
		this.lon = value;
	}

	/**
	 * Gets the value of the action property.
	 * 
	 * @return possible object is {@link String }
	 * 
	 */
	public String getAction()
	{
		return action;
	}

	/**
	 * Sets the value of the action property.
	 * 
	 * @param value
	 *            allowed object is {@link String }
	 * 
	 */
	public void setAction(String value)
	{
		this.action = value;
	}

}
