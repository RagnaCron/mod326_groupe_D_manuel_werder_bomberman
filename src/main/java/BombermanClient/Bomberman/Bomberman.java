package BombermanClient.Bomberman;

import BombermanClient.GameConstants;
import BombermanClient.Labyrinth.Labyrinth;
import BombermanClient.UserInterface.UserGameKeyboardInput;

import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

public class Bomberman extends JFrame implements GameConstants {


	public Bomberman() {
		super("Bomberman");
		setResizable(false);
		setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
		setPreferredSize(BOMBERMAN_FRAME_SIZE);
		addWindowListener(new BombermanWindowListener());
		addKeyListener(BomermanKeyboardListener.CreateBombermanKeyboardListener());

		loadServerLogin();
		loadServerLoggingTextArea();
		loadLabyrinth();

		pack();
		setVisible(true);
	}

	private void loadServerLogin() {
		JTextField textInput = new JTextField("");
		textInput.setBounds(TEXT_INPUT_POSITION);
		textInput.addKeyListener(BomermanKeyboardListener.CreateBombermanKeyboardListener());
		add(textInput);
		JButton signInButton = new JButton("Sign In");
		signInButton.setBounds(SING_IN_BUTTON_POSITION);
		signInButton.addKeyListener(BomermanKeyboardListener.CreateBombermanKeyboardListener());
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
		pane.addKeyListener(BomermanKeyboardListener.CreateBombermanKeyboardListener());
		add(pane);
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
		private BomermanKeyboardListener() {
			super();
		}
		public synchronized static BomermanKeyboardListener CreateBombermanKeyboardListener() {
			if (keyboardListener == null)
				keyboardListener = new BomermanKeyboardListener();
			return keyboardListener;
		}
		@Override
		public void keyPressed(KeyEvent e) {
			if (e.getKeyCode() == KeyEvent.VK_ESCAPE) {
				System.out.println("Exiting Bomberman Game...");
				System.exit(0);
			}
		}
	}

	private static class BombermanWindowListener extends WindowAdapter {
		@Override
		public void windowClosing(WindowEvent e) {
			// TODO: SEND MESSAGE TO SERVER
			System.out.println("Closing Bomberman Game...");
			System.exit(0);
		}
	}
}
