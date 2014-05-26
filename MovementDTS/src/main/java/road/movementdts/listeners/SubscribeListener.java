package road.movementdts.listeners;

import road.movementdts.helpers.Pair;

/**
 * Created by geh on 10-4-14.
 * This interface is for Topic clients that receive a message.
 */
public interface SubscribeListener
{
    public void receive(byte[] message);
}
