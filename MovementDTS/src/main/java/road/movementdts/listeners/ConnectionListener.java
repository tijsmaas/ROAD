package road.movementdts.listeners;

import road.movementdts.helpers.Pair;
import java.util.ArrayList;

/**
 * Created by geh on 10-4-14.
 * An interface that can be used to receive a Pair<string, object[]> and return a byte
 * array reply.
 */
public interface ConnectionListener
{
    public byte[] receive(Pair<String,Object[]> request);
}
