package road.movementdts.connections;

import road.movementdts.helpers.Pair;
import road.movementdts.helpers.RequestHelper;

/**
 * Created by geh on 4-4-14.
 *
 * This class is to be used for Client side RPC connections to the MovementService.
 */
public abstract class ClientConnection
{
    /**
     * The actual JMS connection used to establish communication with the MovementService
     */
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

    /**
     *
     * @param methodName sadly a 'magic' string. this has to be the short name for the method that one wants to call. like 'remoteCall'
     * @param returnType the class of the intended return type. this has to be of type T, the generic type itself is not sufficient.
     * @param parameters the parameters in the correct order and amount for the method that is calling remoteCall.
     * @param <T> the return type
     * @return returns an object of type T. possible for this to be null.
     */
    public <T> T remoteCall(String methodName, Class<T> returnType, Object... parameters)
    {
        String uniqueName = RequestHelper.getUniqueName(methodName, parameters);
        Pair<String, Object[]> pair = new Pair(uniqueName, parameters);
        byte[] rawRequest = this.connection.serializer.serializeBytes(pair);
        byte[] rawReply = this.connection.send(rawRequest);
        //String rawRequest = this.connection.serializer.serialize(pair);
        //String rawReply = this.connection.send(rawRequest);
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
