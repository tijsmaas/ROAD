package road.carsystem.domain;

import com.thoughtworks.xstream.annotations.XStreamAlias;
import com.thoughtworks.xstream.annotations.XStreamAsAttribute;
import com.thoughtworks.xstream.annotations.XStreamImplicit;

/**
 * Created by geh on 14-5-14.
 */
public class Vehicle
{
    @XStreamAsAttribute @XStreamAlias("id")
    public String id;
    @XStreamAsAttribute @XStreamAlias("pos")
    public Float pos;
    @XStreamAsAttribute @XStreamAlias("speed")
    public Float speed;
}
