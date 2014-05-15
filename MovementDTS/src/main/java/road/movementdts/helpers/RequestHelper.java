package road.movementdts.helpers;

import java.lang.reflect.Method;
import java.util.List;

/**
 * Created by geh on 11-4-14.
 */
public class RequestHelper
{
    public static String getUniqueName(String methodName, Object[] parameters)
    {
        String uniqueName = methodName;

        for(Object par : parameters)
        {
            uniqueName += par.getClass().getCanonicalName();
        }

        return uniqueName;
    }

    public static String getUniqueName(String methodName, List<Class> parameters)
    {
        String uniqueName = methodName;

        for(Class par : parameters)
        {
            uniqueName += par.getCanonicalName();
        }

        return uniqueName;
    }
}
