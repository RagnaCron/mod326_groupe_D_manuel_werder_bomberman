package BomberGame.GameEntities.Player;

import BomberGame.GameEntities.Bomb.Bomb;
import BomberGame.GameEntities.Collide;
import BomberGame.GameEntities.Entity;
import lombok.Getter;
import lombok.Setter;

import java.awt.*;

public final class Player extends Entity implements Collide {

	private boolean canCollide = true;
	@Getter @Setter
	private Direction facingDirection;

	private Image[] movingUp;
	private Image[] movingRight;
	private Image[] movingDown;
	private Image[] movingLeft;

	private int oldX;
	private int oldY;

	public Player(Dimension size, Rectangle position, PlayerStartPosition startPosition) {
		super(PlayerStartPosition.PlayerVersion(startPosition), size, position);
		this.facingDirection = PlayerStartPosition.FacingDirection(startPosition);
		loadPlayer(startPosition);
	}

	@Override
	public boolean isCollidingWith(Rectangle rect) {
		return canCollide && getBounds().intersects(rect);
	}

	public int getOldX() {
		return oldX;
	}

	public int getOldY() {
		return oldY;
	}



	public void move(Direction direction) {
		oldX = getX();
		oldY = getY();
		switch (direction) {
			case FACING_UP:
				facingDirection = Direction.FACING_UP;
				image = movingUp[0];
				setBounds(getX(), getY()-PLAYER_MOVING_VALUE, getWidth(), getHeight());
				break;
			case FACING_RIGHT:
				facingDirection = Direction.FACING_RIGHT;
				image = movingRight[0];
				setBounds(getX()+PLAYER_MOVING_VALUE, getY(), getWidth(), getHeight());
				break;
			case FACING_DOWN:
				facingDirection = Direction.FACING_DOWN;
				image = movingDown[0];
				setBounds(getX(), getY()+PLAYER_MOVING_VALUE, getWidth(), getHeight());
				break;
			case FACING_LEFT:
				facingDirection = Direction.FACING_LEFT;
				image = movingLeft[0];
				setBounds(getX()-PLAYER_MOVING_VALUE, getY(), getWidth(), getHeight());
				break;
		}
	}

	public Bomb dropBomb(int x, int y) {
		if (facingDirection == Direction.FACING_UP)
			return new Bomb(new Rectangle(x, y - BOMB_SIZE, BOMB_SIZE,BOMB_SIZE));
		else if (facingDirection == Direction.FACING_RIGHT)
			return new Bomb(new Rectangle(x + BOMB_SIZE, y, BOMB_SIZE,BOMB_SIZE));
		else if (facingDirection == Direction.FACING_DOWN)
			return new Bomb(new Rectangle(x, y + BOMB_SIZE, BOMB_SIZE,BOMB_SIZE));
		else
			return new Bomb(new Rectangle(x - BOMB_SIZE, y, BOMB_SIZE,BOMB_SIZE));
	}

