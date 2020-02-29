package BombermanClient.GameElements.Tiles;

import BombermanClient.GameConstants;

import java.awt.*;

public class GrassTile extends Tile {
	public GrassTile(String imagePath, Dimension size , Rectangle position) {
		super(imagePath, size, position);
	}

	public GrassTile(Dimension tileSize, Rectangle rectangle) {
		this(GameConstants.GRASS_TILE, tileSize, rectangle);
	}
}
