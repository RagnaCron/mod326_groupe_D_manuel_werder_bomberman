package BombermanClient.Labyrinth;

import BombermanClient.GameConstants;
import BombermanClient.GameElements.GameElement;

import java.awt.*;
import java.io.IOException;
import java.util.Arrays;

public class Labyrinth extends GameElement implements GameConstants {

	private Image[][] labyrinth = new Image[GRID_SIZE][GRID_SIZE];

	public Labyrinth(final int width, final int height) {
		Dimension size = new Dimension(width, height);
		setSize(size);
		setPreferredSize(size);
		setMaximumSize(size);
		setMaximumSize(size);

		populateLabyrinth();
	}

	// todo: refactor to use Tiles......
	private void populateLabyrinth() {
		for (int row = 0; row < GRID_SIZE; row++){
			for (int col = 0; col < GRID_SIZE; col++) {
				try {
					if ((col == 0 && row != GRID_SIZE - 1) || (col == GRID_SIZE - 1 && row != GRID_SIZE - 1)) {
						labyrinth[row][col] = loadImage(UNDESTROYABLE_TILE_1)
								.getScaledInstance(LABYRINTH_TILE_SIZE, LABYRINTH_TILE_SIZE, 0);
					} if ((row == 0 && col != 0 && col < GRID_SIZE - 1) || row == GRID_SIZE -1) {
						labyrinth[row][col] = loadImage(UNDESTROYABLE_TILE_2)
								.getScaledInstance(LABYRINTH_TILE_SIZE, LABYRINTH_TILE_SIZE, 0);
					}
				} catch (IOException e) {
					System.out.println(Arrays.toString(e.getStackTrace()));
				}

			}
		}
	}

	@Override
	protected void paintComponent(Graphics g) {
		super.paintComponent(g);
		g.setColor(Color.RED);
		g.fillRect(0,0, this.getWidth(), this.getHeight());
		for (int row = 0; row < GRID_SIZE; row++) {
			for (int col = 0; col < GRID_SIZE; col++) {

				int y = (row * LABYRINTH_TILE_SIZE);
				int x = (col * LABYRINTH_TILE_SIZE);

				g.drawImage(labyrinth[row][col], x, y, this);

			}
		}
	}
}
