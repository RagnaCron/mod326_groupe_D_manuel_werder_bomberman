package BomberMen.BomberGame.GameEntities.Player.PlayerFactory;

import BomberMen.BomberGame.GameEntities.Player.Player;
import BomberMen.BomberGame.GameEntities.Player.PlayerStartPosition;

import java.awt.*;
import java.util.HashMap;
import java.util.function.Consumer;

/**
 * https://github.com/iluwatar/java-design-patterns/tree/master/factory-kit
 *
 * Functional interface, an example of the factory-kit design pattern.
 * <br>Instance created locally gives an opportunity to strictly define
 * which objects types the instance of a factory will be able to create.
 * <br>Factory is a placeholder for {@link PlayerBuilder}s
 * with {@link PlayerFactory#create(PlayerStartPosition, Dimension, Rectangle, PlayerStartPosition)} method to initialize new objects.
 */
public interface PlayerFactory {

	/**
	 * Creates an instance of the given type.
	 *
	 * @param version representing enum of an object type to be created.
	 * @return new instance of a requested class implementing {@link Player} interface.
	 */
	Player create(PlayerStartPosition version, Dimension size, Rectangle position, PlayerStartPosition startPosition);

	/**
	 * Creates factory - placeholder for specified {@link PlayerBuilder}s.
	 *
	 * @param consumer for the new builder to the factory.
	 * @return factory with specified {@link PlayerBuilder}s
	 */
	static PlayerFactory factory(Consumer<PlayerBuilder> consumer)
	{
		var map = new HashMap<PlayerStartPosition, PlayerFactoryFunction>();
		consumer.accept(map::put);
		return (type, size, position, startPosition) -> map.get(type).execute(size, position, startPosition);
	}

}
