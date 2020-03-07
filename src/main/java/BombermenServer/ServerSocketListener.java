package BombermenServer;

import BombermenClientServerInterfaces.AbstractSocketListener;
import BombermenClientServerInterfaces.Messaging.CommandCode;
import BombermenClientServerInterfaces.Messaging.CustomJSONArray;
import BombermenClientServerInterfaces.Messaging.Message;

import java.net.Socket;
import java.util.concurrent.ConcurrentLinkedQueue;

public class ServerSocketListener extends AbstractSocketListener {

	public ServerSocketListener(Socket client, ConcurrentLinkedQueue<Message> queue) {
		super(client, queue);
	}

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
					if (message.getCode() == CommandCode.PLAYER_EXIT)
						break;
				}
//					System.out.format("%s: %s%n", Thread.currentThread().getName(), "Sleeps for 1 milliseconds...");
				sleep(0, 1000);
			}
			client.close();
		} catch (Exception exception) {
			exception.printStackTrace();
		}
		System.out.format("%s: %s%n", Thread.currentThread().getName(), "Closing this Thread.");
	}
}
