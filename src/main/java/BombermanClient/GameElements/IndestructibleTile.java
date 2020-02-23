package BombermanClient.GameElements;

import java.awt.*;

public class IndestructibleTile extends Tile implements Collide {

	public IndestructibleTile(String imagePath, Dimension size, Rectangle position) {
		super(imagePath, size, position);
	}

	@Override
	public boolean isCollidingWith(Rectangle rect) {
		return false;
	}

}
