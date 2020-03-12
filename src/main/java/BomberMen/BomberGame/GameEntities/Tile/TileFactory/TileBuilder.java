package BomberMen.BomberGame.GameEntities.Tile.TileFactory;

import BomberMen.BomberGame.GameEntities.Tile.TileType;

/**
 * Functional interface that allows adding builder with name to the factory.
 */
@FunctionalInterface
public interface TileBuilder {
	void add(TileType tileType, TileFactoryFunction execute);
}


