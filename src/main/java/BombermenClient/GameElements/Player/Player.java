package BombermenClient.GameElements.Player;

import BombermenClient.GameElements.Collide;
import BombermenClient.GameElements.GameElement;

import java.awt.*;

public class Player extends GameElement implements PlayerConstants, Collide {

	private Direction facingDirection;
	private Moving moving = null;

	public Player(Direction facingDirection, Dimension size, Rectangle position) {
		this.facingDirection = facingDirection;
		setSize(size);
		setBounds(position);
	}
	public Player(Direction facingDirection, Rectangle position) {
		this(facingDirection, PLAYER_DIMENSION , position);
	}



	@Override
	public boolean isCollidingWith(Rectangle rect) {
		return false;
	}

	private enum Moving {
		MOVING_UP,
		MOVING_DOWN,
		MOVING_RIGHT,
		MOVING_LEFT,
	}

	private enum Direction {
		FACING_UP,
		FACING_DOWN,
		FACING_RIGHT,
		FACING_LEFT
	}
}
