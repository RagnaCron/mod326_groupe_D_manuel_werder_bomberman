package BombermenClient.Bombermen;

import BombermenClient.Bombermen.ClientCommunication.ClientServerProxy;
import BombermenClient.Labyrinth.Labyrinth;
import BombermenClient.UserInterface.BombermenJButton;
import BombermenClient.UserInterface.BombermenJTextArea;
import BombermenClient.UserInterface.BombermenJTextField;
import BombermenClient.UserInterface.UserEvents.BombermenWindowListener;
import BombermenClientServerInterfaces.Messaging.Message;

import javax.swing.*;
import java.util.concurrent.ConcurrentLinkedQueue;

import static java.lang.Thread.sleep;

public final class Bombermen extends JFrame implements GameConstants {

	private BombermenJTextField textField;
	private BombermenJButton signInButton;
	private String userName = "";
	private BombermenJTextArea textArea;
	private Labyrinth labyrinth;

	private ConcurrentLinkedQueue<Message> inputQueue = new ConcurrentLinkedQueue<>();
	private ConcurrentLinkedQueue<Message> outputQueue = new ConcurrentLinkedQueue<>();

	private ClientServerProxy serverConnection;

	public Bombermen() {
		super("Bombermen");
		setResizable(false);
		setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
		setPreferredSize(BOMBERMAN_FRAME_SIZE);
		setLayout(null);
		addWindowListener(new BombermenWindowListener(outputQueue));

		loadServerLogin();
		loadServerLoggingTextArea();
		loadLabyrinth();

		connectToServer();

		pack();
		setVisible(true);
	}

	private void loadServerLogin() {
		textField = new BombermenJTextField(outputQueue);
		add(textField);
		signInButton = new BombermenJButton(outputQueue, textField);
		add(signInButton);
	}

	private void loadServerLoggingTextArea() {
		textArea = new BombermenJTextArea();
		JScrollPane pane  = new JScrollPane(
				textArea,
				JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED,
				JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
		pane.setBounds(SERVER_LOGGING_TEXTAREA_POSITION);
		add(pane);
	}

	private void connectToServer() {
		serverConnection = new ClientServerProxy(inputQueue, outputQueue, textArea);
//		serverConnection.addPropertyChangeListener(event -> {
//			System.err.format("Hello form the PropertyChangeListener: %s%n", event.getPropertyName());
//		});
		serverConnection.start();
	}

	private void loadLabyrinth() {
		labyrinth = new Labyrinth(LABYRINTH_WIDTH, LABYRINTH_HEIGHT);
		labyrinth.setBounds(LABYRINTH_POSITION);
//		labyrinth.addKeyListener(new UserGameKeyboardInput());
		add(labyrinth);
	}



	public static void GoodbyePlayer() {
		new Thread(() -> {
			try {
				sleep(2500);
				System.exit(0);
			} catch (Exception ignored) {}
		}).start();
	}
}
