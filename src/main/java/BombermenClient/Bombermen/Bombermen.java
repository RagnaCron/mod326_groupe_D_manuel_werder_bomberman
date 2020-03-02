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

	private JTextField textInput;

	private ConcurrentLinkedQueue<Message> outputQueue = new ConcurrentLinkedQueue<>();

	private ClientServerProxy serverConnection;

	public Bombermen() {
		super("Bombermen");
		setResizable(false);
		setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
		setPreferredSize(BOMBERMAN_FRAME_SIZE);
		addWindowListener(new BombermanWindowListener(outputQueue));
		addKeyListener(BomermanKeyboardListener.CreateBombermanKeyboardListener(outputQueue));

		loadServerLogin();
		loadServerLoggingTextArea();
		loadLabyrinth();

		connectToServer();

		pack();
		setVisible(true);
	}

	private void loadServerLogin() {
		textInput = new JTextField("Your Player Name");
		textInput.setBounds(TEXT_INPUT_POSITION);
		textInput.addKeyListener(BomermanKeyboardListener.CreateBombermanKeyboardListener(outputQueue));
		add(textInput);
		JButton signInButton = new JButton("Sign In");
		signInButton.setBounds(SING_IN_BUTTON_POSITION);
		signInButton.addKeyListener(BomermanKeyboardListener.CreateBombermanKeyboardListener(outputQueue));
		add(signInButton);
	}

	private void loadServerLoggingTextArea() {
		JTextArea area = new JTextArea(5, 49);
		area.setWrapStyleWord(true);
		area.setLineWrap(true);
		area.setFont(new Font("DialogInput", Font.PLAIN, 18));
		area.setEditable(false);
		area.setTabSize(4);
		JScrollPane pane  = new JScrollPane(area, JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED,
				JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
		pane.setBounds(SERVER_LOGGING_TEXTAREA_POSITION);
		pane.addKeyListener(BomermanKeyboardListener.CreateBombermanKeyboardListener(outputQueue));
		add(pane);
	}

	private void connectToServer() {
		serverConnection = new ClientServerProxy(outputQueue);
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


	private static class BomermanKeyboardListener extends KeyAdapter {
		private static BomermanKeyboardListener keyboardListener;
		private static ConcurrentLinkedQueue<Message> queue;
		private BomermanKeyboardListener() {
			super();
		}
		public synchronized static BomermanKeyboardListener CreateBombermanKeyboardListener(ConcurrentLinkedQueue<Message> outputQueue) {
			if (keyboardListener == null) {
				keyboardListener = new BomermanKeyboardListener();
				queue = outputQueue;
			}
			return keyboardListener;
		}
		@Override
		public void keyPressed(KeyEvent event) {
			if (event.getKeyCode() == KeyEvent.VK_ESCAPE) {
				queue.add(new Message(CommandCode.PLAYER_EXIT, "player_exit playerID saveGamePoints".split(" ")));
			    GoodbeyPlayer();
			}
		}
	}

	private static class BombermanWindowListener extends WindowAdapter {
		private ConcurrentLinkedQueue<Message> outputQueue;

		public BombermanWindowListener(ConcurrentLinkedQueue<Message> outputQueue) {
			this.outputQueue = outputQueue;
		}

		@Override
		public void windowClosing(WindowEvent e) {
			// TODO: SEND MESSAGE TO SERVER
			outputQueue.add(new Message(CommandCode.PLAYER_EXIT, "player_exit playerID saveGamePoints".split(" ")));
			GoodbeyPlayer();
		}
	}

	private static void GoodbeyPlayer() {
		new Thread(() -> {
			try {
				sleep(5000);
				System.exit(0);
			} catch (Exception ignored) {}
		}).start();
	}
}
