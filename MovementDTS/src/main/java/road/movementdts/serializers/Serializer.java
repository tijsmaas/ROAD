package road.movementdts.serializers;

import com.esotericsoftware.kryo.Kryo;
import com.esotericsoftware.kryo.io.Input;
import com.esotericsoftware.kryo.io.Output;
import com.google.gson.Gson;
import road.movementdtos.dtos.*;

import java.io.ByteArrayOutputStream;

/**
 * Created by geh on 4-4-14.
 * This is serializer that makes it as easy as possible to use.
 * The methods that use and return byte arrays use Kryo serialization,
 * and the ones that use Strings use Gson serialization.
 * For RPC Kryo is highly preferred.
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
        ByteArrayOutputStream stream = new ByteArrayOutputStream();
        Output output = new Output(stream);
        this.kryo.writeClassAndObject(output, obj);
        output.close();

        return stream.toByteArray();
    }

    public <T> T deSerialize(byte[] raw, Class<T> type)
    {
        T object = (T)kryo.readClassAndObject(new Input(raw));
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
