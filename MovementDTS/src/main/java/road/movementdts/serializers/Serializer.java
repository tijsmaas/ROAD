package road.movementdts.serializers;

import com.esotericsoftware.kryo.Kryo;
import com.esotericsoftware.kryo.io.Input;
import com.esotericsoftware.kryo.io.Output;
import com.google.gson.Gson;
import road.movementdtos.dtos.*;
import road.movemententities.entities.*;

import java.io.ByteArrayOutputStream;

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

        kryo.register(VehicleDto.class);
        kryo.register(VehicleMovementDto.class);
        kryo.register(VehicleOwnerDto.class);
        kryo.register(CityDto.class);
        kryo.register(MovementDto.class);
        kryo.register(LaneDto.class);
        kryo.register(MovementUserDto.class);
    }

    public <T> byte[] serializeBytes(T obj)
    {
        Output output = new Output(new ByteArrayOutputStream());
        this.kryo.writeClassAndObject(output, obj);
        output.close();

        return output.getBuffer();
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
