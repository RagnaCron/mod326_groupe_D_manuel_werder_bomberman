package Old.BombermenClient.GameElements.Tiles;

import Old.BombermenClient.GameElements.Collide;

import java.awt.*;

public class DestructibleTile extends Tile implements Collide {

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

