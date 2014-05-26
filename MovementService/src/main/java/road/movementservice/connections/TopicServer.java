package road.movementservice.connections;

import road.movementdts.helpers.Pair;
import road.movementdts.helpers.RequestHelper;
import road.movementdts.listeners.ConnectionListener;

import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ConcurrentHashMap;

/**
 * Created by geh on 10-4-14.
 * This class is the base for a push based JMS Topic server.
 */
public abstract class TopicServer
{
    private SendConnection connection;

    public TopicServer(String factoryName, String sendTo)
    {
        this.connection = new SendConnection(factoryName, sendTo);
    }

    public <T> void send(T content)
    {
        byte[] rawMessage = this.connection.serializer.serializeBytes(content);
        this.connection.send(rawMessage);
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
