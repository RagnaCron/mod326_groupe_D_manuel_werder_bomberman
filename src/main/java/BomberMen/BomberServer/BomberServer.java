package BomberMen.BomberServer;

import BomberMen.BomberClientServerInterfaces.Messaging.CommandCode;
import BomberMen.BomberClientServerInterfaces.Messaging.Message;
import org.jetbrains.annotations.NotNull;

import java.net.InetAddress;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Arrays;
import java.util.HashMap;
import java.util.concurrent.ConcurrentLinkedQueue;

public final class BomberServer extends Thread {

	private static final int INPUT_PORT = 8764;
	private static final int OUTPUT_PORT = 8768;
	private static final String GROUP = "230.0.0.1";
	private ConcurrentLinkedQueue<Message> inputQueue;
	private ConcurrentLinkedQueue<Message> outputQueue;
	private ServerSocket inputServer;

	private static final int MAX_PLAYER_NUMBER = 1;
	private HashMap<String, Integer> playerNames = new HashMap<>(4);
	private boolean hasGameStarted = false;

	public BomberServer() {
		setName("BomberServer Main Thread");
		inputQueue = new ConcurrentLinkedQueue<>();
		outputQueue = new ConcurrentLinkedQueue<>();
	}

	@Override
	public void run() {
		try {
			inputServer = new ServerSocket(INPUT_PORT);
			InetAddress group = InetAddress.getByName(GROUP);
			acceptNewPlayers();
			connectUDPSender(group);
			while (true) {
				startNewGame();
				if (!inputQueue.isEmpty()) {
					Message message = inputQueue.poll();
					queryMessage(message);
				}
				sleep(1000);
			}
		} catch (Exception exception) {
			exception.printStackTrace();
		}
	}

	private void connectUDPSender(InetAddress group) {
		(new Thread(
				(new BomberServerMulticastUDPSender(group, OUTPUT_PORT, outputQueue)),
				"Output Thread")
		).start();
	}

	private void queryMessage(Message message) {
		String name;
		switch (message.getCode()) {
			case DROP_BOMB:
				dropBomb(message);
				break;
			case PLAYER_LOGIN:
				name = message.getPlayerName();
				outputQueue.add(loginMessage(name));
				break;
			case PLAYER_EXIT:
				name = message.getPlayerName();
				outputQueue.add(playerExitMessage(name));
				break;
			default:
				outputQueue.add(message);
		}
	}

	private Message playerExitMessage(String name) {
		Message message;
		if (playerNames.containsKey(name)) {
			playerNames.remove(name);
			hasGameStarted = playerNames.size() > 0 &&  hasGameStarted;
			message =  new Message(new String[]{"player_goodbye", name, "Goodbye " + name + "!"});
		} else {
			message =  new Message(new String[]{"player_goodbye", "Goodbye!"});
		}
		return message;
	}

	@NotNull
	private Message loginMessage(String name) {
		Message message;
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
						outputQueue.add(new Message(new String[]{"player_position", name, playerNames.get(name).toString()}));
					}
					outputQueue.add(new Message("start_game"));
					join();
				} catch (Exception ignored) {}
			})).start();
		}
	}

	private void dropBomb(Message message) {
		new Thread(() -> {
			Message m = message;
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
			while (playerNames.size() < 4) {
				try {
					Socket inputClient = inputServer.accept();
					(new Thread(new BomberServerSocketListener(inputClient, inputQueue))).start();
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
