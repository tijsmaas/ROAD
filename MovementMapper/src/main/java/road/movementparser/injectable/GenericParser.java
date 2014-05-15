package road.movementparser.injectable;

import java.io.BufferedInputStream;
import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBElement;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Unmarshaller;
import javax.xml.stream.XMLInputFactory;
import javax.xml.stream.XMLStreamException;

public class GenericParser {
    /**
     * Parse XML from file with classes in classpath
     * @param file
     * @param classpath
     * @return JAXB root element
     */
    @SuppressWarnings("rawtypes")
    public JAXBElement parse(File file, String classpath) {
        JAXBContext jc;
        try {
            /**
             * Read XML(JAXB) annotations from the classes in this package *
             */
            jc = JAXBContext.newInstance(classpath);
            Unmarshaller unmarshaller = jc.createUnmarshaller();
            BufferedInputStream bis = new BufferedInputStream(new FileInputStream(file));
            return (JAXBElement) unmarshaller.unmarshal(bis);
        } catch (JAXBException | OutOfMemoryError | FileNotFoundException e) {
            System.err.println("Parsing of " + file.getName() + " failed");
            e.printStackTrace();
        }
        return null;
    }
    
    /**
     * Parse XML from string movements with classes in classpath
     * @param movements
     * @param classpath
     * @return JAXB root element
     */
    public JAXBElement parse(String movements, String classpath) {
        JAXBContext jc;
        try {
            /**
             * Read XML(JAXB) annotations from the classes in this package *
             */
            jc = JAXBContext.newInstance(classpath);
            Unmarshaller unmarshaller = jc.createUnmarshaller();
            return (JAXBElement) unmarshaller.unmarshal(
                    XMLInputFactory.newFactory().createXMLEventReader(
                            new ByteArrayInputStream(movements.getBytes())));
        } catch (JAXBException e) {
            System.err.println("Parsing of string failed");
            e.printStackTrace();
        } catch (XMLStreamException ex) {
            Logger.getLogger(GenericParser.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }
}
