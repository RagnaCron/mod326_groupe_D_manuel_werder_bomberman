package BombermanClient.GameElements.Tiles;

import BombermanClient.GameConstants;
import BombermanClient.GameElements.Collide;

import java.awt.*;

public class DestructibleTile extends Tile implements Collide {

	public DestructibleTile(String imagePath, Dimension size, Rectangle position) {
		super(imagePath, size, position);
	}

	public GrassTile destroyTile() {
		return new GrassTile(GameConstants.GRASS_TILE, getSize(), getBounds());
	}

	@Override
	public boolean isCollidingWith(Rectangle rect) {
		return this.getBounds().intersects(rect);
	}



}

