//
// This file was generated by the JavaTM Architecture for XML Binding(JAXB) Reference Implementation, v2.2.4-2 
// See <a href="http://java.sun.com/xml/jaxb">http://java.sun.com/xml/jaxb</a> 
// Any modifications to this file will be lost upon recompilation of the source schema. 
// Generated on: 2014.03.28 at 11:15:27 AM CET 
//

package sumo.jaxb;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlType;

/**
 * <p>
 * Java class for intOptionType complex type.
 * 
 * <p>
 * The following schema fragment specifies the expected content contained within
 * this class.
 * 
 * <pre>
 * &lt;complexType name="intOptionType">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;attribute name="value" use="required" type="{http://www.w3.org/2001/XMLSchema}int" />
 *       &lt;attribute name="synonymes" type="{http://www.w3.org/2001/XMLSchema}string" />
 *       &lt;attribute name="type" type="{http://www.w3.org/2001/XMLSchema}string" />
 *       &lt;attribute name="help" type="{http://www.w3.org/2001/XMLSchema}string" />
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "intOptionType")
public class IntOptionType
{

	@XmlAttribute(name = "value", required = true)
	protected int value;
	@XmlAttribute(name = "synonymes")
	protected String synonymes;
	@XmlAttribute(name = "type")
	protected String type;
	@XmlAttribute(name = "help")
	protected String help;

	/**
	 * Gets the value of the value property.
	 * 
	 */
	public int getValue()
	{
		return value;
	}

	/**
	 * Sets the value of the value property.
	 * 
	 */
	public void setValue(int value)
	{
		this.value = value;
	}

	/**
	 * Gets the value of the synonymes property.
	 * 
	 * @return possible object is {@link String }
	 * 
	 */
	public String getSynonymes()
	{
		return synonymes;
	}

	/**
	 * Sets the value of the synonymes property.
	 * 
	 * @param value
	 *            allowed object is {@link String }
	 * 
	 */
	public void setSynonymes(String value)
	{
		this.synonymes = value;
	}

	/**
	 * Gets the value of the type property.
	 * 
	 * @return possible object is {@link String }
	 * 
	 */
	public String getType()
	{
		return type;
	}

	/**
	 * Sets the value of the type property.
	 * 
	 * @param value
	 *            allowed object is {@link String }
	 * 
	 */
	public void setType(String value)
	{
		this.type = value;
	}

	/**
	 * Gets the value of the help property.
	 * 
	 * @return possible object is {@link String }
	 * 
	 */
	public String getHelp()
	{
		return help;
	}

	/**
	 * Sets the value of the help property.
	 * 
	 * @param value
	 *            allowed object is {@link String }
	 * 
	 */
	public void setHelp(String value)
	{
		this.help = value;
	}

}
