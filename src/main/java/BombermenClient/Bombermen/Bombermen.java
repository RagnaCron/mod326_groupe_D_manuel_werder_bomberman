package BombermenClient.Bombermen;

import BombermenClient.GameConstants;
import BombermenClient.Labyrinth.Labyrinth;
import BombermenClient.UserInterface.UserGameKeyboardInput;
import BombermenClientServerInterfaces.Messaging.CommandCode;
import BombermenClientServerInterfaces.Messaging.Message;

import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.util.concurrent.ConcurrentLinkedQueue;

import static java.lang.Thread.sleep;

public class Bombermen extends JFrame implements GameConstants {

	private JTextField textField;
	private JTextArea textArea;

	private ConcurrentLinkedQueue<Message> inputQueue = new ConcurrentLinkedQueue<>();
	private ConcurrentLinkedQueue<Message> outputQueue = new ConcurrentLinkedQueue<>();

	private ClientServerProxy serverConnection;

	public Bombermen() {
		super("Bombermen");
		setResizable(false);
		setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
		setPreferredSize(BOMBERMAN_FRAME_SIZE);
		addWindowListener(new BombermenWindowListener(outputQueue));
		addKeyListener(BombermenKeyboardListener.CreateBombermenKeyboardListener(outputQueue));

		loadServerLogin();
		loadServerLoggingTextArea();
		loadLabyrinth();

		connectToServer();

		pack();
		setVisible(true);
	}

	private void loadServerLogin() {
		textField = new JTextField("Your Player Name");
		textField.setBounds(TEXT_INPUT_POSITION);
		textField.addKeyListener(BombermenKeyboardListener.CreateBombermenKeyboardListener(outputQueue));
		add(textField);
		JButton signInButton = new JButton("Sign In");
		signInButton.setBounds(SING_IN_BUTTON_POSITION);
		signInButton.addKeyListener(BombermenKeyboardListener.CreateBombermenKeyboardListener(outputQueue));
		add(signInButton);
	}

	private void loadServerLoggingTextArea() {
		textArea = new JTextArea(5, 49);
		textArea.setWrapStyleWord(true);
		textArea.setLineWrap(true);
		textArea.setFont(new Font("DialogInput", Font.PLAIN, 18));
		textArea.setEditable(false);
		textArea.setTabSize(4);
		JScrollPane pane  = new JScrollPane(textArea, JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED,
				JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
		pane.setBounds(SERVER_LOGGING_TEXTAREA_POSITION);
		pane.addKeyListener(BombermenKeyboardListener.CreateBombermenKeyboardListener(outputQueue));
		add(pane);
	}

	private void connectToServer() {
		serverConnection = new ClientServerProxy(inputQueue, outputQueue, textArea);
//		serverConnection.addPropertyChangeListener();
		serverConnection.execute();
	}

	private void loadLabyrinth() {
		Labyrinth labyrinth = new Labyrinth(LABYRINTH_WIDTH, LABYRINTH_HEIGHT);
		labyrinth.setBounds(LABYRINTH_POSITION);
		labyrinth.addKeyListener(new UserGameKeyboardInput());
		setLayout(null);
		add(labyrinth);
	}


	private static class BombermenKeyboardListener extends KeyAdapter {
		private static BombermenKeyboardListener keyboardListener;
		private static ConcurrentLinkedQueue<Message> queue;
		private BombermenKeyboardListener() {
			super();
		}
		public synchronized static BombermenKeyboardListener CreateBombermenKeyboardListener(ConcurrentLinkedQueue<Message> outputQueue) {
			if (keyboardListener == null) {
				keyboardListener = new BombermenKeyboardListener();
				queue = outputQueue;
			}
			return keyboardListener;
		}
		@Override
		public void keyPressed(KeyEvent event) {
			if (event.getKeyCode() == KeyEvent.VK_ESCAPE) {
				queue.add(new Message(CommandCode.PLAYER_EXIT, "player_exit playerID socketID saveGamePoints".split(" ")));
			    GoodbyePlayer();
			}
		}
	}

	private static class BombermenWindowListener extends WindowAdapter {
		private ConcurrentLinkedQueue<Message> outputQueue;

		public BombermenWindowListener(ConcurrentLinkedQueue<Message> outputQueue) {
			this.outputQueue = outputQueue;
		}

		@Override
		public void windowClosing(WindowEvent e) {
			outputQueue.add(new Message(CommandCode.PLAYER_EXIT, "player_exit playerID socketID saveGamePoints".split(" ")));
			GoodbyePlayer();
		}
	}

	private static void GoodbyePlayer() {
		new Thread(() -> {
			try {
				sleep(5000);
				System.exit(0);
			} catch (Exception ignored) {}
		}).start();
	}
}
