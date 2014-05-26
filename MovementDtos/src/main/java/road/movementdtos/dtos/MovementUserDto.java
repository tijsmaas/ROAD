package road.movementdtos.dtos;

/**
 * Created by geh on 20-5-14.
 */
public class MovementUserDto
{

    /**
     * The identifier of the movement user.
     */
    private int id;

    /**
     * The username of the movement user.
     */
    private String userName;

    /**
     * The email address of the movement user.
     */
    private String email;

    /**
     * The name of the movement user.
     */
    private String name;

    /**
     * The street on which the movement user lives.
     */
    private String street;

    /**
     * The house number of the movement user's address.
     */
    private String houseNumber;

    /**
     * The postal code of the movement user's address.
     */
    private String postalCode;

    /**
     * The city where the movement user lives.
     */
    private String city;

    /**
     * The flag which specifies a user will receive an email when an invoice has been generated.
     */
    private boolean invoiceMail;

    /**
     * Creates a new instance of the {@link MovementUserDto} class.
     */
    public MovementUserDto()
    {

    }

    /**
     * Creates a new instance of the {@link MovementUserDto} class.
     * @param id The identifier of the movement user.
     * @param userName The username of the movement user.
     * @param email The email address of the movement user.
     * @param name The name of the movement user.
     * @param street The street on which the movement user lives.
     * @param houseNumber The house number of the movement user's address.
     * @param postalCode The postal code of the movement user's address.
     * @param city The city where the movement user lives.
     * @param invoiceMail The flag which specifies a user will receive an email when an invoice has been generated.
     */
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

    /**
     * Get the {@link #id} of the {@link MovementUserDto}.
     * @return The identifier of the movement user.
     */
    public int id()
    {
        return id;
    }

    /**
     * Set the {@link #id} of the {@link MovementUserDto}.
     * @param movementUserId The identifier of the movement user.
     * @return this {@link MovementUserDto}.
     */
    public MovementUserDto id(int movementUserId)
    {
        this.id = movementUserId;
        return this;
    }

    /**
     * Get the {@link #userName} of the {@link MovementUserDto}.
     * @return The username of the movement user.
     */
    public String userName()
    {
        return userName;
    }

    /**
     * Set the {@link #userName} of the {@link MovementUserDto}.
     * @param username The username of the movement user.
     * @return this {@link MovementUserDto}.
     */
    public MovementUserDto userName(String username)
    {
        this.userName = username;
        return this;
    }

    /**
     * Get the {@link #email} of the {@link MovementUserDto}.
     * @return The email address of the movement user.
     */
    public String email()
    {
        return email;
    }

    /**
     * Set the {@link #email} of the {@link MovementUserDto}.
     * @param email The email address of the movement user.
     * @return this {@link MovementUserDto}.
     */
    public MovementUserDto email(String email)
    {
        this.email = email;
        return this;
    }

    /**
     * Get the {@link #name} of the {@link MovementUserDto}.
     * @return The name of the movement user.
     */
    public String name()
    {
        return name;
    }

    /**
     * Set the {@link #name} of the {@link MovementUserDto}.
     * @param name The name of the movement user.
     * @return this {@link MovementUserDto}.
     */
    public MovementUserDto name(String name)
    {
        this.name = name;
        return this;
    }

    /**
     * Get the {@link #street} of the {@link MovementUserDto}.
     * @return The street on which the movement user lives.
     */
    public String street()
    {
        return street;
    }

    /**
     * Set the {@link #street} of the {@link MovementUserDto}.
     * @param street The street on which the movement user lives.
     * @return this {@link MovementUserDto}.
     */
    public MovementUserDto street(String street)
    {
        this.street = street;
        return this;
    }

    /**
     * Get the {@link #houseNumber} of the {@link MovementUserDto}.
     * @return The house number of the movement user's address.
     */
    public String houseNumber()
    {
        return houseNumber;
    }

    /**
     * Set the {@link #houseNumber} of the {@link MovementUserDto}.
     * @param houseNumber The house number of the movement user's address.
     * @return this {@link MovementUserDto}.
     */
    public MovementUserDto houseNumber(String houseNumber)
    {
        this.houseNumber = houseNumber;
        return this;
    }

    /**
     * Get the {@link #postalCode} of the {@link MovementUserDto}.
     * @return The postal code of the movement user's address.
     */
    public String postalCode()
    {
        return postalCode;
    }

    /**
     * Set the {@link #postalCode} of the {@link MovementUserDto}.
     * @param postalCode The postal code of the movement user's address.
     * @return this {@link MovementUserDto}.
     */
    public MovementUserDto postalCode(String postalCode)
    {
        this.postalCode = postalCode;
        return this;
    }

