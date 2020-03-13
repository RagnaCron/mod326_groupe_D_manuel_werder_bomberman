package Old.BombermenServer;

import Old.BombermenClientServerInterfaces.AbstractSocketListener;
import Old.BombermenClientServerInterfaces.Messaging.CommandCode;
import Old.BombermenClientServerInterfaces.Messaging.CustomJSONArray;
import Old.BombermenClientServerInterfaces.Messaging.Message;

import java.net.Socket;
import java.util.concurrent.ConcurrentLinkedQueue;

public class ServerSocketListener extends AbstractSocketListener {

	public ServerSocketListener(Socket client, ConcurrentLinkedQueue<Message> queue) {
		super(client, queue);
	}

	@Override
	public void run() {
		try {
			Message message;
			String input;
			while (true) {
				if (in.ready()) {
					input = in.readLine();
					System.out.format("%s: %s%n", Thread.currentThread().getName(), input);
					message = decode(new CustomJSONArray(input));
					queue.add(message);
					if (message.getCode() == CommandCode.PLAYER_EXIT || message.getCode() == CommandCode.SERVER_FULL)
						break;
				}
//					System.out.format("%s: %s%n", Thread.currentThread().getName(), "Sleeps for 1 milliseconds...");
				sleep(0, 1000);
			}
			client.close();
			join();
		} catch (Exception exception) {
			exception.printStackTrace();
		}
		System.out.format("%s: %s%n", Thread.currentThread().getName(), "Closing this Thread.");
	}
}
