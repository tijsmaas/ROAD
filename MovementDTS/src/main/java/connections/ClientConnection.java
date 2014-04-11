package connections;

import javafx.util.Pair;
import javax.naming.Context;
import java.util.Properties;

/**
 * Created by geh on 4-4-14.
 */
public abstract class ClientConnection
{
    private RequestConnection connection;

    public ClientConnection(String serverAddress, String factoryName, String sendTo)
    {
        try
        {
            Properties props = new Properties();
            props.put(Context.INITIAL_CONTEXT_FACTORY, "org.jnp.interfaces.NamingContextFactory");
            props.put(Context.URL_PKG_PREFIXES, "org.jnp.interfaces");
            props.put(Context.PROVIDER_URL, serverAddress);

            this.connection = new RequestConnection(props, factoryName, sendTo);
        }
        catch(Exception ex)
        {
            ex.printStackTrace();
        }

    }

    public <T> T RemoteCall(String method, Object... parameters)
    {
        Pair<String, Object[]> pair = new Pair(method, parameters);
        String rawRequest = this.connection.serializer.serialize(pair);
        String rawReply = this.connection.send(rawRequest);
        T reply = this.connection.serializer.deSerialize(rawReply);
        return reply;
    }

    public void start()
    {
        this.connection.start();
    }

    public void stop()
    {
        this.connection.stop();
    }
}
