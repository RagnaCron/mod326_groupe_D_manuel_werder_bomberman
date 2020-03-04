package BombermenServer;

import BombermenClientServerInterfaces.Messaging.CommandCode;
import BombermenClientServerInterfaces.Messaging.JSONEncode;
import BombermenClientServerInterfaces.Messaging.Message;
import org.jetbrains.annotations.NotNull;

import java.net.InetAddress;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentLinkedQueue;

@SuppressWarnings("all")
public class BombermenServer extends Thread implements JSONEncode {

	private static final int INPUT_PORT = 8764;
	private static final int OUTPUT_PORT = 8768;
	private ConcurrentLinkedQueue<Message> inputQueue;
	private ConcurrentLinkedQueue<Message> outputQueue;
	private ServerSocket inputServer;
	private ServerSocket outputServer;

	private ConcurrentHashMap<String, String> players = new ConcurrentHashMap<>(8);

	public BombermenServer() {
		setName("BombermenServer Main Thread");
		inputQueue = new ConcurrentLinkedQueue<>();
		outputQueue = new ConcurrentLinkedQueue<>();
	}

	@Override
	public void run() {
		try {
			inputServer = new ServerSocket(INPUT_PORT);
			InetAddress group = InetAddress.getByName("230.0.0.1");
//			outputServer = new ServerSocket(OUTPUT_PORT);
//			inputServer.setReuseAddress(true);
//			outputServer.setReuseAddress(true);
//			outputServer.setOption(true, StandardSocketOptions.SO_BROADCAST);

			new Thread(() -> {
				while (true) {
					try {
//						sleep(1);
						Socket inputClient = inputServer.accept();
						(new Thread(new ServerSocketListener(inputClient, inputQueue))).start();
					} catch (Exception exception) {
						exception.printStackTrace();
					}
				}
			}, "Accept new User input Thread").start();

//			Socket outputClient = outputServer.accept();
//			(new Thread(new ServerSocketListener(inputClient, inputQueue), "Listener Thread")).start();
//			System.out.println("ServerSocketListener auf " + INPUT_PORT + " gestartet ...");
//			(new Thread(new ServerSocketSender(outputClient, outputQueue), "Output Thread")).start();
			(new Thread((new MulticastUDPSender(group, OUTPUT_PORT, outputQueue)), "Output Thread")).start();
//			System.out.println("ServerSocketSender auf " + INPUT_PORT + " gestartet ...");
//			System.out.println("Hello, form the Bombermen Thread....");
//			outputQueue.add(new Message(new String[]{"Hello, world! Welcome to the Bombermen Server."}));
			 while (true) {
//				 outputQueue.add(new Message(new String[]{"Hello, world! Welcome to the Bombermen Server."}));
				if (!inputQueue.isEmpty()) {
//					System.out.format("%s\n", Thread.currentThread().getName());
					Message message = inputQueue.poll();
					queryMessage(message);
				} else {
//					System.out.format("%s: %s%n", Thread.currentThread().getName(), "Sleeps for 1 milliseconds...");
					sleep(1000);
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

//				break;

			default:
				outputQueue.add(message);
		}
	}
}
