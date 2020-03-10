package BomberGame;

import BomberGame.Constants.BomberGameConstants;
import BomberGame.GameEntities.Player.Direction;
import BomberGame.GameEntities.Player.PlayerStartPosition;
import BomberGame.Labyrinth.BomberLabyrinth;
import BomberGame.UIEntities.BomberJButton;
import BomberGame.UIEntities.BomberJTextArea;
import BomberGame.UIEntities.BomberJTextField;

import javax.swing.*;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

public final class BomberGame extends JFrame implements BomberGameConstants {

	private BomberJTextField textField;
	private BomberJButton signInButton;
	private BomberJButton startGameButton;
	private String playerName = "";
	private BomberJTextArea textArea;
	private BomberLabyrinth labyrinth;

	private GameKeyboardListener keyboard;

	public BomberGame() {
		super("Bombermen Game");
		setLayout(null);
		setResizable(false);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setPreferredSize(BOMBER_FRAME_SIZE);
		setLocation(INITIAL_LOCATION);

		keyboard = new GameKeyboardListener();
//		addKeyListener(keyboard);

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

				textField.setEnabled(false);
				signInButton.setEnabled(false);

				startGameButton.setVisible(true);
			} else {
				textArea.append("You have to enter a valid Name.\n");
			}
		});
		getContentPane().add(signInButton);
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
			labyrinth.startGame();
			labyrinth.setNewPlayer(playerName, PlayerStartPosition.LEFT_UPPER_CORNER);

//			labyrinth.setNewPlayer(PlayerStartPosition.RIGHT_UPPER_CORNER);
//			labyrinth.setNewPlayer(PlayerStartPosition.LEFT_BOTTOM_CORNER);
//			labyrinth.setNewPlayer(PlayerStartPosition.RIGHT_BOTTOM_CORNER);

			startGameButton.setEnabled(false);
			labyrinth.addKeyListener(keyboard);

		});
		getContentPane().add(startGameButton);
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

	private class GameKeyboardListener extends KeyAdapter {
		@Override
		public void keyPressed(KeyEvent e) {
			switch (e.getKeyCode()) {
//				case KeyEvent.VK_ESCAPE:
//					System.out.println("Exiting Bombermen Game...");
//					System.exit(0);
//					break;
				case KeyEvent.VK_W:
				case KeyEvent.VK_UP:
					System.out.println("Go up...");
					labyrinth.movePlayer(playerName, Direction.FACING_UP);
					break;
				case KeyEvent.VK_D:
				case KeyEvent.VK_RIGHT:
					labyrinth.movePlayer(playerName, Direction.FACING_RIGHT);
					System.out.println("Go right...");
					break;
				case KeyEvent.VK_S:
				case KeyEvent.VK_DOWN:
					labyrinth.movePlayer(playerName, Direction.FACING_DOWN);
					System.out.println("Go down...");
					break;
				case KeyEvent.VK_A:
				case KeyEvent.VK_LEFT:
					labyrinth.movePlayer(playerName, Direction.FACING_LEFT);
					System.out.println("Go left...");
					break;
				default:
					break;
			}
		}

		@Override
		public void keyTyped(KeyEvent e) {
			if (e.getKeyCode() == KeyEvent.VK_SPACE) {
				System.out.println("Drop bomb...");
			}
		}
	}



}
