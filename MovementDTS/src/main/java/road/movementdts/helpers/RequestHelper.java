package road.movementdts.helpers;

import java.lang.reflect.Method;
import java.util.List;

/**
 * Created by geh on 11-4-14.
 * This is an important class for Remote Procedure calls - it will generate a unique identifier that belongs
 * to a specific method. Both methods should always return exactly the same name, given the 'same' parameters.
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
