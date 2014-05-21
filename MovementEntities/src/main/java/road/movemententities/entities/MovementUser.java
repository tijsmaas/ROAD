package road.movemententities.entities;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

/**
 * Created by geh on 20-5-14.
 */
@Entity(name="movementusers")
public class MovementUser
{
    @Id @GeneratedValue
    private int id;

    @Column(name = "username", length = 28, unique = true, nullable = false)
    private String username;

    @Column(name = "email", length = 512, nullable = false)
    private String email;

    @Column(name = "name")
    private String name;

    @Column(name = "street")
    private String street;

    @Column(name = "housenumber")
    private String houseNumber;

    @Column(name = "postalcode")
    private String postalCode;

    @Column(name = "city")
    private String city;

    @Column(name = "invoicemail")
    private boolean invoiceMail;

    public MovementUser()
    {
        this.invoiceMail = true;
    }

    public MovementUser(String username, String email)
    {
        this.username = username;
        this.email = email;
        this.invoiceMail = true;
    }

    public MovementUser(String username, String email, String name, String street, String houseNumber, String postalCode, String city)
    {
        this.username = username;
        this.email = email;
        this.name = name;
        this.street = street;
        this.houseNumber = houseNumber;
        this.postalCode = postalCode;
        this.city = city;
        this.invoiceMail = true;
    }

    public int getId()
    {
        return id;
    }

    public void setId(int id)
    {
        this.id = id;
    }

    public String getUsername()
    {
        return username;
    }

    public void setUsername(String username)
    {
        this.username = username;
    }

    public String getEmail()
    {
        return email;
    }

    public void setEmail(String email)
    {
        this.email = email;
    }

    public String getName()
    {
        return name;
    }

    public void setName(String name)
    {
        this.name = name;
    }

    public String getStreet()
    {
        return street;
    }

    public void setStreet(String street)
    {
        this.street = street;
    }

    public String getHouseNumber()
    {
        return houseNumber;
    }

    public void setHouseNumber(String houseNumber)
    {
        this.houseNumber = houseNumber;
    }

    public String getPostalCode()
    {
        return postalCode;
    }

    public void setPostalCode(String postalCode)
    {
        this.postalCode = postalCode;
    }

    public String getCity()
    {
        return city;
    }

    public void setCity(String city)
    {
        this.city = city;
    }

    public boolean isInvoiceMail()
    {
        return invoiceMail;
    }

    public void setInvoiceMail(boolean invoiceMail)
    {
        this.invoiceMail = invoiceMail;
    }
}
