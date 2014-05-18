package road.movementdts.serializers;

import com.esotericsoftware.kryo.Kryo;
import com.esotericsoftware.kryo.io.Input;
import com.esotericsoftware.kryo.io.Output;
import com.google.gson.Gson;
import com.sun.xml.internal.messaging.saaj.util.ByteOutputStream;

/**
 * Created by geh on 4-4-14.
 */
public class Serializer
{
    private Gson gson;
    private Kryo kryo;


    public Serializer()
    {
        this.gson = new Gson();
        this.kryo = new Kryo();
    }

    public <T> byte[] serializeBytes(T obj)
    {
        Output output = new Output(new ByteOutputStream());
        this.kryo.writeObject(output, obj);
        output.close();

        return output.getBuffer();
    }

    public <T> T deSerialize(byte[] raw, Class<T> type)
    {
        T object = kryo.readObjectOrNull(new Input(raw), type);
        return object;
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