	private void loadPlayer(PlayerStartPosition version) {
		switch (version) {
			case LEFT_UPPER_CORNER:
				movingUp = new Image[] {
						loadImage(GRAY_PLAYER_UP, PLAYER_SIZE, PLAYER_SIZE),
						loadImage(GRAY_PLAYER_UP_W_1, PLAYER_SIZE, PLAYER_SIZE),
						loadImage(GRAY_PLAYER_UP, PLAYER_SIZE, PLAYER_SIZE),
						loadImage(GRAY_PLAYER_UP_W_2, PLAYER_SIZE, PLAYER_SIZE)
				};
				movingRight = new Image[] {
						loadImage(GRAY_PLAYER_RIGHT, PLAYER_SIZE, PLAYER_SIZE),
						loadImage(GRAY_PLAYER_RIGHT_W_1, PLAYER_SIZE, PLAYER_SIZE),
						loadImage(GRAY_PLAYER_RIGHT_W_2, PLAYER_SIZE, PLAYER_SIZE),
						loadImage(GRAY_PLAYER_RIGHT_W_3, PLAYER_SIZE, PLAYER_SIZE)
				};
				movingDown = new Image[] {
						loadImage(GRAY_PLAYER_DOWN, PLAYER_SIZE, PLAYER_SIZE),
						loadImage(GRAY_PLAYER_DOWN_W_1, PLAYER_SIZE, PLAYER_SIZE),
						loadImage(GRAY_PLAYER_DOWN, PLAYER_SIZE, PLAYER_SIZE),
						loadImage(GRAY_PLAYER_DOWN_W_2, PLAYER_SIZE, PLAYER_SIZE)
				};
				movingLeft = new Image[] {
						loadImage(GRAY_PLAYER_LEFT, PLAYER_SIZE, PLAYER_SIZE),
						loadImage(GRAY_PLAYER_LEFT_W_1, PLAYER_SIZE, PLAYER_SIZE),
						loadImage(GRAY_PLAYER_LEFT_W_2, PLAYER_SIZE, PLAYER_SIZE),
						loadImage(GRAY_PLAYER_LEFT_W_3, PLAYER_SIZE, PLAYER_SIZE)
				};
				break;
			case RIGHT_UPPER_CORNER:
				movingUp = new Image[] {
						loadImage(GREEN_PLAYER_UP, PLAYER_SIZE, PLAYER_SIZE),
						loadImage(GREEN_PLAYER_UP_W_1, PLAYER_SIZE, PLAYER_SIZE),
						loadImage(GREEN_PLAYER_UP, PLAYER_SIZE, PLAYER_SIZE),
						loadImage(GREEN_PLAYER_UP_W_2, PLAYER_SIZE, PLAYER_SIZE)
				};
				movingRight = new Image[] {
						loadImage(GREEN_PLAYER_RIGHT, PLAYER_SIZE, PLAYER_SIZE),
						loadImage(GREEN_PLAYER_RIGHT_W_1, PLAYER_SIZE, PLAYER_SIZE),
						loadImage(GREEN_PLAYER_RIGHT_W_2, PLAYER_SIZE, PLAYER_SIZE),
						loadImage(GREEN_PLAYER_RIGHT_W_3, PLAYER_SIZE, PLAYER_SIZE)
				};
				movingDown = new Image[] {
						loadImage(GREEN_PLAYER_DOWN, PLAYER_SIZE, PLAYER_SIZE),
						loadImage(GREEN_PLAYER_DOWN_W_1, PLAYER_SIZE, PLAYER_SIZE),
						loadImage(GREEN_PLAYER_DOWN, PLAYER_SIZE, PLAYER_SIZE),
						loadImage(GREEN_PLAYER_DOWN_W_2, PLAYER_SIZE, PLAYER_SIZE)
				};
				movingLeft = new Image[] {
						loadImage(GREEN_PLAYER_LEFT, PLAYER_SIZE, PLAYER_SIZE),
						loadImage(GREEN_PLAYER_LEFT_W_1, PLAYER_SIZE, PLAYER_SIZE),
						loadImage(GREEN_PLAYER_LEFT_W_2, PLAYER_SIZE, PLAYER_SIZE),
						loadImage(GREEN_PLAYER_LEFT_W_3, PLAYER_SIZE, PLAYER_SIZE)
				};
				break;
			case RIGHT_BOTTOM_CORNER:
				movingUp = new Image[] {
						loadImage(PURPLE_PLAYER_UP, PLAYER_SIZE, PLAYER_SIZE),
						loadImage(PURPLE_PLAYER_UP_W_1, PLAYER_SIZE, PLAYER_SIZE),
						loadImage(PURPLE_PLAYER_UP, PLAYER_SIZE, PLAYER_SIZE),
						loadImage(PURPLE_PLAYER_UP_W_2, PLAYER_SIZE, PLAYER_SIZE)
				};
				movingRight = new Image[] {
						loadImage(PURPLE_PLAYER_RIGHT, PLAYER_SIZE, PLAYER_SIZE),
						loadImage(PURPLE_PLAYER_RIGHT_W_1, PLAYER_SIZE, PLAYER_SIZE),
						loadImage(PURPLE_PLAYER_RIGHT_W_2, PLAYER_SIZE, PLAYER_SIZE),
						loadImage(PURPLE_PLAYER_RIGHT_W_3, PLAYER_SIZE, PLAYER_SIZE)
				};
				movingDown = new Image[] {
						loadImage(PURPLE_PLAYER_DOWN, PLAYER_SIZE, PLAYER_SIZE),
						loadImage(PURPLE_PLAYER_DOWN_W_1, PLAYER_SIZE, PLAYER_SIZE),
						loadImage(PURPLE_PLAYER_DOWN, PLAYER_SIZE, PLAYER_SIZE),
						loadImage(PURPLE_PLAYER_DOWN_W_2, PLAYER_SIZE, PLAYER_SIZE)
				};
				movingLeft = new Image[] {
						loadImage(PURPLE_PLAYER_LEFT, PLAYER_SIZE, PLAYER_SIZE),
						loadImage(PURPLE_PLAYER_LEFT_W_1, PLAYER_SIZE, PLAYER_SIZE),
						loadImage(PURPLE_PLAYER_LEFT_W_2, PLAYER_SIZE, PLAYER_SIZE),
						loadImage(PURPLE_PLAYER_LEFT_W_3, PLAYER_SIZE, PLAYER_SIZE)
				};
				break;
			case LEFT_BOTTOM_CORNER:
				movingUp = new Image[] {
						loadImage(YELLOW_PLAYER_UP, PLAYER_SIZE, PLAYER_SIZE),
						loadImage(YELLOW_PLAYER_UP_W_1, PLAYER_SIZE, PLAYER_SIZE),
						loadImage(YELLOW_PLAYER_UP, PLAYER_SIZE, PLAYER_SIZE),
						loadImage(YELLOW_PLAYER_UP_W_2, PLAYER_SIZE, PLAYER_SIZE)
				};
				movingRight = new Image[] {
						loadImage(YELLOW_PLAYER_RIGHT, PLAYER_SIZE, PLAYER_SIZE),
						loadImage(YELLOW_PLAYER_RIGHT_W_1, PLAYER_SIZE, PLAYER_SIZE),
						loadImage(YELLOW_PLAYER_RIGHT_W_2, PLAYER_SIZE, PLAYER_SIZE),
						loadImage(YELLOW_PLAYER_RIGHT_W_3, PLAYER_SIZE, PLAYER_SIZE)
				};
				movingDown = new Image[] {
						loadImage(YELLOW_PLAYER_DOWN, PLAYER_SIZE, PLAYER_SIZE),
						loadImage(YELLOW_PLAYER_DOWN_W_1, PLAYER_SIZE, PLAYER_SIZE),
						loadImage(YELLOW_PLAYER_DOWN, PLAYER_SIZE, PLAYER_SIZE),
						loadImage(YELLOW_PLAYER_DOWN_W_2, PLAYER_SIZE, PLAYER_SIZE)
				};
				movingLeft = new Image[] {
						loadImage(YELLOW_PLAYER_LEFT, PLAYER_SIZE, PLAYER_SIZE),
						loadImage(YELLOW_PLAYER_LEFT_W_1, PLAYER_SIZE, PLAYER_SIZE),
						loadImage(YELLOW_PLAYER_LEFT_W_2, PLAYER_SIZE, PLAYER_SIZE),
						loadImage(YELLOW_PLAYER_LEFT_W_3, PLAYER_SIZE, PLAYER_SIZE)
				};
				break;
		}
	}

}
