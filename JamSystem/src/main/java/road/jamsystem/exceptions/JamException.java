package road.jamsystem.exceptions;

/**
 * Created by Geert on 24/05/2014.
 */
public class JamException extends Exception
{
    /**
     * Create a new instance of the {@link JamException} class.
     * @param message the message explaining what went wrong.
     */
    public JamException(String message)
    {
        super(message);
    }
}
