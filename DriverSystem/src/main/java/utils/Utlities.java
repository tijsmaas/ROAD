package utils;

import javax.faces.context.FacesContext;

/**
 * Created by Niek on 13/05/14.
 * © Aidas 2014
 */
public class Utlities
{
    public static String getContextRoot()
    {
        return  FacesContext.getCurrentInstance().getExternalContext().getRequestContextPath();
    }
}
