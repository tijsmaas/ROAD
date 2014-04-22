package connections;

import serializers.Serializer;
import javax.jms.*;
import javax.naming.Context;

/**
 * Created by geh on 10-4-14.
 */
public abstract class MovementConnection
{
    public final static String FactoryName = "roadFactory";
    public final static String BillSystemQueue = "billRequestQueue";
    public final static String CarSystemQueue = "carRequestQueue";
    public final static String PoliceSystemQueue = "policeRequestQueue";
    public final static String DriverSystemQueue = "driverRequestQueue";

    protected Context context;
    protected ConnectionFactory factory;
    protected Connection connection;
    // The session is what creates the producer and consumer
    // from the listenTo and sendTo destinations
    protected Session session;
    // Used to establish a request/reply channel.
    // Consumer reads from a temporary queue, which gets sent to
    // the server through the sendTo destination.
    protected Destination listenTo;
    protected Destination sendTo;
    // These are the objects that can actually send or receive.
    // Producer can send and consumer can have a listener to handle
    // incoming messages.
    protected MessageProducer producer;
    protected MessageConsumer consumer;
    // Used to (de)serialize raw JMS messages bodies
    protected Serializer serializer;

    public void start()
    {
        try
        {
            this.connection.start();
        }
        catch(Exception ex)
        {
            ex.printStackTrace();
        }
    }

    public void stop()
    {
        try
        {
            this.connection.stop();
        }
        catch(Exception ex)
        {
            ex.printStackTrace();
        }

    }
}
