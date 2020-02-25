package BombermanClient;

import java.awt.*;

/**
 * Source: Daniel Senften, Modul 326
 */
public interface GameConstants {
	int GRID_SIZE = 16;
	int LABYRINTH_TILE_SIZE = 32;
	int FRAME_SIZE = 48;
	int SPACING = 1;
	int X_OFFSET = 0, Y_OFFSET = 128;

	int FRAME_WIDTH = X_OFFSET + (GRID_SIZE * FRAME_SIZE) + SPACING;
	int FRAME_HEIGHT = Y_OFFSET + (GRID_SIZE * FRAME_SIZE) + SPACING;
	Dimension BOMBERMAN_FRAME_SIZE = new Dimension(FRAME_WIDTH, FRAME_HEIGHT);

	int LABYRINTH_WIDTH = GRID_SIZE * LABYRINTH_TILE_SIZE;
	int LABYRINTH_HEIGHT = GRID_SIZE * LABYRINTH_TILE_SIZE;
	Rectangle LABYRINTH_POSITION = new Rectangle(
			(FRAME_WIDTH - LABYRINTH_WIDTH) / 2,
			(FRAME_HEIGHT - LABYRINTH_HEIGHT - Y_OFFSET) /2,
			LABYRINTH_WIDTH,
			LABYRINTH_HEIGHT
	);

	Dimension TILE_SIZE = new Dimension(LABYRINTH_TILE_SIZE, LABYRINTH_TILE_SIZE);
	String INDESTRUCTIBLE_TILE_1 = "src/main/resources/GameArt/Tiles/tile_undestroyable_1.png";
	String INDESTRUCTIBLE_TILE_2 = "src/main/resources/GameArt/Tiles/tile_undestroyable_2.png";
	String GRASS_TILE = "src/main/resources/GameArt/Tiles/tile_grass.png";
	String DESTROYABLE_TILE = "src/main/resources/GameArt/Tiles/tile_grass.png";

	Rectangle TEXT_INPUT_POSITION = new Rectangle(
			(FRAME_WIDTH) / 6,
			LABYRINTH_TILE_SIZE,
			LABYRINTH_WIDTH / 2 + 64,
			LABYRINTH_TILE_SIZE
	);
	Rectangle SING_IN_BUTTON_POSITION = new Rectangle(
			FRAME_WIDTH / 2 + Y_OFFSET,
			LABYRINTH_TILE_SIZE,
			LABYRINTH_WIDTH / 4,
			LABYRINTH_TILE_SIZE
	);
	Rectangle SERVER_LOGGING_TEXTAREA_POSITION = new Rectangle(
			FRAME_WIDTH / 6,
			FRAME_HEIGHT / 2 + LABYRINTH_HEIGHT / 2 - 32,
			LABYRINTH_WIDTH,
			Y_OFFSET + 64
	);
}
