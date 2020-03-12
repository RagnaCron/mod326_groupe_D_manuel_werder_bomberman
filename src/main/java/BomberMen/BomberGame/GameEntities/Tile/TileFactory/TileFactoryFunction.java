package BomberMen.BomberGame.GameEntities.Tile.TileFactory;


import BomberMen.BomberGame.GameEntities.Tile.Tile;

import java.awt.*;

@FunctionalInterface
public interface TileFactoryFunction {
	Tile execute(String imagePath, Dimension size, Rectangle position);
}
