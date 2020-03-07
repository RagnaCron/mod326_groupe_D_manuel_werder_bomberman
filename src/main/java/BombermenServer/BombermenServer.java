package BombermenServer;

import BombermenClientServerInterfaces.Messaging.CommandCode;
import BombermenClientServerInterfaces.Messaging.JSONEncode;
import BombermenClientServerInterfaces.Messaging.Message;
import org.jetbrains.annotations.NotNull;

import java.net.InetAddress;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.HashSet;
import java.util.Set;
import java.util.concurrent.ConcurrentLinkedQueue;

@SuppressWarnings("all")
public class BombermenServer extends Thread implements JSONEncode {

	private static final int INPUT_PORT = 8764;
	private static final int OUTPUT_PORT = 8768;
	private ConcurrentLinkedQueue<Message> inputQueue;
	private ConcurrentLinkedQueue<Message> outputQueue;
	private ServerSocket inputServer;
	private ServerSocket outputServer;

	private Set<String> playerNames = new HashSet<>();

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

			acceptNewPlayers();

			(new Thread((new ServerMulticastUDPSender(group, OUTPUT_PORT, outputQueue)), "Output Thread")).start();

			System.out.println("Hello, form the Bombermen Thread....");
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
		switch (message.getCode()) {
			case DROP_BOMB:
				new Thread(() -> {
					Message m = message;
					m.setValue(0, "bomb_explode");
					m.setCode(CommandCode.BOMB_EXPLODE);
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
				String name = message.getValue(1);
				Message m;
				if (playerNames.add(name)) {
					m = new Message(new String[]{"player_login_success", "Welcome " + name + "!"});
				} else {
					m = new Message(new String[]{"player_login_error", "Player name is taken: " + name});
				}
				outputQueue.add(m);
				break;

			case PLAYER_EXIT:

				break;

			default:
				outputQueue.add(message);
		}
	}

	private void acceptNewPlayers() {
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
	}
}
