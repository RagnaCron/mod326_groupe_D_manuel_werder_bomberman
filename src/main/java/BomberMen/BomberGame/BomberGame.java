package BomberMen.BomberGame;

import BomberMen.BomberGame.Constants.BomberGameConstants;
import BomberMen.BomberGame.GameEntities.Player.PlayerStartPosition;
import BomberMen.BomberGame.Labyrinth.BomberLabyrinth;
import BomberMen.BomberGame.UIEntities.BomberJButton;
import BomberMen.BomberGame.UIEntities.BomberJTextArea;
import BomberMen.BomberGame.UIEntities.BomberJTextField;

import javax.swing.*;

public final class BomberGame extends JFrame implements BomberGameConstants {

	private BomberJTextField textField;
	private BomberJButton signInButton;
	private BomberJButton startGameButton;
	private String playerName = "";
	private BomberJTextArea textArea;
	private BomberLabyrinth labyrinth;

//	private GameKeyboardListener keyboard;

	public BomberGame() {
		super("Bombermen Game");
		setLayout(null);
		setResizable(false);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setPreferredSize(BOMBER_FRAME_SIZE);
		setLocation(INITIAL_LOCATION);
		setFocusable(true);

//		keyboard = new GameKeyboardListener();
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
			labyrinth.startGame(playerName);
			labyrinth.setNewPlayer(PlayerStartPosition.LEFT_UPPER_CORNER);

//			labyrinth.setNewPlayer(PlayerStartPosition.RIGHT_UPPER_CORNER);
//			labyrinth.setNewPlayer(PlayerStartPosition.LEFT_BOTTOM_CORNER);
//			labyrinth.setNewPlayer(PlayerStartPosition.RIGHT_BOTTOM_CORNER);

			startGameButton.setEnabled(false);
//			addKeyListener(keyboard);
//			labyrinth.addKeyListener(keyboard);

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

}