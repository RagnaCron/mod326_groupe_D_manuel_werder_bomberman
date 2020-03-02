package BombermenServer;

import BombermenClientServerInterfaces.AbstractSocketListener;
import BombermenClientServerInterfaces.Messaging.CustomJSONArray;
import BombermenClientServerInterfaces.Messaging.Message;

import java.net.Socket;
import java.util.concurrent.ConcurrentLinkedQueue;

public class ServerSocketListener extends AbstractSocketListener {

	public ServerSocketListener(Socket client, ConcurrentLinkedQueue<Message> queue) {
		super(client, queue);
	}

	@SuppressWarnings("InfiniteLoopStatement")
	@Override
	public void run() {
		try {
//			System.out.println("About to start " + Thread.currentThread().getName());
			Message message;
			String input;
			while (true) {
				if (in.ready()) {
					input = in.readLine();
					System.out.format("%s: %s%n", Thread.currentThread().getName(), input);
					message = decode(new CustomJSONArray(input));
					queue.add(message);
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
