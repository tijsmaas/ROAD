package road.movementservice.connections;

import road.movementdts.connections.MovementConnection;
import road.movementdts.helpers.Pair;
import road.movementdts.listeners.ConnectionListener;
import road.movementdts.serializers.Serializer;

import javax.jms.*;
import javax.naming.InitialContext;

/**
 * Created by geh on 10-4-14.
 * This class establishes a push based topic connection.
 */
public class SendConnection extends MovementConnection
{
    public SendConnection(String factoryName, String sendTo)
    {
        try
        {
            this.serializer = new Serializer();
            this.context = new InitialContext();

            this.factory = (ConnectionFactory)this.context.lookup(factoryName);
            this.connection = this.factory.createConnection();

            this.session = this.connection.createSession(false, Session.AUTO_ACKNOWLEDGE);

            this.sendTo = (Destination)this.context.lookup(sendTo);
            this.producer = this.session.createProducer(this.sendTo);
        }
        catch(Exception ex)
        {
            ex.printStackTrace();
        }
    }

    public void send(byte[] rawMessage)
    {
        byte[] rawReply = new byte[1];
        try
        {
            BytesMessage message = this.session.createBytesMessage();
            message.writeBytes(rawMessage);
            this.producer.send(message);
        }
        catch(Exception ex)
        {
            ex.printStackTrace();
        }
    }
}
