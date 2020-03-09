package BomberGame.GameEntities.Tile.TileFactory;

import BomberGame.GameEntities.Tile.TileType;

/**
 * Functional interface that allows adding builder with name to the factory.
 */
@FunctionalInterface
public interface Builder {
	void add(TileType tileType, FactoryFunction execute);
}


