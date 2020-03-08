package BombermenClient.Labyrinth;

import BombermenClient.Bombermen.GameConstants;
import BombermenClient.GameElements.Player.Player;
import BombermenClient.GameElements.Player.Player.Direction;
import BombermenClient.GameElements.Player.Player.PlayerVersion;
import BombermenClientServerInterfaces.Messaging.CustomJSONArray;

import javax.swing.*;
import java.awt.*;

public final class Labyrinth extends JPanel implements GameConstants {

	private Board labyrinth = Board.CreateInitialGrassBoard();

	private Player grayPlayer = new Player(PlayerVersion.GRAY, Direction.FACING_RIGHT, Player.RIGHT_UPPER_CORNER_POSITION);
//	private Player greenPlayer = new Player();
//	private Player purplePlayer = new Player();
//	private Player yellowPlayer = new Player();

	public Labyrinth(final int width, final int height) {
		Dimension size = new Dimension(width, height);
		setSize(size);
		setPreferredSize(size);
		setMaximumSize(size);
		setMaximumSize(size);

//		addKeyListener(new BombermenGameKeyboardInput());

//		populateLabyrinth();
	}

	public void loadPlayingBoard(CustomJSONArray array) {
		labyrinth.populateNewBoard(array);
	}

	                             @Override
	protected void paintComponent(Graphics g) {
		super.paintComponent(g);
		paintBoard(g);
//		g.drawImage(grayPlayer.getImage(), grayPlayer.getX(), grayPlayer.getY(), this);
	}

	private void paintBoard(Graphics g) {
		for (int row = 0; row < GRID_SIZE; row++) {
			for (int col = 0; col < GRID_SIZE; col++) {
				int y = (row * LABYRINTH_TILE_SIZE);
				int x= (col * LABYRINTH_TILE_SIZE);
				g.drawImage(labyrinth.get(row, col).getImage(), x, y, this);
			}
		}
	}


}
