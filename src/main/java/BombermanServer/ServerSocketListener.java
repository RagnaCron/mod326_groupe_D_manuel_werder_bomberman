package BombermanServer;

import BombermanClientServerInterfaces.JSONDecode;
import BombermanClientServerInterfaces.Message;
import org.json.JSONArray;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.Socket;
import java.util.concurrent.ConcurrentLinkedQueue;

public class ServerSocketListener implements Runnable, JSONDecode {
	private Socket client;
	private ConcurrentLinkedQueue<Message> queue;

	public ServerSocketListener(Socket client, ConcurrentLinkedQueue<Message> queue) {
		this.client = client;
		this.queue = queue;
	}

	@Override
	public void run() {
		try (BufferedReader in =
				     new BufferedReader(
						     new InputStreamReader(client.getInputStream())))
		{
			Message message;
			String input;
			while ((input = in.readLine()) != null) {
				System.out.println("Message vom Client: "+ input);
				message = decode(new JSONArray(input));
				queue.add(message);
			}
		} catch (Exception exception) {
			//noinspection ThrowablePrintedToSystemOut
			System.err.println(exception);
		}
	}
}