    /**
     * Get the {@link #city} of the {@link MovementUserDto}.
     * @return The city where the movement user lives.
     */
    public String city()
    {
        return city;
    }

    /**
     * Set the {@link #city} of the {@link MovementUserDto}.
     * @param city The city where the movement user lives.
     * @return this {@link MovementUserDto}.
     */
    public MovementUserDto city(String city)
    {
        this.city = city;
        return this;
    }

    /**
     * Get the {@link #invoiceMail} of the {@link MovementUserDto}.
     * @return The flag which specifies a user will receive an email when an invoice has been generated.
     */
    public boolean invoiceMail()
    {
        return invoiceMail;
    }

    /**
     * Set the {@link #invoiceMail} of the {@link MovementUserDto}.
     * @param invoiceMail The flag which specifies a user will receive an email when an invoice has been generated.
     * @return this {@link MovementUserDto}.
     */
    public MovementUserDto invoiceMail(boolean invoiceMail)
    {
        this.invoiceMail = invoiceMail;
        return this;
    }

    /**
     * Get the {@link #id} of the {@link MovementUserDto}.
     * @return The identifier of the movement user.
     */
    public int getId()
    {
        return id;
    }

    /**
     * Get the {@link #userName} of the {@link MovementUserDto}.
     * @return The username of the movement user.
     */
    public String getUserName()
    {
        return userName;
    }

    /**
     * Get the {@link #email} of the {@link MovementUserDto}.
     * @return The email address of the movement user.
     */
    public String getEmail()
    {
        return email;
    }

    /**
     * Get the {@link #name} of the {@link MovementUserDto}.
     * @return The name of the movement user.
     */
    public String getName()
    {
        return name;
    }

    /**
     * Get the {@link #street} of the {@link MovementUserDto}.
     * @return The street on which the movement user lives.
     */
    public String getStreet()
    {
        return street;
    }

    /**
     * Get the {@link #houseNumber} of the {@link MovementUserDto}.
     * @return The house number of the movement user's address.
     */
    public String getHouseNumber()
    {
        return houseNumber;
    }

    /**
     * Get the {@link #postalCode} of the {@link MovementUserDto}.
     * @return The postal code of the movement user's address.
     */
    public String getPostalCode()
    {
        return postalCode;
    }

    /**
     * Get the {@link #city} of the {@link MovementUserDto}.
     * @return The city where the movement user lives.
     */
    public String getCity()
    {
        return city;
    }

    /**
     * Get the {@link #invoiceMail} of the {@link MovementUserDto}.
     * @return The flag which specifies a user will receive an email when an invoice has been generated.
     */
    public boolean isInvoiceMail()
    {
        return invoiceMail;
    }

    /**
     * Set the {@link #id} of the {@link MovementUserDto}.
     * @param id The identifier of the movement user.
     */
    public void setId(int id)
    {
        this.id = id;
    }

    /**
     * Set the {@link #userName} of the {@link MovementUserDto}.
     * @param userName The username of the movement user.
     */
    public void setUserName(String userName)
    {
        this.userName = userName;
    }

    /**
     * Set the {@link #email} of the {@link MovementUserDto}.
     * @param email The email address of the movement user.
     */
    public void setEmail(String email)
    {
        this.email = email;
    }

    /**
     * Set the {@link #name} of the {@link MovementUserDto}.
     * @param name The name of the movement user.
     */
    public void setName(String name)
    {
        this.name = name;
    }

    /**
     * Set the {@link #street} of the {@link MovementUserDto}.
     * @param street The street on which the movement user lives.
     */
    public void setStreet(String street)
    {
        this.street = street;
    }

    /**
     * Set the {@link #houseNumber} of the {@link MovementUserDto}.
     * @param houseNumber The house number of the movement user's address.
     */
    public void setHouseNumber(String houseNumber)
    {
        this.houseNumber = houseNumber;
    }

    /**
     * Set the {@link #postalCode} of the {@link MovementUserDto}.
     * @param postalCode The postal code of the movement user's address.
     */
    public void setPostalCode(String postalCode)
    {
        this.postalCode = postalCode;
    }

    /**
     * Set the {@link #city} of the {@link MovementUserDto}.
     * @param city The city where the movement user lives.
     */
    public void setCity(String city)
    {
        this.city = city;
    }

    /**
     * Set the {@link #invoiceMail} of the {@link MovementUserDto}.
     * @param invoiceMail The flag which specifies a user will receive an email when an invoice has been generated.
     */
    public void setInvoiceMail(boolean invoiceMail)
    {
        this.invoiceMail = invoiceMail;
    }
}
