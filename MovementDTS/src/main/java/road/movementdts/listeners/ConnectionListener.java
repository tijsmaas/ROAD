package road.movementdts.listeners;

import road.movementdts.helpers.Pair;
import java.util.ArrayList;

/**
 * Created by geh on 10-4-14.
 */
public interface ConnectionListener
{
    public byte[] receive(Pair<String,Object[]> request);
}
