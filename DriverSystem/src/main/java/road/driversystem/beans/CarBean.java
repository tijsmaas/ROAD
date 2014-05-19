package road.driversystem.beans;

import org.w3c.dom.Document;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

import javax.ejb.Stateless;
import javax.inject.Named;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import java.io.IOException;
import java.util.HashMap;

/**
 * Created by Mitch on 15-5-2014.
 */
@Named @Stateless
public class CarBean {
    private HashMap<String, String> details = new HashMap<>();

    public String getDetail(String property) {
        return details.get(property);
    }

    public void getCarDetails(String kenteken){
        HashMap<String, String> values = new HashMap<>();
        try {
            DocumentBuilder dBuilder = DocumentBuilderFactory.newInstance().newDocumentBuilder();
            Document doc = dBuilder.parse("https://api.datamarket.azure.com/opendata.rdw/VRTG.Open.Data/v1/KENT_VRTG_O_DAT?$filter=Kenteken eq '" + kenteken + "'");
            if(doc.hasChildNodes()) {
                handleNodeList(values, doc.getChildNodes());
            }
        } catch (SAXException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (ParserConfigurationException e) {
            e.printStackTrace();
        }
    }

    private HashMap<String, String> handleNodeList(HashMap<String, String> values, NodeList nodes) {
        for (int counter = 0; counter < nodes.getLength(); counter++) {
            Node node = nodes.item(counter);
            // Make sure it's an element node.
            if (node.getNodeType() == Node.ELEMENT_NODE) {
                values.put(node.getNodeName(), node.getNodeValue());
            }
        }
        return values;
    }
}