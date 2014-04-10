package serializers;

import org.codehaus.jackson.map.ObjectReader;
import org.codehaus.jackson.map.ObjectWriter;

/**
 * Created by geh on 4-4-14.
 */
public class Serializer
{
    private ObjectReader reader;
    private ObjectWriter writer;

    public Serializer()
    {

    }

    public <T> String serialize(T obj)
    {
        String result = null;
        try
        {
            result = writer.writeValueAsString(obj);
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

    public <T> T deSerialize(String raw)
    {
        T result = null;
        try
        {
            result = reader.readValue(raw);
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
