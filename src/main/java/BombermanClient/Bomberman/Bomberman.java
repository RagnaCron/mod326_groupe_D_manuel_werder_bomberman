package BombermanClient.Bomberman;

import BombermanClient.GameConstants;
import BombermanClient.Labyrinth.Labyrinth;

import javax.swing.*;

public class Bomberman extends JFrame implements GameConstants {

	public Bomberman() {
		super("Bomberman");
		setResizable(false);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setPreferredSize(BOMBERMAN_FRAME_SIZE);

		// Set up the content pane.
		Labyrinth labyrinth = new Labyrinth(LABYRINTH_WIDTH, LABYRINTH_HEIGHT);
		labyrinth.setBounds(LABYRINTH_POSITION);
		setLayout(null);
		add(labyrinth);

		pack();
		setVisible(true);
	}

}
