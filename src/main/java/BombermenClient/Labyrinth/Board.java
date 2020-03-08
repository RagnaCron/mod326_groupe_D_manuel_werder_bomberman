package BombermenClient.Labyrinth;

import BombermenClient.GameElements.Tiles.*;
import BombermenClient.GameElements.Tiles.FactoryKit.TileFactory;
import BombermenClientServerInterfaces.Messaging.CustomJSONArray;

import java.awt.*;

public class Board implements TilesConstants {
	private Tile[][] board = new Tile[GRID_SIZE][GRID_SIZE];
	private TileFactory factory;

	public Board(CustomJSONArray labyrinthFile){
		factory = TileFactory.factory(builder -> {
			// Shows an Error that is not one! to test. first Compile and then run the game...
			builder.add(TileType.GRASS_TILE, GrassTile::new);
			builder.add(TileType.DESTRUCTIBLE_TILE, DestructibleTile::new);
			builder.add(TileType.INDESTRUCTIBLE_TILE_ONE, IndestructibleTile::new);
			builder.add(TileType.INDESTRUCTIBLE_TILE_TWO, IndestructibleTile::new);
		});
		populateNewBoard(labyrinthFile);
	}

	void populateNewBoard(CustomJSONArray labyrinthFile) {
		for (int row = 0; row < GRID_SIZE; row++) {
			for (int col = 0; col < GRID_SIZE; col++) {

				int y = (row * LABYRINTH_TILE_SIZE);
				int x = (col * LABYRINTH_TILE_SIZE);

				int tile = labyrinthFile.getJSONArray(row).getInt(col);
				switch (tile) {
					case 1:
						set(row, col, factory.create(
								TileType.INDESTRUCTIBLE_TILE_ONE,
								INDESTRUCTIBLE_TILE_1,
								TILE_DIMENSION,
								new Rectangle(x, y, LABYRINTH_TILE_SIZE, LABYRINTH_TILE_SIZE)));
						break;
					case 2:
						set(row, col, factory.create(
								TileType.INDESTRUCTIBLE_TILE_TWO,
								INDESTRUCTIBLE_TILE_2,
								TILE_DIMENSION,
								new Rectangle(x, y, LABYRINTH_TILE_SIZE, LABYRINTH_TILE_SIZE)));
						break;
					case 3:
						set(row, col, factory.create(
								TileType.DESTRUCTIBLE_TILE,
								DESTROYABLE_TILE,
								TILE_DIMENSION,
								new Rectangle(x, y, LABYRINTH_TILE_SIZE, LABYRINTH_TILE_SIZE)));
						break;
					default:
						set(row, col, factory.create(
								TileType.GRASS_TILE,
								GRASS_TILE,
								TILE_DIMENSION,
								new Rectangle(x, y, LABYRINTH_TILE_SIZE, LABYRINTH_TILE_SIZE)));
						break;
				}
			}
		}
	}

	public void set(int row, int column, Tile tile) {
		board[row][column] = tile;
	}

	public Tile get(int row, int column) {
		return board[row][column];
	}

	public static Board CreateInitialGrassBoard() {
		return new Board(new CustomJSONArray(InitialGrassBoard));

	}

	private final static String[][] InitialGrassBoard = {
			{"0","0","0","0","0","0","0","0","0","0","0","0","0","0","0","0"},
			{"0","0","0","0","0","0","0","0","0","0","0","0","0","0","0","0"},
			{"0","0","0","0","0","0","0","0","0","0","0","0","0","0","0","0"},
			{"0","0","0","0","0","0","0","0","0","0","0","0","0","0","0","0"},
			{"0","0","0","0","0","0","0","0","0","0","0","0","0","0","0","0"},
			{"0","0","0","0","0","0","0","0","0","0","0","0","0","0","0","0"},
			{"0","0","0","0","0","0","0","0","0","0","0","0","0","0","0","0"},
			{"0","0","0","0","0","0","0","0","0","0","0","0","0","0","0","0"},
			{"0","0","0","0","0","0","0","0","0","0","0","0","0","0","0","0"},
			{"0","0","0","0","0","0","0","0","0","0","0","0","0","0","0","0"},
			{"0","0","0","0","0","0","0","0","0","0","0","0","0","0","0","0"},
			{"0","0","0","0","0","0","0","0","0","0","0","0","0","0","0","0"},
			{"0","0","0","0","0","0","0","0","0","0","0","0","0","0","0","0"},
			{"0","0","0","0","0","0","0","0","0","0","0","0","0","0","0","0"},
			{"0","0","0","0","0","0","0","0","0","0","0","0","0","0","0","0"},
			{"0","0","0","0","0","0","0","0","0","0","0","0","0","0","0","0"},
	};
}
