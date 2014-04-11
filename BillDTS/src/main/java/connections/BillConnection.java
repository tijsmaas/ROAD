package connections;

/**
 * Created by geh on 11-4-14.
 * This is the connection that should be used by the BillSystem. One can call remotecall.
 * This class is NOT THREADSAFE. If you want multithreading, create on BillConnection for EACH
 * thread.
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
