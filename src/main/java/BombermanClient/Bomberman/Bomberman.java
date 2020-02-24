package BombermanClient.Bomberman;

import BombermanClient.GameConstants;
import BombermanClient.Labyrinth.Labyrinth;
import BombermanClient.UserInterface.UserGameKeyboardInput;
import BombermanClient.UserInterface.UserKeyboardInput;

import javax.swing.*;

// zetcode.com/tutorials/javagamestutorial/movingsprites/

public class Bomberman extends JFrame implements GameConstants {

	UserKeyboardInput keyboardInput = new UserKeyboardInput();

	public Bomberman() {
		super("Bomberman");
		setResizable(false);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setPreferredSize(BOMBERMAN_FRAME_SIZE);

		addKeyListener(keyboardInput);

		loadServerLogin();
		loadLabyrinth();

		pack();
		setVisible(true);
	}

	private void loadServerLogin() {
		JTextField textInput = new JTextField("");
		textInput.setBounds(TEXT_INPUT_POSITION);
		textInput.addKeyListener(keyboardInput);
		add(textInput);
		JButton signInButton = new JButton("Sign In");
		signInButton.setBounds(SING_IN_BUTTON_POSITION);
		signInButton.addKeyListener(keyboardInput);
		add(signInButton);
	}

	private void loadLabyrinth() {
		Labyrinth labyrinth = new Labyrinth(LABYRINTH_WIDTH, LABYRINTH_HEIGHT);
		labyrinth.setBounds(LABYRINTH_POSITION);
		labyrinth.addKeyListener(new UserGameKeyboardInput());
		setLayout(null);
		add(labyrinth);
	}

}
