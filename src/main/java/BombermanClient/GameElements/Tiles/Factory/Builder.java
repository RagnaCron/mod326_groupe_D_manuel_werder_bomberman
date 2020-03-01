package BombermanClient.GameElements.Tiles.Factory;

import BombermanClient.GameElements.Tiles.TileType;

/**
 * Functional interface that allows adding builder with name to the factory.
 */
public interface Builder {
	void add(TileType tileType, FactoryFunction execute);
}

