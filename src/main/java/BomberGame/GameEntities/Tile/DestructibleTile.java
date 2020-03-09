package BomberGame.GameEntities.Tile;

import BomberGame.GameEntities.Collide;

import java.awt.*;

public final class DestructibleTile extends Tile implements Collide {

	public DestructibleTile(String imagePath, Dimension size, Rectangle position) {
		super(imagePath, size, position);
	}

	public GrassTile destroyTile() {
		return new GrassTile(GRASS_TILE, getSize(), getBounds());
	}

	@Override
	public boolean isCollidingWith(Rectangle rect) {
		return this.getBounds().intersects(rect);
	}

}
