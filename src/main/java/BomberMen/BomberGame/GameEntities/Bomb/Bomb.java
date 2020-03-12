package BomberMen.BomberGame.GameEntities.Bomb;

import BomberMen.BomberGame.GameEntities.Collide;
import BomberMen.BomberGame.GameEntities.Entity;
import BomberMen.BomberGame.GameEntities.Player.Player;
import BomberMen.BomberGame.GameEntities.Tile.Tile;

import java.awt.*;

import static java.lang.Thread.sleep;

public final class Bomb extends Entity implements Collide {

	private static long bombID = 0;
	public synchronized static long getBombID() {
		return bombID++;
	}

	private boolean canCollide = true;
	private int explosionRadius = 2;
	private boolean hasExploded = false;

	private Image[] bombsAnimation = new Image[3];

	public Bomb(Rectangle position) {
		super(BOMB_1, BOMB_DIMENSION, position);
		loadBombs();
		(new Thread(() -> {
			try {
				sleep(2000);
				hasExploded = true;
			} catch (Exception ignored) {}
		})).start();
	}

	private void loadBombs() {
		bombsAnimation[0] = image;
		bombsAnimation[1] = loadImage(BOMB_2, getWidth(), getHeight());
		bombsAnimation[2] = loadImage(BOMB_3, getWidth(), getHeight());
	}

	@Override
	public boolean isCollidingWith(Rectangle rect) {
		return canCollide && getBounds().intersects(rect);
	}

	public boolean isExploded() {
		return hasExploded;
	}

	public boolean checkForDestroyableTile(Tile tile) {
		return tile.isDestroyable() && checkExplosionVicinity(tile.getPositionX(), tile.getPositionY());
	}

	public boolean checkForDestroyablePlayer(Player player) {
		return player.isALife() && checkExplosionVicinity(player.getPositionX(), player.getPositionY());
	}

	private boolean checkExplosionVicinity(int x, int y) {
		if (getPositionX() == x || getPositionY() == y)
			return true;
		else if (getPositionX() + 1 == x || getPositionY() + 1 == y)
			return true;
		else if (getPositionX() - 1 == x || getPositionY() - 1 == y)
			return true;
		else if (getPositionX() + 2 == x || getPositionY() + 2 == y)
			return true;
		else return getPositionX() - 2 == x || getPositionY() - 2 == y;
	}
}
