package BombermanServer;

import BombermanClientServerInterfaces.JSONEncode;
import BombermanClientServerInterfaces.Message;
import org.json.JSONArray;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.List;
import java.util.concurrent.ConcurrentLinkedQueue;

public class BombermanServer extends Thread implements JSONEncode {

	private static final int INPUT_PORT = 8764;
	private static final int OUTPUT_PORT = 8768;

	private ConcurrentLinkedQueue<Message> inputQueue;
	private ConcurrentLinkedQueue<Message> outputQueue;

//	private  inputServer;
//	private ServerSocket outputServer;

	public BombermanServer() {
		inputQueue = new ConcurrentLinkedQueue<>();
		outputQueue = new ConcurrentLinkedQueue<>();
	}

	@Override
	public void run() {
		try (ServerSocket inputServer = new ServerSocket(INPUT_PORT);
		     ServerSocket outputServer = new ServerSocket(OUTPUT_PORT))
		{
			System.out.println("BombermanServer auf " + INPUT_PORT + " gestartet ...");
			Socket inputClient = inputServer.accept();
			Socket outputClient = outputServer.accept();

			(new Thread(new ServerSocketListener(inputClient, inputQueue))).start();
			(new Thread(new ServerSocketSender(outputClient, outputQueue))).start();

			JSONArray array = JSONArray()

			while (true) {
				if (!inputQueue.isEmpty())
					queryMessage(inputQueue.poll());
//				System.out.println(server.getInetAddress().toString());
			}
		} catch (IOException e) {
			//noinspection ThrowablePrintedToSystemOut
			System.err.println(e);
		}
	}

	private void queryMessage(Message message) {
		switch (message.CODE) {
			case DROP_BOMB:
				new Thread(() -> {
					List<String> list = message.PARAMETERS;
					list.set(0, "bomb_explode");
					try {
						wait(1991);
						outputQueue.add(new Message(list));
					} catch (InterruptedException e) {
						e.printStackTrace();
					}
				}).start();
			default:
				outputQueue.add(message);
		}
	}
}
