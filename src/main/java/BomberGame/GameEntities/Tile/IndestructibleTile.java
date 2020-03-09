package BomberGame.GameEntities.Tile;

import BomberGame.GameEntities.Collide;

import java.awt.*;

public final class IndestructibleTile extends Tile implements Collide {

	public IndestructibleTile(String imagePath, Dimension size, Rectangle position) {
		super(imagePath, size, position);
		canCollide = true;
	}

}
