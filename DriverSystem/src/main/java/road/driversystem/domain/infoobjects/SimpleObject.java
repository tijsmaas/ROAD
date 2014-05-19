package road.driversystem.domain.infoobjects;

/**
 * Created by Niek on 19/05/14.
 * © Aidas 2014
 */
public class SimpleObject
{
    private String name;
    private int year;
    private String city;
    private int month;

    public SimpleObject()
    {
        this.name = "test";
        this.year  = 1;
        this.city = "eindhoven";
        this.month = 12;
    }

    public String getName()
    {
        return name;
    }

    public void setName(String name)
    {
        this.name = name;
    }

    public int getYear()
    {
        return year;
    }

    public void setYear(int year)
    {
        this.year = year;
    }

    public String getCity()
    {
        return city;
    }

    public void setCity(String city)
    {
        this.city = city;
    }

    public int getMonth()
    {
        return month;
    }

    public void setMonth(int month)
    {
        this.month = month;
    }
}
