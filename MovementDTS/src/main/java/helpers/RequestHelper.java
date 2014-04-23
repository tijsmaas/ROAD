package helpers;

import java.lang.reflect.Method;

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
}
