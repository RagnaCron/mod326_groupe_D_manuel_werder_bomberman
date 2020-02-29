package BombermanClient.FuntionalInterfaces;

import BombermanClient.GameElements.Tiles.Tile;

import java.awt.*;

@FunctionalInterface
public interface FactoryFunction {
	Tile execute(String path, Dimension size, Rectangle position);
}
