package road.cardts.connections;

import java.util.Calendar;

/**
 * Created by geh on 6-5-14.
 */
public interface ICarQuery
{
    String addMovement(String apiKey, long sequence, String xml);
}
