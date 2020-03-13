package Old.BombermenClient.GameElements.Tiles.FactoryKit;

import Old.BombermenClient.GameElements.Tiles.TileType;

/**
 * Functional interface that allows adding builder with name to the factory.
 */
@FunctionalInterface
public interface Builder {
	void add(TileType tileType, FactoryFunction execute);
}


