package service;

import org.zeromq.ZMQ;

/**
 * Created by geh on 21-3-14.
 */
public class Worker extends Thread
{
    private ZMQ.Context ctx;
    private String dealerAddress;

    public Worker(ZMQ.Context ctx, String dealerAddress)
    {
        this.ctx = ctx;
        this.dealerAddress = dealerAddress;
    }

    @Override
    public void run()
    {
        ZMQ.Socket socket = ctx.socket(ZMQ.REP);
        socket.connect(dealerAddress);
        while(true)
        {
            byte[] incoming = socket.recv();
            socket.send("genrgjk", ZMQ.NOBLOCK);
        }
    }
}
