package BomberMen.BomberServer;

import BomberMen.BomberClientServerInterfaces.AbstractSocketListener;
import BomberMen.BomberClientServerInterfaces.Messaging.Message;

import java.net.Socket;
import java.util.concurrent.ConcurrentLinkedQueue;

public class BomberServerSocketListener extends AbstractSocketListener {
	public BomberServerSocketListener(Socket client, ConcurrentLinkedQueue<Message> queue) {
		super(client, queue);
	}
}
