package BomberGame.GameEntities.Tile;

import BomberGame.GameEntities.Entity;

import java.awt.*;

public abstract class Tile extends Entity {

	protected Tile(String pathToIcon, Dimension size, Rectangle position) {
		super(pathToIcon, size, position);
	}

	@Override
	protected void paintComponent(Graphics g) {
		super.paintComponent(g);
		g.drawImage((Image) getIcon(), getX(), getY(), this);
	}
}
