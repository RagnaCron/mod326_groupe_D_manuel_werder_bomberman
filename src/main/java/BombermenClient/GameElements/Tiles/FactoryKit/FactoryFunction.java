package BombermenClient.GameElements.Tiles.FactoryKit;

import BombermenClient.GameElements.Tiles.Tile;

import java.awt.*;

@FunctionalInterface
public interface FactoryFunction {
	Tile execute(String imagePath, Dimension size, Rectangle position);
}
