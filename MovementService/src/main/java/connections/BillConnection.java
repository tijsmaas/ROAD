package connections;

/**
 * Created by geh on 11-4-14.
 */
public class BillConnection extends ServerConnection implements IBillQuery
{
    public BillConnection(String factoryName, String listenTo)
    {
        super(factoryName, listenTo);
    }

    @Override
    public String authenticate(String userId, String password)
    {
        return "authenticated";
    }
}
