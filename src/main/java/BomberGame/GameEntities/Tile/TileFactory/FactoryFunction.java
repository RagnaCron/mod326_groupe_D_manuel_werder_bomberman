package BomberGame.GameEntities.Tile.TileFactory;


import BomberGame.GameEntities.Tile.Tile;

import java.awt.*;

@FunctionalInterface
public interface FactoryFunction {
	Tile execute(String imagePath, Dimension size, Rectangle position);
}
