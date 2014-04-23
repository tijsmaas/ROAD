package connections;

import helpers.Pair;

import javax.jms.TextMessage;

/**
 * Created by geh on 10-4-14.
 */
public interface ConnectionListener
{
    public String receive(Pair<String, Object[]> request);
}
