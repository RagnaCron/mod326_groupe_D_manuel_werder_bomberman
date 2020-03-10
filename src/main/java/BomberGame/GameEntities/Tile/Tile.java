package BomberGame.GameEntities.Tile;

import BomberGame.GameEntities.Collide;
import BomberGame.GameEntities.Entity;

import java.awt.*;

public abstract class Tile extends Entity implements Collide {

	protected boolean canCollide = false;
	protected boolean isDestroyable = false;

	protected Tile(String pathToIcon, Dimension size, Rectangle position) {
		super(pathToIcon, size, position);
	}

	@Override
	public boolean isCollidingWith(Rectangle rect) {
		return canCollide && getBounds().intersects(rect);
	}

	public boolean isDestroyable() {
		return isDestroyable;
	}

	public abstract boolean isGrass();
}
