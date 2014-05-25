package road.carsystem.domain.netstate;

import com.thoughtworks.xstream.annotations.XStreamAlias;
import com.thoughtworks.xstream.annotations.XStreamAsAttribute;
import com.thoughtworks.xstream.annotations.XStreamImplicit;

import java.util.List;

/**
 * Created by geh on 14-5-14.
 */
public class Lane
{
    @XStreamAsAttribute @XStreamAlias("id")
    public String id;
    @XStreamImplicit(itemFieldName="vehicle")
    public List<Vehicle> vehicles;

}
