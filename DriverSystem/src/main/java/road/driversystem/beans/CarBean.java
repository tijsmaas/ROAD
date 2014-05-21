package road.driversystem.beans;

import com.ocpsoft.pretty.PrettyContext;
import org.w3c.dom.Document;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;
import road.driversystem.utils.Utlities;

import javax.ejb.Stateless;
import javax.faces.context.ExternalContext;
import javax.faces.context.FacesContext;
import javax.inject.Named;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLEncoder;
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
        details = new HashMap<>();
        ExternalContext context = FacesContext.getCurrentInstance().getExternalContext();
        try {
            URL url = new URL("https://api.datamarket.azure.com/opendata.rdw/VRTG.Open.Data/v1/KENT_VRTG_O_DAT?$filter=Kenteken%20eq%20%27" + kenteken + "%27");
            HttpURLConnection con = (HttpURLConnection) url.openConnection();

            con.setRequestMethod("GET");
            con.setRequestProperty("Accept", "application/atom+xml,application/xml; charset=utf-8");

            con.connect();

            InputStream is = con.getInputStream();
            DocumentBuilder dBuilder = DocumentBuilderFactory.newInstance().newDocumentBuilder();
            Document doc = dBuilder.parse(is);
            if(doc.hasChildNodes()) {
                handleNodeList(details, doc.getChildNodes());
                context.redirect(Utlities.getContextRoot() + "/carDetails/");
            }
        } catch (SAXException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (ParserConfigurationException e) {
            e.printStackTrace();
        }
    }

    private HashMap<String, String> handleNodeList(HashMap<String, String> values, NodeList nodes)
    {
        for (int counter = 0; counter < nodes.getLength(); counter++)
        {
            Node node = nodes.item(counter);
            // Make sure it's an element node.
            if (node.getNodeType() == Node.ELEMENT_NODE)
            {
                if(node.getNodeName().contains("d:")) {
                    values.put(node.getNodeName().substring(2), node.getTextContent());
                }
                if(node.hasChildNodes())
                {
                    handleNodeList(values, node.getChildNodes());
                }
            }
        }
        return values;
    }
}