package BomberMen.BomberGame.GameEntities.Player;

import BomberMen.BomberGame.GameEntities.Bomb.Bomb;
import BomberMen.BomberGame.GameEntities.Collide;
import BomberMen.BomberGame.GameEntities.Entity;
import lombok.Getter;
import lombok.Setter;

import java.awt.*;

import static java.lang.Thread.sleep;

public final class Player extends Entity implements Collide {

	@Getter @Setter
	private boolean isALife = true;
	private boolean canCollide = true;
//	@Getter @Setter
	private Direction facingDirection;

	private Image[] movingUp;
	private Image[] movingRight;
	private Image[] movingDown;
	private Image[] movingLeft;

	private Thread animation;

	@Getter
	private int oldX;
	@Getter
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

	public void move(Direction direction) {
		oldX = getX();
		oldY = getY();
//		System.err.println("[x: " + getPositionX() + ", y: " + getPositionY() + "]");
		if (animation != null && !(facingDirection == direction))
			animation.interrupt();
		switch (direction) {
			case FACING_UP:
				facingDirection = Direction.FACING_UP;
//				moveAnimation(getBounds(), movingUp);
				image = movingUp[0];
				setBounds(getX(), getY()-PLAYER_MOVING_VALUE, getWidth(), getHeight());
				break;
			case FACING_RIGHT:
				facingDirection = Direction.FACING_RIGHT;
//				moveAnimation(getBounds(), movingRight);
				image = movingRight[0];
				setBounds(getX()+PLAYER_MOVING_VALUE, getY(), getWidth(), getHeight());
				break;
			case FACING_DOWN:
				facingDirection = Direction.FACING_DOWN;
//				moveAnimation(getBounds(), movingDown);
				image = movingDown[0];
				setBounds(getX(), getY()+PLAYER_MOVING_VALUE, getWidth(), getHeight());
				break;
			case FACING_LEFT:
				facingDirection = Direction.FACING_LEFT;
//				moveAnimation(getBounds(), movingLeft);
				image = movingLeft[0];
				setBounds(getX()-PLAYER_MOVING_VALUE, getY(), getWidth(), getHeight());
				break;
		}
	}

	private void moveAnimation(Rectangle rect, Image[] images) {
		int index = 0;
		while (true) {
			display(facingDirection, images[index], rect);
			index = (index + 1) % movingUp.length;
		}

	}

	private void display(Direction direction, Image image, Rectangle rect) {
		animation = new Thread(() -> {
			this.image = image;
			setBounds(rect);
			repaint(rect);
			try {
				sleep(1);
			} catch (InterruptedException ignored) {
				setInitialDirection(direction);
			}
		});
		animation.start();
	}

	public Bomb dropBomb(int x, int y) {
		if (facingDirection == Direction.FACING_UP)
			return new Bomb(new Rectangle(x, y , BOMB_SIZE,BOMB_SIZE));
		else if (facingDirection == Direction.FACING_RIGHT)
			return new Bomb(new Rectangle(x, y, BOMB_SIZE,BOMB_SIZE));
		else if (facingDirection == Direction.FACING_DOWN)
			return new Bomb(new Rectangle(x, y, BOMB_SIZE,BOMB_SIZE));
		else
			return new Bomb(new Rectangle(x, y, BOMB_SIZE,BOMB_SIZE));
	}

	private void setInitialDirection(Direction direction) {
		switch (direction) {
			case FACING_UP:
				image = movingUp[0];
				break;
			case FACING_RIGHT:
				image = movingRight[0];
				break;
			case FACING_DOWN:
				image = movingDown[0];
				break;
			case FACING_LEFT:
				image = movingLeft[0];
				break;
		}
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
