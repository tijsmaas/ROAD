package translation;

import java.io.FileReader;
import java.io.IOException;
import java.io.Serializable;
import java.util.Locale;
import java.util.Properties;
import javax.enterprise.context.SessionScoped;
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
    
    public Translator(Locale locale)
    {
        changeLanguage(locale);
    }
    
    public void changeLanguage(Locale locale)
    {
        try
        {
            String core = "./src/main/java/translation/";
            String translation = core + "car.properties";
            if(locale.getISO3Language().equalsIgnoreCase("nl"))
            {
                translation = core + "car_nl.properties";
            }
            FileReader reader = new FileReader(translation);
            properties = new Properties();
            properties.load(reader);
        }
        catch (IOException e)
        {
            e.printStackTrace();
        }
    }
    
    public String getProperty(String propertyName)
    {
        return properties.getProperty(propertyName);
    }
}
