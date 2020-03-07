package BombermenClient.Bombermen.ClientCommunication;

import BombermenClientServerInterfaces.AbstractSocketSender;
import BombermenClientServerInterfaces.Messaging.CommandCode;
import BombermenClientServerInterfaces.Messaging.Message;

import java.net.Socket;
import java.util.concurrent.ConcurrentLinkedQueue;

public class ClientSocketSender extends AbstractSocketSender {

	public ClientSocketSender(Socket client, ConcurrentLinkedQueue<Message> queue) {
		super(client, queue);
	}

	@Override
	public void run() {
		try {
			boolean isRunning = true;
			while (isRunning) {
				if(!queue.isEmpty()) {
					Message message = queue.poll();
					if (message.getCode() == CommandCode.PLAYER_EXIT)
						isRunning = false;
					out.println(encode(message));
				}
				sleep(0, 1000);
			}
			client.close();
		} catch (Exception exception) {
			exception.printStackTrace();
		}
	}
}
