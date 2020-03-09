package Old.BombermenClient.GameElements.Tiles.FactoryKit;

import Old.BombermenClient.GameElements.Tiles.Tile;

import java.awt.*;

@FunctionalInterface
public interface FactoryFunction {
	Tile execute(String imagePath, Dimension size, Rectangle position);
}
