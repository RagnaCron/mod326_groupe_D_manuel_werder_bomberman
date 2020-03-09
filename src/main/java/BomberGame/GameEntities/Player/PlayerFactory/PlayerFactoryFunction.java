package BomberGame.GameEntities.Player.PlayerFactory;


import BomberGame.GameEntities.Player.Player;
import BomberGame.GameEntities.Player.PlayerStartPosition;

import java.awt.*;

@FunctionalInterface
public interface PlayerFactoryFunction {
	Player execute(Dimension size, Rectangle position, PlayerStartPosition startPosition);
}
