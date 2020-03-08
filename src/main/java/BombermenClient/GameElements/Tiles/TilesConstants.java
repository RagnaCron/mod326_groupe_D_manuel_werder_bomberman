package BombermenClient.GameElements.Tiles;

import BombermenClient.Bombermen.GameConstants;

import java.awt.*;

public interface TilesConstants extends GameConstants {
	Dimension TILE_DIMENSION = new Dimension(LABYRINTH_TILE_SIZE, LABYRINTH_TILE_SIZE);
	String INDESTRUCTIBLE_TILE_1 = "src/main/resources/GameArt/Tiles/tile_indestructible_1.png";
	String INDESTRUCTIBLE_TILE_2 = "src/main/resources/GameArt/Tiles/tile_indestructible_2.png";
	String GRASS_TILE = "src/main/resources/GameArt/Tiles/tile_grass.png";
	String DESTROYABLE_TILE = "src/main/resources/GameArt/Tiles/tile_destroyable.png";
	String EMPTY_TILE = "src/main/resources/GameArt/Tiles/tile_empty.png";

}
