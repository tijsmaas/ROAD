package road.movementservice.connections;

import road.movementdts.helpers.Pair;
import road.movementdts.helpers.RequestHelper;

import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ConcurrentHashMap;

/**
 * Created by geh on 10-4-14.
 */
public abstract class ServerConnection<T> implements ConnectionListener
{
    private ReplyConnection connection;
    private ConcurrentHashMap<String, Method> methods;
    private T instance;

    public ServerConnection(String factoryName, String listenTo)
    {
        this.connection = new ReplyConnection(factoryName, listenTo, this);
    }

    public void initRpc(Class<T> type, T instance)
    {
        this.instance = instance;
        this.methods = new ConcurrentHashMap<>();
        for (Method method : type.getMethods())
        {
            List<Class> parameters = new ArrayList<Class>();
            for (Class par : method.getParameterTypes())
            {
                try
                {
                    parameters.add(par);
                } catch (Exception ex)
                {
                    ex.printStackTrace();
                }
            }
            String name = RequestHelper.getUniqueName(method.getName(), parameters);
            this.methods.put(name, method);
        }
    }

    @Override
    public byte[] receive(Pair<String, Object[]> request)
    {
        byte[] rawResult = new byte[0];
        try
        {
            Method method = this.methods.get(request.getFirst());
            Object result = method.invoke(this.instance, request.getSecond());
            rawResult = this.connection.serializer.serializeBytes(method.getReturnType().cast(result));
        }
        catch (Exception ex)
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
