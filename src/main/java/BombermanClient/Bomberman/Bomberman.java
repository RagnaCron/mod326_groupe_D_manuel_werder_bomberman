package BombermanClient.Bomberman;

import BombermanClient.GameConstants;
import BombermanClient.Labyrinth.Labyrinth;

import javax.swing.*;

// zetcode.com/tutorials/javagamestutorial/movingsprites/

public class Bomberman extends JFrame implements GameConstants {

	public Bomberman() {
		super("Bomberman");
		setResizable(false);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setPreferredSize(BOMBERMAN_FRAME_SIZE);

		setLabyrinth();

		pack();
		setVisible(true);
	}

	private void setLabyrinth() {
		Labyrinth labyrinth = new Labyrinth(LABYRINTH_WIDTH, LABYRINTH_HEIGHT);
		labyrinth.setBounds(LABYRINTH_POSITION);
		setLayout(null);
		add(labyrinth);
	}

}
