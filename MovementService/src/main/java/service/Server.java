package service;

import org.zeromq.ZMQ;

import java.util.ArrayList;

/**
 * Created by geh on 21-3-14.
 */
public class Server
{
    public static void main(String[] args)
    {
        String serverName = "billservice";
        String routerAddress = "tcp://" + serverName + ":32500";
        String dealerAddress = "inproc://responders";

        ZMQ.Context ctx = ZMQ.context(1);
        ZMQ.Socket routerSocket = ctx.socket(ZMQ.ROUTER);
        ZMQ.Socket dealerSocket = ctx.socket(ZMQ.DEALER);
        routerSocket.bind(routerAddress);
        dealerSocket.bind(dealerAddress);

        ArrayList<Worker> repliers = new ArrayList<Worker>();
        for(int i = 0; i < 10; i++)
        {
            Worker worker = new Worker(ctx, dealerAddress);
            repliers.add(worker);
            worker.start();
        }

    }
}
