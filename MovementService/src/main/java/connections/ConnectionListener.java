package connections;

import helpers.Pair;
import java.util.ArrayList;

/**
 * Created by geh on 10-4-14.
 */
public interface ConnectionListener
{
    public String receive(Pair<String,ArrayList<Object>> request);
}
