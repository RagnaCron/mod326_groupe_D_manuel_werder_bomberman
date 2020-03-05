package BombermenClient.Bombermen.ClientCommunication;

import BombermenClientServerInterfaces.AbstractSocketSender;
import BombermenClientServerInterfaces.Messaging.Message;

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
					Message message = queue.poll();
//					if (message.CODE == CommandCode.PLAYER_EXIT) {
//						String[] m = message.PARAMETERS;
//						m[1] = "SomePlayerName that has to be defined...";
//						m[2] = client.getInetAddress().toString();
//						message = new Message(CommandCode.PLAYER_EXIT, m);
//					}
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
