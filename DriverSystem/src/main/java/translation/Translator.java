package translation;

import java.io.FileReader;
import java.io.IOException;
import java.io.Serializable;
import java.util.LinkedHashMap;
import java.util.Locale;
import java.util.Map;
import java.util.Properties;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.enterprise.context.SessionScoped;
import javax.faces.bean.ManagedBean;
import javax.faces.context.FacesContext;
import javax.faces.event.ValueChangeEvent;
import javax.inject.Named;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author Mitch
 */
@Named
@SessionScoped
public class Translator implements Serializable
{
    private Properties properties;
    
    private Locale locale = FacesContext.getCurrentInstance().getViewRoot().getLocale();

    public Locale getLocale()
    {
        return locale;
    }

    public String getLanguage()
    {
        return locale.getLanguage();
    }

    public void setLanguage(String language)
    {
        this.locale = new Locale(language);
        try
        {
            String translation = "car.properties";
            if(language.equalsIgnoreCase("nl"))
            {
                translation = "car_nl.properties";
            }
            properties = new Properties();
            properties.load(Thread.currentThread().getContextClassLoader().getResourceAsStream(translation));
        }
        catch (Exception e)
        {
            e.printStackTrace();
        }
    }         
    
    public String getProperty(String propertyName)
    {
        if(properties == null) {
            try {
                properties = new Properties();
                properties.load(Thread.currentThread().getContextClassLoader().getResourceAsStream("car.properties"));
            } catch (IOException ex) {
                Logger.getLogger(Translator.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        return properties.getProperty(propertyName);
    }
}
