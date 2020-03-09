package BomberGame.GameEntities.Tile.TileFactory;


import BomberGame.GameEntities.Tile.Tile;
import BomberGame.GameEntities.Tile.TileType;

import java.awt.*;
import java.util.HashMap;
import java.util.function.Consumer;

/**
 * https://github.com/iluwatar/java-design-patterns/tree/master/factory-kit
 *
 * Functional interface, an example of the factory-kit design pattern.
 * <br>Instance created locally gives an opportunity to strictly define
 * which objects types the instance of a factory will be able to create.
 * <br>Factory is a placeholder for {@link TileBuilder}s
 * with {@link TileFactory#create(TileType, String, Dimension, Rectangle)} method to initialize new objects.
 */
public interface TileFactory {

	/**
	 * Creates an instance of the given type.
	 *
	 * @param name representing enum of an object type to be created.
	 * @return new instance of a requested class implementing {@link Tile} interface.
	 */
	Tile create(TileType name, String path, Dimension size, Rectangle position);

	/**
	 * Creates factory - placeholder for specified {@link TileBuilder}s.
	 *
	 * @param consumer for the new builder to the factory.
	 * @return factory with specified {@link TileBuilder}s
	 */
	static TileFactory factory(Consumer<TileBuilder> consumer)
	{
		var map = new HashMap<TileType, TileFactoryFunction>();
		consumer.accept(map::put);
		return (type, path, size, position) -> map.get(type).execute(path, size, position);
	}

}
