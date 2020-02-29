package BombermanServer;

import BombermanClientServerInterfaces.JSONEncode;
import BombermanClientServerInterfaces.Message;

import java.io.PrintWriter;
import java.net.Socket;
import java.util.concurrent.ConcurrentLinkedQueue;

public class ServerSocketSender implements Runnable, JSONEncode {
	private Socket client;
	private ConcurrentLinkedQueue<Message> queue;

	public ServerSocketSender(Socket client, ConcurrentLinkedQueue<Message> queue) {
		this.client = client;
		this.queue = queue;
	}


	@Override
	public void run() {
		try (PrintWriter out =
				     new PrintWriter(
						     client.getOutputStream(), true))
		{
			out.println("Hello.");
			while (true) {
				if(!queue.isEmpty()) {
					Message message = queue.poll();
					System.out.println("Message Clients: "+ message.toString());
					out.println(encode(message));
				}
			}
		} catch (Exception exception) {
			//noinspection ThrowablePrintedToSystemOut
			System.err.println(exception);
		}
	}
}
