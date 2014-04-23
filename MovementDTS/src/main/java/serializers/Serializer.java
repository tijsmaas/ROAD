package serializers;

import org.codehaus.jackson.map.ObjectMapper;
import org.codehaus.jackson.map.ObjectReader;
import org.codehaus.jackson.map.ObjectWriter;

/**
 * Created by geh on 4-4-14.
 */
public class Serializer
{
    private ObjectMapper mapper;

    public Serializer()
    {
        this.mapper = new ObjectMapper();
    }

    public <T> String serialize(T obj)
    {
        String result = null;
        try
        {
            result = mapper.writeValueAsString(obj);
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
            result = mapper.reader().readValue(raw);
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
