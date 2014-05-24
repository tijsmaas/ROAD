package road.movementservice.connections;

import road.movementdts.listeners.ConnectionListener;
import road.movementdts.connections.MovementConnection;
import road.movementdts.helpers.Pair;
import road.movementdts.serializers.Serializer;
import javax.jms.*;
import javax.naming.InitialContext;

/**
 * Created by geh on 10-4-14.
 */
public class ReplyConnection extends MovementConnection implements MessageListener
{
    private ConnectionListener listener;

    public ReplyConnection(String factoryName, String listenTo, ConnectionListener listener)
    {
        try
        {
            this.serializer = new Serializer();
            this.context = new InitialContext();

            this.factory = (ConnectionFactory)this.context.lookup(factoryName);
            this.connection = this.factory.createConnection();

            this.session = this.connection.createSession(false, Session.AUTO_ACKNOWLEDGE);

            this.producer = this.session.createProducer(null);
            this.producer.setDeliveryMode(DeliveryMode.NON_PERSISTENT);

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
            byte[] bytes = new byte[(int)bytesMessage.getBodyLength()];
            bytesMessage.readBytes(bytes);
            Pair<String, Object[]> pair = this.serializer.deSerialize(bytes, Pair.class);

            byte[] rawReply = this.listener.receive(pair);
            BytesMessage reply = this.session.createBytesMessage();
            reply.writeBytes(rawReply);
            this.producer.send(message.getJMSReplyTo(), reply);
        }
        catch(Exception ex)
        {
            ex.printStackTrace();
        }
    }
}
