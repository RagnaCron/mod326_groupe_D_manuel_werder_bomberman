package BombermanClient.GameElements;

import java.awt.*;

public class DestructibleTile extends Tile implements Collide {

	public DestructibleTile(Dimension size, Rectangle position) {
		super(DESTROYABLE_TILE, size, position);
	}

	@Override
	public boolean isCollidingWith(Rectangle rect) {
		return this.getBounds().intersects(rect);
	}

	public GrassTile destroyTile() {
		return new GrassTile(getSize(), getBounds());
	}

}

