package connections;

import helpers.Pair;
import java.lang.reflect.Method;
import java.util.concurrent.ConcurrentHashMap;

/**
 * Created by geh on 10-4-14.
 */
public abstract class ServerConnection implements ConnectionListener
{
    private ReplyConnection connection;
    private ConcurrentHashMap<String, Method> methods;

    public ServerConnection(String factoryName, String listenTo)
    {
        this.connection = new ReplyConnection(factoryName, listenTo, this);
    }

    @Override
    public String receive(Pair<String, Object[]> request)
    {
        String rawResult = "";
        try
        {
            Method method = this.methods.get(request.getFirst());
            Object result = method.invoke(request.getSecond());
            rawResult = this.connection.serializer.serialize(method.getReturnType().cast(result));
        }
        catch(Exception ex)
        {
            ex.printStackTrace();
        }
        finally
        {
            return rawResult;
        }
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
