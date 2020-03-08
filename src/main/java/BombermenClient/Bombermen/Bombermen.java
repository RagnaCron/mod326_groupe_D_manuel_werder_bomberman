package BombermenClient.Bombermen;

import BombermenClient.Bombermen.ClientCommunication.ClientServerProxy;
import BombermenClient.Labyrinth.Labyrinth;
import BombermenClient.UserInterface.BombermenJButton;
import BombermenClient.UserInterface.BombermenJTextArea;
import BombermenClient.UserInterface.BombermenJTextField;
import BombermenClientServerInterfaces.Messaging.Message;

import javax.swing.*;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.util.concurrent.ConcurrentLinkedQueue;

import static java.lang.Thread.sleep;

public final class Bombermen extends JFrame implements GameConstants {

	private BombermenJTextField textField;
	private BombermenJButton signInButton;
	private String playerName = "";
	private BombermenJTextArea textArea;
	private Labyrinth labyrinth;

	private ConcurrentLinkedQueue<Message> inputQueue;
	private ConcurrentLinkedQueue<Message> outputQueue;

	private ClientServerProxy serverConnection;

	public Bombermen() {
		super("Bombermen");
		setResizable(false);
		setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
		setPreferredSize(BOMBERMEN_FRAME_SIZE);
		setLayout(null);
		addWindowListener(new WindowAdapter() {
			@Override
			public void windowClosing(WindowEvent e) {
				super.windowClosing(e);
				goodbyePlayer();
			}
		});
		loadServerLogin();
		loadServerLoggingTextArea();
		loadLabyrinth();

//		connectToServer();

		pack();
		setVisible(true);
	}

	private void loadServerLogin() {
		signInButton = new BombermenJButton();
		signInButton.addActionListener((event) -> {
			String name = textField.getText().trim();
			if (!name.isEmpty()) {
				inputQueue = new ConcurrentLinkedQueue<>();
				outputQueue = new ConcurrentLinkedQueue<>();
				connectToServer();
				System.out.println("Ready to send message for login in....");
				outputQueue.add(new Message("player_login " + name));
			} else {
				textArea.append("You have to enter a valid Name.\n");
			}
		});
		add(signInButton);
		textField = new BombermenJTextField(outputQueue);
		add(textField);
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
		serverConnection = new ClientServerProxy(inputQueue, outputQueue, labyrinth, textArea, textField, signInButton);
		serverConnection.start();
	}

	private void loadLabyrinth() {
		labyrinth = new Labyrinth(LABYRINTH_WIDTH, LABYRINTH_HEIGHT);
		labyrinth.setBounds(LABYRINTH_POSITION);
//		labyrinth.addKeyListener(new UserGameKeyboardInput());
		add(labyrinth);
	}



	public void goodbyePlayer() {
		new Thread(() -> {
			try {
				if (inputQueue != null)
					inputQueue.add(new Message("player_exit"));
				sleep(2000);
				System.exit(0);
			} catch (Exception ignored) {}
		}).start();
	}
}
