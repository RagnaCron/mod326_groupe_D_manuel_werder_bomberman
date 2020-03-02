package BombermenServer;

import BombermenClientServerInterfaces.AbstractSocketSender;
import BombermenClientServerInterfaces.Messaging.Message;

import java.net.Socket;
import java.util.concurrent.ConcurrentLinkedQueue;

public class ServerSocketSender extends AbstractSocketSender {

	public ServerSocketSender(Socket client, ConcurrentLinkedQueue<Message> queue) {
		super(client, queue);
	}

	@Override
	public void run() {
		super.run();
	}
}
