package BombermenServer;

import BombermenClientServerInterfaces.Messaging.JSONEncode;
import BombermenClientServerInterfaces.Messaging.Message;
import org.jetbrains.annotations.NotNull;

import java.net.ServerSocket;
import java.net.Socket;
import java.util.HashMap;
import java.util.concurrent.ConcurrentLinkedQueue;

@SuppressWarnings("all")
public class BombermenServer extends Thread implements JSONEncode {

	private static final int INPUT_PORT = 8764;
	private static final int OUTPUT_PORT = 8768;
	private ConcurrentLinkedQueue<Message> inputQueue;
	private ConcurrentLinkedQueue<Message> outputQueue;

	private HashMap<String, String> players = new HashMap<>(8);

	public BombermenServer() {
		setName("BombermenServer Main Thread");
		inputQueue = new ConcurrentLinkedQueue<>();
		outputQueue = new ConcurrentLinkedQueue<>();
		try (ServerSocket inputServer = new ServerSocket(INPUT_PORT);
		     ServerSocket outputServer = new ServerSocket(OUTPUT_PORT))
		{
			Socket inputClient = inputServer.accept();
			Socket outputClient = outputServer.accept();
			(new Thread(new ServerSocketListener(inputClient, inputQueue), "Listener Thread")).start();
//			System.out.println("ServerSocketListener auf " + INPUT_PORT + " gestartet ...");
			(new Thread(new ServerSocketSender(outputClient, outputQueue), "Output Thread")).start();
//			System.out.println("ServerSocketSender auf " + INPUT_PORT + " gestartet ...");
		} catch (Exception exception) {
			exception.printStackTrace();
		}
	}

	@Override
	public void run() {
		try {
//			System.out.println("Hello, form the Bombermen Thread....");
			 while (true) {
				if (!inputQueue.isEmpty()) {
//					System.out.format("%s\n", Thread.currentThread().getName());
					Message message = inputQueue.poll();
					queryMessage(message);
				} else {
//					System.out.format("%s: %s%n", Thread.currentThread().getName(), "Sleeps for 1 milliseconds...");
					sleep(1);
				}
			}
		} catch (Exception exception) {
			System.out.println("Error in the Run Method of the main thread.....");
			exception.printStackTrace();
		}
	}

	private void queryMessage(@NotNull Message message) {
		switch (message.CODE) {
			case DROP_BOMB:
				new Thread(() -> {
					String[] array = message.PARAMETERS;
					array[0] = "bomb_explode";
					try {
//						System.err.format("%s: %s%n", Thread.currentThread().getName(), "Sleeps for 1991 milliseconds...");
						sleep(1991);
						outputQueue.add(new Message(array));
//						System.err.format("%s: %s%n", Thread.currentThread().getName(), "is going to join");
						join();
					} catch (Exception e) {
						System.out.println("Error in the queryMessage Method.....");
						e.printStackTrace();
					}
				}, "bomb_explode").start();
				break;
			case PLAYER_LOGIN:

				break;

			default:
				outputQueue.add(message);
		}
	}
}
