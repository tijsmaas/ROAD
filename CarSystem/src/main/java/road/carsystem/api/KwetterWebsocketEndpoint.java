package road.carsystem.api;

import javax.ejb.Stateless;
import javax.websocket.*;
import javax.websocket.server.ServerEndpoint;
import java.io.IOException;
import java.util.concurrent.ConcurrentHashMap;

/**
 * Created by geh on 7-4-14.
 */
@Stateless
@ServerEndpoint("/socket")
public class KwetterWebsocketEndpoint
{
    ConcurrentHashMap<String, Session> sessions = new ConcurrentHashMap<>();

    @OnOpen
    public void open(Session session, EndpointConfig conf)
    {
        String name = session.getUserPrincipal().getName();
        if(!sessions.containsKey(name))
        {
            sessions.put(name, session);
        }
    }

    @OnError
    public void error(Session session, Throwable error)
    {
        try
        {
            session.close();
        }
        catch(Exception ex)
        {
            ex.printStackTrace();
        }

    }

    @OnMessage
    public void onMessage(Session session, String msg)
    {
        try
        {
            session.getBasicRemote().sendText(msg);
        }
        catch (IOException e)
        {
            e.printStackTrace();
        }
    }

    @OnClose
    public void close(Session session, CloseReason reason)
    {
        this.sessions.remove(session.getId());
    }
}
