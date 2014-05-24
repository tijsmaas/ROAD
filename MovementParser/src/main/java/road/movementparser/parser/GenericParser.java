package road.movementparser.parser;

import java.io.ByteArrayInputStream;
import java.io.File;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBElement;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Unmarshaller;
import javax.xml.stream.XMLEventReader;
import javax.xml.stream.XMLInputFactory;
import javax.xml.stream.XMLStreamException;

public class GenericParser
{
    private Unmarshaller unmarshaller;

    public GenericParser(String classpath)
    {
        /**
         * Read XML(JAXB) annotations from the classes in this package *
         */
        try
        {
            JAXBContext jc = JAXBContext.newInstance(classpath);
            this.unmarshaller = jc.createUnmarshaller();
        }
        catch (JAXBException e)
        {
            System.err.println("Classpath lookup failed");
            e.printStackTrace();
        }
    }

    /**
     * Parse XML from file with classes in classpath
     * @param file
     * @return JAXB root element
     */
    @SuppressWarnings("rawtypes")
    public JAXBElement parse(File file)
    {
        JAXBElement element = null;
        try
        {
            element = (JAXBElement)unmarshaller.unmarshal(file);
        }
        catch(Exception ex)
        {
            ex.printStackTrace();
        }
        finally
        {
            return element;
        }
    }
    
    /**
     * Parse XML from string movements with classes in classpath
     * @param movements
     * @return JAXB root element
     */
    public JAXBElement parse(String movements)
    {
        JAXBElement element = null;
        try
        {
            XMLEventReader reader = XMLInputFactory.newFactory().createXMLEventReader(new ByteArrayInputStream(movements.getBytes()));
            element = (JAXBElement)unmarshaller.unmarshal(reader);
        }
        catch(Exception ex)
        {
            System.err.println("Parsing of string failed");
            ex.printStackTrace();
        }
        finally
        {
            return element;
        }
    }
}
