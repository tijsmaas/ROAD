package road.carsystem.domain;

import com.thoughtworks.xstream.annotations.XStreamAlias;
import com.thoughtworks.xstream.annotations.XStreamAsAttribute;
import com.thoughtworks.xstream.annotations.XStreamImplicit;

import java.util.List;

/**
 * Created by geh on 14-5-14.
 */
public class Edge
{
    @XStreamAsAttribute @XStreamAlias("id")
    public String id;
    @XStreamImplicit(itemFieldName="lane")
    public List<Lane> lanes;
}
