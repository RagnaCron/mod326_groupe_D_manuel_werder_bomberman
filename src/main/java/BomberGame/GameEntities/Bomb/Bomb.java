package BomberGame.GameEntities.Bomb;

import BomberGame.GameEntities.Entity;

import java.awt.*;

public final class Bomb extends Entity {

	private static long bombID = 0;
	public synchronized static long getBombID() {
		return bombID++;
	}

	private Image[] bombsAnimation = new Image[3];

	public Bomb(Rectangle position) {
		super(BOMB_1, BOMB_DIMENSION, position);
		loadBombs();
	}

	private void loadBombs() {
		bombsAnimation[0] = image;
		bombsAnimation[1] = loadImage(BOMB_2, getWidth(), getHeight());
		bombsAnimation[2] = loadImage(BOMB_3, getWidth(), getHeight());
	}

}
