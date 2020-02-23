package BombermanClient.Labyrinth;

import BombermanClient.GameConstants;
import BombermanClient.GameElements.GameElement;

import java.awt.*;

public class Labyrinth extends GameElement implements GameConstants {

	public Labyrinth(final int width, final int height) {
		Dimension size = new Dimension(width, height);
		setSize(size);
		setPreferredSize(size);
		setMaximumSize(size);
		setMaximumSize(size);
	}

	@Override
	protected void paintComponent(Graphics g) {
		super.paintComponent(g);
		g.setColor(Color.GREEN);
		g.fillRect(0,0, this.getWidth(), this.getHeight());

		for (int row = 0; row < GRID_SIZE; row++) {
			for (int col = 0; col < GRID_SIZE; col++) {

				int y = (row * LABYRINTH_TILE_SIZE) + SPACING;
				int x = (col * LABYRINTH_TILE_SIZE) + SPACING;

				g.setColor(Color.LIGHT_GRAY);
				g.fillRect(x, y, LABYRINTH_TILE_SIZE - SPACING, LABYRINTH_TILE_SIZE - SPACING);
			}
		}
	}
}
