package BombermanServer;

import BombermanClientServerInterfaces.JSONDecode;
import BombermanClientServerInterfaces.Message;
import org.json.JSONArray;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.Socket;
import java.util.concurrent.ConcurrentLinkedQueue;

public class ServerSocketListener extends Thread implements JSONDecode {
	private Socket client;
	private ConcurrentLinkedQueue<Message> queue;

	public ServerSocketListener(Socket client, ConcurrentLinkedQueue<Message> queue) {
		this.client = client;
		this.queue = queue;
	}

	@SuppressWarnings("InfiniteLoopStatement")
	@Override
	public void run() {
		try (BufferedReader in =
				     new BufferedReader(
						     new InputStreamReader(client.getInputStream())))
		{
//			System.out.println("About to start " + Thread.currentThread().getName());
			Message message;
			String input;
			while (true) {
				if (in.ready()) {
					input = in.readLine();
//					System.out.format("%s: %s%n", Thread.currentThread().getName(), input);
					message = decode(new JSONArray(input));
					queue.add(message);
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
