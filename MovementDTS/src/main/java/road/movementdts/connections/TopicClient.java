package road.movementdts.connections;


import road.movementdts.connections.*;
import road.movementdts.listeners.SubscribeListener;

/**
 * This is a generic client for a java.jms.Topic channel. This will receive
 * byte arrays in the abstract method receive. Implement this and deserialize it
 * with the road.movementdts.serializers.Serializer class.
 * Created by geh on 22-4-14.
 */
public abstract class TopicClient implements SubscribeListener
{
    protected SubscribeConnection connection;

    public TopicClient(String factoryName, String listenTo)
    {
        try
        {
            //Properties props = new Properties();
            //props.put(Context.INITIAL_CONTEXT_FACTORY, "org.jnp.interfaces.NamingContextFactory");
            //props.put(Context.URL_PKG_PREFIXES, "org.jnp.interfaces");)
            //props.put(Context.PROVIDER_URL, serverAddress);
            //props.put("addresslist", serverAddress);
            //this.connection = new RequestConnection(props, factoryName, listenTo);
            this.connection = new SubscribeConnection(null, this, factoryName, listenTo);
        }
        catch(Exception ex)
        {
            ex.printStackTrace();
        }
    }

    @Override
    public abstract void receive(byte[] message);

    public void start()
    {
        this.connection.start();
    }

    public void stop()
    {
        this.connection.stop();
    }
}
