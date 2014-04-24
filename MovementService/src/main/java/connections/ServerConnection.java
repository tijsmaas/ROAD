package connections;

import helpers.Pair;
import helpers.RequestHelper;

import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ConcurrentHashMap;

/**
 * Created by geh on 10-4-14.
 */
public abstract class ServerConnection <T> implements ConnectionListener
{
    private ReplyConnection connection;
    private T instance;
    private ConcurrentHashMap<String, Method> methods;

    public ServerConnection(String factoryName, String listenTo)
    {
        this.connection = new ReplyConnection(factoryName, listenTo, this);
    }

    public void initRpc(Class<T> type, T instance)
    {
        this.instance = instance;
        this.methods = new ConcurrentHashMap<>();
        for(Method method : type.getMethods())
        {
            List<Object> parameters = new ArrayList<Object>();
            for(Class par : method.getParameterTypes())
            {
                try
                {
                    parameters.add(par.newInstance());
                }
                catch(Exception ex)
                {
                    ex.printStackTrace();
                }
            }
            String name = RequestHelper.getUniqueName(method.getName(), parameters.toArray());
            this.methods.put(name, method);
        }
    }

    @Override
    public String receive(Pair<String, ArrayList<Object>> request)
    {
        String rawResult = "";
        try
        {
            Method method = this.methods.get(request.getFirst());
            Object result = method.invoke(instance, request.getSecond().toArray());
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
