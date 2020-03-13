package BomberMen.BomberGame;

import BomberMen.BomberClientServerInterfaces.Messaging.CommandCode;
import BomberMen.BomberClientServerInterfaces.Messaging.CustomJSONArray;
import BomberMen.BomberClientServerInterfaces.Messaging.Message;
import BomberMen.BomberGame.Constants.BomberGameConstants;
import BomberMen.BomberGame.GameEntities.Player.PlayerStartPosition;
import BomberMen.BomberGame.Labyrinth.BomberLabyrinth;
import BomberMen.BomberGame.ServerConnection.BomberClientMulticastUDPListener;
import BomberMen.BomberGame.ServerConnection.BomberSocketSender;
import BomberMen.BomberGame.UIEntities.BomberJButton;
import BomberMen.BomberGame.UIEntities.BomberJTextArea;
import BomberMen.BomberGame.UIEntities.BomberJTextField;

import javax.swing.*;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.net.InetAddress;
import java.net.Socket;
import java.util.concurrent.ConcurrentLinkedQueue;

import static java.lang.Thread.currentThread;
import static java.lang.Thread.sleep;

public final class BomberGame extends JFrame implements BomberGameConstants {

	private BomberJTextField textField;
	private BomberJButton signInButton;
	private BomberJButton startGameButton;
	private String playerName = "";
	private BomberJTextArea textArea;
	private BomberLabyrinth labyrinth;

	private static final String GROUP = "230.0.0.1";
	private static final int INPUT_PORT = 8768;
	private static final int OUTPUT_PORT = 8764;
	private ConcurrentLinkedQueue<Message> inputQueue;
	private ConcurrentLinkedQueue<Message> outputQueue;
	private Socket outputSocket;

	public BomberGame() {
		super("Bombermen Game");
		setLayout(null);
		setResizable(false);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setPreferredSize(BOMBER_FRAME_SIZE);
		setLocation(INITIAL_LOCATION);
		setFocusable(true);
		addWindowListener(new WindowAdapter() {
			@Override
			public void windowClosing(WindowEvent e) {
				super.windowClosing(e);
				if (outputQueue != null) {
					outputQueue.add(new Message("player_exit " + playerName));
					try {
						sleep(1000);
						outputSocket.close();
					} catch (Exception ignored) {}
				}
				System.exit(0);
			}
		});
		loadLogin();
		pack();
		setVisible(true);
	}

	private void loadLogin() {
		createSignInButton();
		createTextField();
		createStartGameButton();
		createLoggingTextArea();
		createLabyrinth();
	}

	private void createSignInButton() {
		signInButton =  new BomberJButton("Sign In", SING_IN_BUTTON_POSITION);
		signInButton.setFocusable(false);
		signInButton.addActionListener(event -> {
			if (!textField.getText().trim().isEmpty()) {
				playerName = textField.getText().trim();
				textArea.append("Welcome " + playerName + "\n");
				textArea.append("You can now start a game, you will have to wait if a game is running...\n");
				connectToServer();
			} else {
				textArea.append("You have to enter a valid Name.\n");
			}
		});
		getContentPane().add(signInButton);
	}

	private void connectToServer() {
		try {
			this.inputQueue = new ConcurrentLinkedQueue<>();
			this.outputQueue = new ConcurrentLinkedQueue<>();
			this.outputSocket = new Socket(InetAddress.getLocalHost().getHostName(), OUTPUT_PORT);
			(new BomberSocketSender(outputSocket, outputQueue)).start();
			(new BomberClientMulticastUDPListener(INPUT_PORT, GROUP, inputQueue)).start();
			outputQueue.add(new Message(new String[]{"player_login", playerName}));
			validateConnection();
		} catch (Exception ignored1) {
			textArea.append("You cannot connect to the server... try later again\n");
			try {
				outputSocket.close();
			} catch (Exception ignored2) {}
		}
	}

	private void validateConnection() {
		(new Thread(() -> {
			try {
				boolean isRunning = true;
				while (isRunning) {
					if (!inputQueue.isEmpty()) {
						Message message = inputQueue.poll();
						if (message.getCode() == CommandCode.PLAYER_LOGIN_SUCCESS) {
							textField.setEnabled(false);
							signInButton.setEnabled(false);
							startGameButton.setVisible(true);
							isRunning = false;
						}
						textArea.append(message.getParameters().toJSONString());
					}
					sleep(0, 1000);
				}
				currentThread().join();
			} catch (Exception ignored) {}
		})).start();
	}

	private void createTextField() {
		textField = new BomberJTextField();
		getContentPane().add(textField);
	}

	private void createStartGameButton() {
		startGameButton = new BomberJButton("Start Game", START_GAME_BUTTON_POSITION);
		startGameButton.setVisible(false);
		startGameButton.setFocusable(false);
		startGameButton.addActionListener(event -> {
			listenForGameStart();
			startGameButton.setEnabled(false);
		});
		getContentPane().add(startGameButton);
	}

	private void listenForGameStart() {
		(new Thread(() -> {
			try {
				boolean isRunning = true;
				while (isRunning) {
					if (!inputQueue.isEmpty()) {
						Message message = inputQueue.poll();
						if (message.getCode() == CommandCode.LOAD_LABYRINTH) {
							labyrinth.loadGame(playerName, new CustomJSONArray(message.getParameters().getString(1)));
						}
						else if (message.getCode() == CommandCode.PLAYER_POSITION) {
							if (message.getPlayerName().equals(playerName)) {
								if (message.getValue(2).equals("0")) {
									labyrinth.setNewPlayer(PlayerStartPosition.LEFT_UPPER_CORNER);
								} else if (message.getValue(2).equals("1")) {
									labyrinth.setNewPlayer(PlayerStartPosition.RIGHT_UPPER_CORNER);
								} else if (message.getValue(2).equals("2")) {
									labyrinth.setNewPlayer(PlayerStartPosition.LEFT_BOTTOM_CORNER);
								} else if (message.getValue(2).equals("3")) {
									labyrinth.setNewPlayer(PlayerStartPosition.RIGHT_BOTTOM_CORNER);
								}
							}
							textArea.append(message.getParameters().toJSONString());
							sleep(0, 50000);
							continue;
						}
						if (message.getCode()  == CommandCode.START_GAME) {
							isRunning = false;
							labyrinth.startGame(inputQueue);
						}
						textArea.append(message.getParameters().toJSONString());
					}
					sleep(1);
				}
				currentThread().join();
			} catch (Exception ignored) {}
		})).start();
	}

	private void createLabyrinth() {
		labyrinth = new BomberLabyrinth(LABYRINTH_SIZE, LABYRINTH_POSITION);
		labyrinth.setFocusable(true);
		getContentPane().add(labyrinth);
	}

	private void createLoggingTextArea() {
		textArea = new BomberJTextArea();
		JScrollPane pane  = new JScrollPane(
				textArea,
				JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED,
				JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
		pane.setBounds(LOGGING_TEXTAREA_POSITION);
		pane.setFocusable(false);
		getContentPane().add(pane);
	}

}
