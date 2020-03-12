package BomberMen.BomberGame.ServerConnection;

import BomberMen.BomberClientServerInterfaces.AbstractSocketSender;
import BomberMen.BomberClientServerInterfaces.Messaging.CommandCode;
import BomberMen.BomberClientServerInterfaces.Messaging.Message;

import java.net.Socket;
import java.util.concurrent.ConcurrentLinkedQueue;

public final class BomberSocketSender extends AbstractSocketSender {
	public BomberSocketSender(Socket client, ConcurrentLinkedQueue<Message> outputQueue) {
		super(client, outputQueue);
	}

	@Override
	public void run() {
		try {
			while (true) {
				if (!outputQueue.isEmpty()) {
					Message message = outputQueue.poll();
					out.println(encode(message));
					if (message.getCode() == CommandCode.PLAYER_EXIT)
						break;
				}
				sleep(0, 1000);
			}
			client.close();
		} catch (Exception ignored) {}
	}
}
