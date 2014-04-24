package serializers;

import com.google.gson.Gson;

/**
 * Created by geh on 4-4-14.
 */
public class Serializer
{
    private Gson gson;

    public Serializer()
    {
        this.gson = new Gson();
    }

    public <T> String serialize(T obj)
    {
        String result = null;
        try
        {
            result = this.gson.toJson(obj);
        }
        catch(Exception ex)
        {
            ex.printStackTrace();
        }
        finally
        {
            return result;
        }
    }

    public <T> T deSerialize(String raw, Class<T> type)
    {
        T result = null;
        try
        {
            result = (T)gson.fromJson(raw, type);
        }
        catch(Exception ex)
        {
            ex.printStackTrace();
        }
        finally
        {
            return result;
        }
    }

}
