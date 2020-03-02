package BombermenServer;

import BombermenClientServerInterfaces.Messaging.CommandCode;
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
	private ServerSocket inputServer;
	private ServerSocket outputServer;

	private HashMap<String, String> players = new HashMap<>(8);

	public BombermenServer() {
		setName("BombermenServer Main Thread");
		inputQueue = new ConcurrentLinkedQueue<>();
		outputQueue = new ConcurrentLinkedQueue<>();
		try
		{
			inputServer = new ServerSocket(INPUT_PORT);
			outputServer = new ServerSocket(OUTPUT_PORT);
//			inputServer.setReuseAddress(true);
//			outputServer.setReuseAddress(true);
//			outputServer.setOption(true, StandardSocketOptions.SO_BROADCAST);

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
		Message m = message;
		switch (m.CODE) {
			case DROP_BOMB:
				new Thread(() -> {
					m.PARAMETERS[0] = "bomb_explode";
					m.CODE = CommandCode.BOMB_EXPLODE;
					try {
//						System.err.format("%s: %s%n", Thread.currentThread().getName(), "Sleeps for 1991 milliseconds...");
						sleep(1991);
						outputQueue.add(m);
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

			case PLAYER_EXIT:

				break;

			default:
				outputQueue.add(message);
		}
	}
}
