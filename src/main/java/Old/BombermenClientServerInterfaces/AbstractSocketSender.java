package Old.BombermenClientServerInterfaces;

import Old.BombermenClientServerInterfaces.Messaging.JSONEncode;
import Old.BombermenClientServerInterfaces.Messaging.Message;

import java.io.IOException;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.concurrent.ConcurrentLinkedQueue;

public abstract class AbstractSocketSender extends Thread implements JSONEncode {
	protected Socket client;
	protected ConcurrentLinkedQueue<Message> queue;
	protected PrintWriter out;

	public AbstractSocketSender(Socket client, ConcurrentLinkedQueue<Message> queue) {
		this.client = client;
		this.queue = queue;
		try {
			out = new PrintWriter(client.getOutputStream(), true);
		} catch (IOException exception) {
			exception.printStackTrace();
		}
	}

	@SuppressWarnings("InfiniteLoopStatement")
	@Override
	public void run() {
		try {
			Message m = new Message(new String[]{"Hello, world! Welcome to the Bombermen Server."});
			out.println(encode(m));
			while (true) {
				if (!queue.isEmpty()) {
					Message message = queue.poll();
					out.println(encode(message));
					sleep(0, 10000);
				} else {
//					System.out.format("%s: %s%n", Thread.currentThread().getName(), "Sleeps for 1 milliseconds...");
					sleep(1);
				}
			}
		} catch (Exception exception) {
			exception.printStackTrace();
		}
	}
}
