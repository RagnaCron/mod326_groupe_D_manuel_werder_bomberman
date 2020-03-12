package BomberMen.BomberGame.GameEntities.Tile;

import java.awt.*;

public final class GrassTile extends Tile {

	public GrassTile(String pathToIcon, Dimension size, Rectangle position) {
		super(pathToIcon, size, position);
	}

	@Override
	public boolean isGrass() {
		return true;
	}

}
