package BombermanClient.Bomberman;

import BombermanClientServerInterfaces.AbstractSocketSender;
import BombermanClientServerInterfaces.Messaging.Message;

import java.net.Socket;
import java.util.concurrent.ConcurrentLinkedQueue;

public class ClientSocketSender extends AbstractSocketSender {

	public ClientSocketSender(Socket client, ConcurrentLinkedQueue<Message> queue) {
		super(client, queue);
	}

	@SuppressWarnings("InfiniteLoopStatement")
	@Override
	public void run() {
		try {
			while (true) {
				if(!queue.isEmpty()) {
					Message m = queue.poll();
					out.println(encode(m));
					sleep(0, 10000);
				} else {
					System.out.format("%s: %s%n", Thread.currentThread().getName(), "Sleeps for 1 milliseconds...");
					sleep(1);
				}
			}
		} catch (Exception exception) {
			exception.printStackTrace();
		}
	}
}
