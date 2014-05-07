package road.movementdts.connections;

import road.movementdts.helpers.Pair;
import road.movementdts.helpers.RequestHelper;

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
            //Properties props = new Properties();
            //props.put(Context.INITIAL_CONTEXT_FACTORY, "org.jnp.interfaces.NamingContextFactory");
            //props.put(Context.URL_PKG_PREFIXES, "org.jnp.interfaces");)
            //props.put(Context.PROVIDER_URL, serverAddress);
            //props.put("addresslist", serverAddress);
            //this.connection = new RequestConnection(props, factoryName, sendTo);
            this.connection = new RequestConnection(null, factoryName, sendTo);
        }
        catch(Exception ex)
        {
            ex.printStackTrace();
        }
    }

    public <T> T remoteCall(String methodName, Class<T> returnType, Object... parameters)
    {
        String uniqueName = RequestHelper.getUniqueName(methodName, parameters);
        Pair<String, Object[]> pair = new Pair(uniqueName, parameters);
        String rawRequest = this.connection.serializer.serialize(pair);
        String rawReply = this.connection.send(rawRequest);
        T reply = this.connection.serializer.deSerialize(rawReply, returnType);
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
