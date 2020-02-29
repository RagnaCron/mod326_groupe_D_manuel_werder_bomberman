package BombermanClient.GameElements.Tiles;


import BombermanClient.FuntionalInterfaces.FactoryFunction;

import java.awt.*;
import java.util.HashMap;
import java.util.function.Consumer;

/**
 * Functional interface, an example of the factory-kit design pattern.
 * <br>Instance created locally gives an opportunity to strictly define
 * which objects types the instance of a factory will be able to create.
 * <br>Factory is a placeholder for {@link Builder}s
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
	 * Creates factory - placeholder for specified {@link Builder}s.
	 *
	 * @param consumer for the new builder to the factory.
	 * @return factory with specified {@link Builder}s
	 */
	static TileFactory factory(Consumer<Builder> consumer)
	{
		HashMap<TileType, FactoryFunction> map = new HashMap<>();
		consumer.accept(map::put);
		return (name, path, size, position) -> map.get(name).execute(path, size, position);
	}

}
