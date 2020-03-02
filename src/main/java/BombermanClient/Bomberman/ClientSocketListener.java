package BombermanClient.Bomberman;

import BombermanClientServerInterfaces.AbstractSocketListener;
import BombermanClientServerInterfaces.Messaging.CustomJSONArray;
import BombermanClientServerInterfaces.Messaging.Message;

import java.net.Socket;
import java.util.concurrent.ConcurrentLinkedQueue;

public class ClientSocketListener extends AbstractSocketListener {

	public ClientSocketListener(Socket client, ConcurrentLinkedQueue<Message> queue) {
		super(client, queue);
	}

	@SuppressWarnings("InfiniteLoopStatement")
	@Override
	public void run() {
		try {
			Message serverMessage;
			while (true) {
				if (in.ready()) {
					String input = in.readLine();
//					System.err.println(input);
//					System.out.format("%s: %s%n", Thread.currentThread().getName(), input);
					serverMessage = decode(new CustomJSONArray(input));
					queue.add(serverMessage);
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
