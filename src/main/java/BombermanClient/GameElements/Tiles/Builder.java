package BombermanClient.GameElements.Tiles;

import FuntionalInterfaces.*;

/**
 * Functional interface that allows adding builder with name to the factory.
 */
public interface Builder {
	void add(TileType tileType, FactoryFunction execute);
}


