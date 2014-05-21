package road.policesystem.utils;

import javax.faces.context.FacesContext;
import javax.servlet.http.HttpServletRequest;

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

    public static String getHostnameAndContext(){
        HttpServletRequest origRequest = (HttpServletRequest)FacesContext.getCurrentInstance().getExternalContext().getRequest();

        return "http://" + origRequest.getServerName() + ":"+ origRequest.getServerPort() + getContextRoot();

    }
}
