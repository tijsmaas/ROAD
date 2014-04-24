package connections;

import aidas.usersystem.dto.UserDto;

import javax.inject.Named;

/**
 * Created by geh on 11-4-14.
 * This is the connection that should be used by the BillSystem. One can call remotecall.
 * This class is NOT THREADSAFE. If you want multithreading, create on BillConnection for EACH
 * thread.
 */
public class BillClientConnection extends ClientConnection implements IBillQuery
{
    public BillClientConnection()
    {
        super("localhost:1099", MovementConnection.FactoryName, MovementConnection.BillSystemQueue);
    }

    @Override
    public UserDto authenticate(String user, String password)
    {
        return this.remoteCall("authenticate", UserDto.class, user, password);
    }
}
