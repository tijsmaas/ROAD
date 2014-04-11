package connections;

/**
 * Created by geh on 11-4-14.
 */
public class BillConnection extends ClientConnection implements IBillQuery
{
    public BillConnection(String serverAddress, String factoryName, String sendTo)
    {
        super(serverAddress, factoryName, sendTo);
    }

    @Override
    public String authenticate(String userId, String password)
    {
        return this.remoteCall("authenticate", userId, password);
    }
}
