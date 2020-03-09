package BomberGame.GameEntities.Player.PlayerFactory;

import BomberGame.GameEntities.Player.PlayerStartPosition;

/**
 * Functional interface that allows adding builder with name to the factory.
 */
@FunctionalInterface
public interface PlayerBuilder {
	void add(PlayerStartPosition playerVersion, PlayerFactoryFunction execute);
}


