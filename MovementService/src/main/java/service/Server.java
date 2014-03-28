package service;

import org.zeromq.*;
import org.zeromq.ZMQ.Context;
import org.zeromq.ZMQ.Socket;
import org.zeromq.ZMQQueue;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by geh on 21-3-14.
 */
public class Server
{
    private ZMQQueue queueDevice;
    private List<Worker> workers;

    public Server()
    {
        String serverName = "billservice";
        String routerAddress = "tcp://" + serverName + ":32500";
        String dealerAddress = "inproc://responders";

        Context ctx = ZMQ.context(1);
        Socket routerSocket = ctx.socket(ZMQ.ROUTER);
        Socket dealerSocket = ctx.socket(ZMQ.DEALER);

        this.queueDevice = new ZMQQueue(ctx, routerSocket, dealerSocket);
        this.workers = new ArrayList<Worker>();

        for(int i = 0; i < 10; i++)
        {
            Worker worker = new Worker(ctx, dealerAddress);
            this.workers.add(worker);
        }
    }

    public void start()
    {
        this.queueDevice.run();
        for(Worker worker : this.workers)
        {
            worker.start();
        }
    }

    public void stop()
    {
        try
        {
            this.queueDevice.close();
            for(Worker worker : this.workers)
            {
                worker.stop();
            }
        }
        catch(Exception ex)
        {
            ex.printStackTrace();
        }

    }
}
