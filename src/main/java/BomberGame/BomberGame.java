package BomberGame;

import BomberGame.Constants.BomberGameConstants;
import BomberGame.Labyrinth.BomberLabyrinth;
import BomberGame.UIEntities.BomberJButton;
import BomberGame.UIEntities.BomberJTextArea;
import BomberGame.UIEntities.BomberJTextField;

import javax.swing.*;

public final class BomberGame extends JFrame implements BomberGameConstants {

	private BomberJTextField textField;
	private BomberJButton signInButton;
	private BomberJButton startGameButton;
	private String playerName = "";
	private BomberJTextArea textArea;
	private BomberLabyrinth labyrinth;

	public BomberGame() {
		super("Bombermen Game");
		setLayout(null);
		setResizable(false);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setPreferredSize(BOMBER_FRAME_SIZE);
		setLocation(INITIAL_LOCATION);

		loadLogin();
		createStartGameButton();
		loadLoggingTextArea();
		loadLabyrinth();

		pack();
		setVisible(true);
	}

	private void loadLogin() {
		createSignInButton();
		createTextField();
	}

	private void createSignInButton() {
		signInButton =  new BomberJButton("Sign In", SING_IN_BUTTON_POSITION);
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
		add(signInButton);
	}

	private void createTextField() {
		textField = new BomberJTextField();
		add(textField);
	}

	private void createStartGameButton() {
		startGameButton = new BomberJButton("Start Game", START_GAME_BUTTON_POSITION);
		startGameButton.setVisible(false);
		startGameButton.addActionListener(event -> {
			labyrinth.populateNewBoard(BomberLabyrinth.DefaultBoard);
			labyrinth.repaint(labyrinth.getBounds());
		});
		add(startGameButton);
	}

	private void loadLabyrinth() {
		labyrinth = new BomberLabyrinth(LABYRINTH_SIZE, LABYRINTH_POSITION);
		add(labyrinth);
	}

	private void loadLoggingTextArea() {
		textArea = new BomberJTextArea();
		JScrollPane pane  = new JScrollPane(
				textArea,
				JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED,
				JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
		pane.setBounds(LOGGING_TEXTAREA_POSITION);
		add(pane);
	}

}
