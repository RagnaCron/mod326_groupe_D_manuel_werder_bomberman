package BombermenServer;

import BombermenClientServerInterfaces.Messaging.CommandCode;
import BombermenClientServerInterfaces.Messaging.JSONEncode;
import BombermenClientServerInterfaces.Messaging.Message;
import org.jetbrains.annotations.NotNull;

import java.net.InetAddress;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Arrays;
import java.util.HashMap;
import java.util.concurrent.ConcurrentLinkedQueue;

@SuppressWarnings("all")
public final class BombermenServer extends Thread implements JSONEncode {

	private static final int INPUT_PORT = 8764;
	private static final int OUTPUT_PORT = 8768;
	private ConcurrentLinkedQueue<Message> inputQueue;
	private ConcurrentLinkedQueue<Message> outputQueue;
	private ServerSocket inputServer;
	private ServerSocket outputServer;

	private static final int MAX_PLAYER_NUMBER = 1;
	private HashMap<String, Integer> playerNames = new HashMap<>(8);
	private boolean hasGameStarted = false;

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
			 while (true) {
				 startNewGame();
				if (!inputQueue.isEmpty()) {
					Message message = inputQueue.poll();
					queryMessage(message);
				}
				sleep(1000);
			 }
		} catch (Exception exception) {
			System.out.println("Error in the Run Method of the main thread.....");
			exception.printStackTrace();
		}
	}

	private void queryMessage(Message message) {
		String name = "";
		switch (message.getCode()) {
			case DROP_BOMB:
				dropBomb(message);
				break;
			case PLAYER_LOGIN:
				name = message.getPlayerName();
				outputQueue.add(loginMessage(message, name));
				break;
			case PLAYER_EXIT:
				name = message.getPlayerName();
				outputQueue.add(playerExitMessage(message, name));
				break;
			default:
				outputQueue.add(message);
		}
	}

	private Message playerExitMessage(Message message, String name) {
		if (playerNames.containsKey(name)) {
			playerNames.remove(name);
			hasGameStarted = hasGameStarted && !playerNames.isEmpty() ? true : false;
			message =  new Message(new String[]{"player_goodbye", name, "Goodbye " + name + "!"});
		} else {
			message =  new Message(new String[]{"player_goodbye", "Goodbye!"});
		}
		return message;
	}

	@NotNull
	private Message loginMessage(Message message, String name) {
		if (!playerNames.containsKey(name)) {
			if (playerNames.size() < MAX_PLAYER_NUMBER) {
				playerNames.put(name, playerNames.size());
				message = new Message(new String[]{"player_login_success", name, "Welcome " + name + "!"});
			} else {
				message = new Message(new String[]{"server_full", name, "Server is full, try later again..."});
			}
		} else {
			message = new Message(new String[]{"player_login_error", "Player name is taken: " + name});
		}
		return message;
	}

	private void startNewGame() {
		if (!hasGameStarted && playerNames.size() == MAX_PLAYER_NUMBER) {
			outputQueue.add(new Message(new String[]{"load_labyrinth",  Arrays.deepToString(DefaultBoard)}));
			hasGameStarted = true;
			(new Thread(() -> {
				try {
					sleep(2000);
					for (var name : playerNames.keySet()) {
						outputQueue.add(new Message(new String[]{"start_game", name, playerNames.get(name).toString()}));
					}
					join();
				} catch (Exception ignored) {}
			})).start();
		}
//		while (startGame.isAlive());
//		startGame = null;
	}

	private void dropBomb(Message message) {
		Message finalMessage = message;
		new Thread(() -> {
			Message m = finalMessage;
			m.setValue(0, "bomb_explode");
			m.setCode(CommandCode.BOMB_EXPLODE);
			try {
				sleep(1991);
				outputQueue.add(m);
				join();
			} catch (Exception e) {
				System.out.println("Error in the queryMessage Method.....");
				e.printStackTrace();
			}
		}, "bomb_explode").start();
	}

	private void acceptNewPlayers() {
		new Thread(() -> {
			while (true) {
				try {
					Socket inputClient = inputServer.accept();
					(new Thread(new ServerSocketListener(inputClient, inputQueue))).start();
				} catch (Exception exception) {
					exception.printStackTrace();
				}
			}
		}, "Accept new User input Thread").start();
	}

	private final static String[][] DefaultBoard = {
			{"1","2","2","2","2","2","2","2","2","2","2","2","2","2","2","1"},
			{"1","0","0","0","0","0","0","3","3","0","0","0","0","0","0","1"},
			{"1","0","2","0","2","0","2","3","3","2","0","2","0","2","0","1"},
			{"1","0","0","0","0","0","0","3","3","0","0","0","0","0","0","1"},
			{"1","0","2","0","2","0","2","3","3","2","0","2","0","2","0","1"},
			{"1","0","0","0","0","0","0","3","3","0","0","0","0","0","0","1"},
			{"1","0","2","0","2","0","2","3","3","2","0","2","0","2","0","1"},
			{"1","3","3","3","3","3","3","3","3","3","3","3","3","3","3","1"},
			{"1","3","3","3","3","3","3","3","3","3","3","3","3","3","3","1"},
			{"1","0","2","0","2","0","2","3","3","2","0","2","0","2","0","1"},
			{"1","0","0","0","0","0","0","3","3","0","0","0","0","0","0","1"},
			{"1","0","2","0","2","0","2","3","3","2","0","2","0","2","0","1"},
			{"1","0","0","0","0","0","0","3","3","0","0","0","0","0","0","1"},
			{"1","0","2","0","2","0","2","3","3","2","0","2","0","2","0","1"},
			{"1","0","0","0","0","0","0","3","3","0","0","0","0","0","0","1"},
			{"2","2","2","2","2","2","2","2","2","2","2","2","2","2","2","2"},
	};
}
