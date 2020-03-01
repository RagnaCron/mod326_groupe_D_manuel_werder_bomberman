package BombermanServer;

import BombermanClientServerInterfaces.JSONEncode;
import BombermanClientServerInterfaces.Message;

import java.io.PrintWriter;
import java.net.Socket;
import java.util.concurrent.ConcurrentLinkedQueue;

public class ServerSocketSender extends Thread implements JSONEncode {
	private Socket client;
	private ConcurrentLinkedQueue<Message> queue;

	public ServerSocketSender(Socket client, ConcurrentLinkedQueue<Message> queue) {
		this.client = client;
		this.queue = queue;
	}

	@SuppressWarnings("InfiniteLoopStatement")
	@Override
	public void run() {
		try (PrintWriter out =
				     new PrintWriter(
						     client.getOutputStream(), true))
		{
			out.println("Hello, world! Welcome to the Bomberman Server.");
			while (true) {
				if (!queue.isEmpty()) {
					Message message = queue.poll();
					out.println(encode(message).toString());
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
