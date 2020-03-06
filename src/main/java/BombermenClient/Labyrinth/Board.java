package BombermenClient.Labyrinth;

import BombermenClient.Bombermen.GameConstants;
import BombermenClient.GameElements.Tiles.*;
import BombermenClient.GameElements.Tiles.FactoryKit.TileFactory;

import java.awt.*;

public class Board implements GameConstants {
	private Tile[][] board = new Tile[GRID_SIZE][GRID_SIZE];
	private TileFactory factory;

	public Board(String[][] labyrinthFile){
		factory = TileFactory.factory(builder -> {
			// Shows an Error that is not one! to test. first Compile and then run the game...
			builder.add(TileType.GRASS_TILE, GrassTile::new);
			builder.add(TileType.DESTRUCTIBLE_TILE, DestructibleTile::new);
			builder.add(TileType.INDESTRUCTIBLE_TILE_ONE, IndestructibleTile::new);
			builder.add(TileType.INDESTRUCTIBLE_TILE_TWO, IndestructibleTile::new);
		});
		for (int row = 0; row < GRID_SIZE; row++) {
			for (int col = 0; col < GRID_SIZE; col++) {
				int y = (row * LABYRINTH_TILE_SIZE);
				int x = (col * LABYRINTH_TILE_SIZE);
				String tile = labyrinthFile[row][col];
				switch (tile) {
					case "1":
						add(row, col, factory.create(
								TileType.INDESTRUCTIBLE_TILE_ONE,
								INDESTRUCTIBLE_TILE_1,
								TILE_DIMENSION,
								new Rectangle(x, y, LABYRINTH_TILE_SIZE, LABYRINTH_TILE_SIZE)));
						break;
					case "2":
						add(row, col, factory.create(
								TileType.INDESTRUCTIBLE_TILE_TWO,
								INDESTRUCTIBLE_TILE_2,
								TILE_DIMENSION,
								new Rectangle(x, y, LABYRINTH_TILE_SIZE, LABYRINTH_TILE_SIZE)));
						break;
					case "3":
						add(row, col, factory.create(
								TileType.DESTRUCTIBLE_TILE,
								DESTROYABLE_TILE,
								TILE_DIMENSION,
								new Rectangle(x, y, LABYRINTH_TILE_SIZE, LABYRINTH_TILE_SIZE)));
						break;
					default:
						add(row, col, factory.create(
								TileType.GRASS_TILE,
								GRASS_TILE,
								TILE_DIMENSION,
								new Rectangle(x, y, LABYRINTH_TILE_SIZE, LABYRINTH_TILE_SIZE)));
						break;
				}
			}
		}
	}

	public void add(int row, int column, Tile tile) {
		board[row][column] = tile;
	}

	public Tile get(int row, int column) {
		return board[row][column];
	}

	public static Board CreateDefaultBoard() {
		return new Board(defaultBoard);
	}

	private final static String[][] defaultBoard = {
			{"1","2","2","2","2","2","2","2","2","2","2","2","2","2","2","1"},
			{"1","0","0","0","0","0","0","3","3","0","0","0","0","0","0","1"},
			{"1","0","2","0","2","0","2","3","3","2","0","2","0","2","0","1"},
			{"1","0","0","0","0","0","0","3","3","0","0","0","0","0","0","1"},
			{"1","0","2","0","2","0","2","3","3","2","0","2","0","2","0","1"},
			{"1","0","0","0","0","0","0","3","3","0","0","0","0","0","0","1"},
			{"1","0","2","0","2","0","2","3","3","2","0","2","0","2","0","1"},
			{"1","3","3","3","3","3","3","3","3","3","3","3","3","3","3","1"},
			{"1","3","3","3","3","3","3","3","3","3","3","3","3","3","3","1"},
			{"1","0","2","0","2","0","2","3","3","2","0","2","0","2","0","1"},
			{"1","0","0","0","0","0","0","3","3","0","0","0","0","0","0","1"},
			{"1","0","2","0","2","0","2","3","3","2","0","2","0","2","0","1"},
			{"1","0","0","0","0","0","0","3","3","0","0","0","0","0","0","1"},
			{"1","0","2","0","2","0","2","3","3","2","0","2","0","2","0","1"},
			{"1","0","0","0","0","0","0","3","3","0","0","0","0","0","0","1"},
			{"2","2","2","2","2","2","2","2","2","2","2","2","2","2","2","2"},
	};
}
