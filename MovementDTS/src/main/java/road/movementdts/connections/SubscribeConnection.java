package road.movementdts.connections;

import road.movementdts.listeners.SubscribeListener;
import road.movementdts.serializers.Serializer;

import javax.jms.*;
import javax.naming.InitialContext;
import java.util.Properties;

/**
 * Created by geh on 4-4-14.
 * This class is not thread safe! If you want send requests with multiple threads
 * use one RequestConnection for each thread.
 */
public class SubscribeConnection extends MovementConnection implements MessageListener
{
    private SubscribeListener listener;

    /***
     * Used to establish a basic syncrhonous request end of a request-reply channel.
     * @param props these are the settings for connecting to a remote jms server. set to null
     *              to use a local server
     * @param factoryName jndi name of the queuefactory on the application server
     * @param listenTo jndi name of the topic this connection subscribes to
     */
    public SubscribeConnection(Properties props, SubscribeListener listener, String factoryName, String listenTo)
    {
        this.serializer = new Serializer();

        try
        {
            if(props == null)
            {
                this.context = new InitialContext();
            }
            else
            {
                this.context = new InitialContext(props);
            }

            this.factory = (ConnectionFactory)this.context.lookup(factoryName);
            this.connection = this.factory.createConnection();
            this.session = this.connection.createSession();

            this.listenTo = (Destination)this.context.lookup(listenTo);
            this.consumer = this.session.createConsumer(this.listenTo);
            this.consumer.setMessageListener(this);

            this.listener = listener;
        }
        catch(Exception ex)
        {
            ex.printStackTrace();
        }
    }

    @Override
    public void onMessage(Message message)
    {
        try
        {
            BytesMessage bytesMessage = (BytesMessage)message;
            byte[] rawMessage = new byte[(int)bytesMessage.getBodyLength()];
            bytesMessage.readBytes(rawMessage);
            this.listener.receive(rawMessage);
        }
        catch(Exception ex)
        {
            ex.printStackTrace();
        }
    }
}
