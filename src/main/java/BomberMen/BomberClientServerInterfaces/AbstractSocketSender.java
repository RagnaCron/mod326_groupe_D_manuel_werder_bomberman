package BomberMen.BomberClientServerInterfaces;

import BomberMen.BomberClientServerInterfaces.Messaging.JSONEncode;
import BomberMen.BomberClientServerInterfaces.Messaging.Message;

import java.io.IOException;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.concurrent.ConcurrentLinkedQueue;

public abstract class AbstractSocketSender extends Thread implements JSONEncode {
	protected Socket client;
	protected ConcurrentLinkedQueue<Message> outputQueue;
	protected PrintWriter out;

	public AbstractSocketSender(Socket client, ConcurrentLinkedQueue<Message> outputQueue) {
		this.client = client;
		this.outputQueue = outputQueue;
		try {
			out = new PrintWriter(client.getOutputStream(), true);
		} catch (IOException exception) {
			exception.printStackTrace();
		}
	}

	@SuppressWarnings("InfiniteLoopStatement")
	@Override
	public void run() {
		try {
			while (true) {
				if (!outputQueue.isEmpty()) {
					Message message = outputQueue.poll();
					out.println(encode(message));
				}
				sleep(0, 1000);
			}
		} catch (Exception exception) {
			exception.printStackTrace();
		}
	}
}
