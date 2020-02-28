package BombermanClient.GameElements.Tiles;

import BombermanClient.GameConstants;
import BombermanClient.GameElements.Collide;

import java.awt.*;

public class DestructibleTile extends Tile implements Collide {

	public DestructibleTile(Dimension size, Rectangle position) {
		super(GameConstants.DESTROYABLE_TILE, size, position);
	}

	@Override
	public boolean isCollidingWith(Rectangle rect) {
		return this.getBounds().intersects(rect);
	}

	public GrassTile destroyTile() {
		return new GrassTile(getSize(), getBounds());
	}

}

