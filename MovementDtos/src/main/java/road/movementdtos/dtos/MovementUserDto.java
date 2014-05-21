package road.movementdtos.dtos;

/**
 * Created by geh on 20-5-14.
 */
public class MovementUserDto
{
    private int id;
    private String userName;

    private String email;
    private String name;
    private String street;
    private String houseNumber;
    private String postalCode;
    private String city;

    // Flag region
    private boolean invoiceMail;

    public MovementUserDto()
    {

    }

    public MovementUserDto(int id, String userName, String email, String name, String street, String houseNumber, String postalCode, String city, boolean invoiceMail)
    {
        this.id = id;
        this.userName = userName;
        this.email = email;
        this.name = name;
        this.street = street;
        this.houseNumber = houseNumber;
        this.postalCode = postalCode;
        this.city = city;
        this.invoiceMail = invoiceMail;
    }

    public int id()
    {
        return id;
    }

    public MovementUserDto id(int movementUserId)
    {
        this.id = movementUserId;
        return this;
    }

    public String userName()
    {
        return userName;
    }

    public MovementUserDto userName(String username)
    {
        this.userName = username;
        return this;
    }

    public String email()
    {
        return email;
    }

    public MovementUserDto email(String email)
    {
        this.email = email;
        return this;
    }

    public String name()
    {
        return name;
    }

    public MovementUserDto name(String name)
    {
        this.name = name;
        return this;
    }

    public String street()
    {
        return street;
    }

    public MovementUserDto street(String street)
    {
        this.street = street;
        return this;
    }

    public String houseNumber()
    {
        return houseNumber;
    }

    public MovementUserDto houseNumber(String houseNumber)
    {
        this.houseNumber = houseNumber;
        return this;
    }

    public String postalCode()
    {
        return postalCode;
    }

    public MovementUserDto postalCode(String postalCode)
    {
        this.postalCode = postalCode;
        return this;
    }

    public String city()
    {
        return city;
    }

    public MovementUserDto city(String city)
    {
        this.city = city;
        return this;
    }

    public boolean invoiceMail()
    {
        return invoiceMail;
    }

    public MovementUserDto invoiceMail(boolean invoiceMail)
    {
        this.invoiceMail = invoiceMail;
        return this;
    }
}
