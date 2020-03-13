package BomberMen.BomberGame.GameEntities.Player.PlayerFactory;


import BomberMen.BomberGame.GameEntities.Player.Player;
import BomberMen.BomberGame.GameEntities.Player.PlayerStartPosition;

import java.awt.*;

@FunctionalInterface
public interface PlayerFactoryFunction {
	Player execute(Dimension size, Rectangle position, PlayerStartPosition startPosition);
}
