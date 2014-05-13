package road.carsystem.api;

import javax.ejb.Stateless;
import javax.inject.Inject;
import javax.websocket.*;
import javax.websocket.server.ServerEndpoint;
import java.io.IOException;
import java.util.LinkedList;
import java.util.concurrent.ConcurrentHashMap;

/**
 * Created by geh on 7-4-14.
 */
@Stateless
@ServerEndpoint("/socket")
public class CarSocket
{
    private static final String apiKey = "11ff";

    @Inject
    private CarSimulator simulator;
    private ConcurrentHashMap<String, LinkedList<Session>> sessions = new ConcurrentHashMap();

    @OnOpen
    public void open(Session session, EndpointConfig conf)
    {
        this.addSession(CarSocket.apiKey, session);
    }

    @OnError
    public void error(Session session, Throwable error)
    {
        try
        {
            this.removeSession(CarSocket.apiKey, session);
        }
        catch(Exception ex)
        {
            ex.printStackTrace();
        }

    }

    @OnMessage
    public void onMessage(Session session, String msg)
    {

    }

    @OnClose
    public void close(Session session, CloseReason reason)
    {
        try
        {
            this.removeSession(CarSocket.apiKey, session);
        }
        catch(Exception ex)
        {
            ex.printStackTrace();
        }
    }

    public void addSession(String name, Session session)
    {
        LinkedList<Session> sessions;
        if(this.sessions.containsKey(name))
        {
            sessions = this.sessions.get(name);
        }
        else
        {
            sessions = new LinkedList<>();
            this.sessions.put(name, sessions);
        }

        sessions.add(session);
    }

    public void removeSession(String name, Session session)
    {
        try
        {
            if(this.sessions.containsKey(name))
            {
                LinkedList<Session> sessions = this.sessions.get(name);
                sessions.remove(session);
                session.close();
                if(sessions.size() == 0)
                {
                    this.sessions.remove(name);
                }
            }
        }
        catch(IOException ex)
        {
            ex.printStackTrace();
        }

    }
}
