package BombermenClient.GameElements.Tiles.Factory;

import BombermenClient.GameElements.Tiles.Tile;

import java.awt.*;

@FunctionalInterface
public interface FactoryFunction {
	Tile execute(String imagePath, Dimension size, Rectangle position);
}
