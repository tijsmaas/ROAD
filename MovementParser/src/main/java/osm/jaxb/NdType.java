//
// This file was generated by the JavaTM Architecture for XML Binding(JAXB) Reference Implementation, v2.2.4-2 
// See <a href="http://java.sun.com/xml/jaxb">http://java.sun.com/xml/jaxb</a> 
// Any modifications to this file will be lost upon recompilation of the source schema. 
// Generated on: 2014.03.28 at 01:05:22 PM CET 
//

package osm.jaxb;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlType;
import javax.xml.bind.annotation.XmlValue;

/**
 * <p>
 * Java class for ndType complex type.
 * 
 * <p>
 * The following schema fragment specifies the expected content contained within
 * this class.
 * 
 * <pre>
 * &lt;complexType name="ndType">
 *   &lt;simpleContent>
 *     &lt;extension base="&lt;http://www.w3.org/2001/XMLSchema>string">
 *       &lt;attribute name="ref" type="{http://www.w3.org/2001/XMLSchema}byte" />
 *     &lt;/extension>
 *   &lt;/simpleContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "ndType", propOrder = { "value" })
public class NdType
{

	@XmlValue
	protected String value;
	@XmlAttribute(name = "ref")
	protected Byte ref;

	/**
	 * Gets the value of the value property.
	 * 
	 * @return possible object is {@link String }
	 * 
	 */
	public String getValue()
	{
		return value;
	}

	/**
	 * Sets the value of the value property.
	 * 
	 * @param value
	 *            allowed object is {@link String }
	 * 
	 */
	public void setValue(String value)
	{
		this.value = value;
	}

	/**
	 * Gets the value of the ref property.
	 * 
	 * @return possible object is {@link Byte }
	 * 
	 */
	public Byte getRef()
	{
		return ref;
	}

	/**
	 * Sets the value of the ref property.
	 * 
	 * @param value
	 *            allowed object is {@link Byte }
	 * 
	 */
	public void setRef(Byte value)
	{
		this.ref = value;
	}

}
