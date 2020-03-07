package BombermenClient.GameElements.Player;

import BombermenClient.GameElements.Collide;
import BombermenClient.GameElements.GameElement;

import javax.swing.*;
import java.awt.*;

import static java.lang.Thread.sleep;

public final class Player extends GameElement implements PlayerConstants, Collide {

	private Direction facingDirection;
	private Moving moving = null;
	private final PlayerVersion version;

	private ImageIcon[] movingUp;
	private ImageIcon[] movingRight;
	private ImageIcon[] movingDown;
	private ImageIcon[] movingLeft;

	private Thread animation;

	public Player(PlayerVersion version, Direction facingDirection, Dimension size, Rectangle position) {
		this.version = version;
		this.facingDirection = facingDirection;

		setSize(size);
		setBounds(position);

		loadPlayer(version);
		setInitialDirection(facingDirection);
	}

	public Player(PlayerVersion version, Direction facingDirection, Rectangle position) {
		this(version, facingDirection, PLAYER_DIMENSION , position);
	}

	public void moving(int direction, int x, int y) {
		Rectangle rect = new Rectangle(x,y, getWidth(), getHeight());
		facingDirection = facingDirection.faceDirection(direction);
		switch (facingDirection) {
			case FACING_UP:
				moving = Moving.MOVING_UP;
				break;
			case FACING_RIGHT:
				moving = Moving.MOVING_RIGHT;
				break;
			case FACING_DOWN:
				moving = Moving.MOVING_DOWN;
				break;
			case FACING_LEFT:
				moving = Moving.MOVING_LEFT;
				break;
		}
	}

	private void moveUpAnimation(Rectangle rect) {
		int index = 0;
		while (true) {
			display(facingDirection, movingUp[index], rect);
			index = (index + 1) % movingUp.length;
		}

	}

	private void display(Direction direction, ImageIcon image, Rectangle rect) {
		animation = new Thread(() -> {
			setIcon(image);
			setBounds(rect);
			repaint(rect);
			try {
				sleep(0, 300);
			} catch (InterruptedException ignored) {
				setInitialDirection(direction);
			}
		});
		animation.start();

	}

//	public void setFacingDirection(int direction) {
//		facingDirection = facingDirection.faceDirection(direction);
//	}

	@Override
	public boolean isCollidingWith(Rectangle rect) {
		return getBounds().intersects(rect);
	}

	private void setInitialDirection(Direction direction) {
		switch (direction) {
			case FACING_UP:
				setIcon(movingUp[0]);
				break;
			case FACING_RIGHT:
				setIcon(movingRight[0]);
				break;
			case FACING_DOWN:
				setIcon(movingDown[0]);
				break;
			case FACING_LEFT:
				setIcon(movingLeft[0]);
				break;
		}
	}

	private void loadPlayer(PlayerVersion version) {
		switch (version) {
			case GRAY:
				movingUp = new ImageIcon[] {
						new ImageIcon(loadImage(GRAY_PLAYER_UP).getScaledInstance(PLAYER_SIZE, PLAYER_SIZE, 0)),
						new ImageIcon(loadImage(GRAY_PLAYER_UP_W_1).getScaledInstance(PLAYER_SIZE, PLAYER_SIZE, 0)),
						new ImageIcon(loadImage(GRAY_PLAYER_UP).getScaledInstance(PLAYER_SIZE, PLAYER_SIZE, 0)),
						new ImageIcon(loadImage(GRAY_PLAYER_UP_W_2).getScaledInstance(PLAYER_SIZE, PLAYER_SIZE, 0))
				};
				movingRight = new ImageIcon[] {
						new ImageIcon(loadImage(GRAY_PLAYER_RIGHT).getScaledInstance(PLAYER_SIZE, PLAYER_SIZE, 0)),
						new ImageIcon(loadImage(GRAY_PLAYER_RIGHT_W_1).getScaledInstance(PLAYER_SIZE, PLAYER_SIZE, 0)),
						new ImageIcon(loadImage(GRAY_PLAYER_RIGHT_W_2).getScaledInstance(PLAYER_SIZE, PLAYER_SIZE, 0)),
						new ImageIcon(loadImage(GRAY_PLAYER_RIGHT_W_3).getScaledInstance(PLAYER_SIZE, PLAYER_SIZE, 0))
				};
				movingDown = new ImageIcon[] {
						new ImageIcon(loadImage(GRAY_PLAYER_DOWN).getScaledInstance(PLAYER_SIZE, PLAYER_SIZE, 0)),
						new ImageIcon(loadImage(GRAY_PLAYER_DOWN_W_1).getScaledInstance(PLAYER_SIZE, PLAYER_SIZE, 0)),
						new ImageIcon(loadImage(GRAY_PLAYER_DOWN).getScaledInstance(PLAYER_SIZE, PLAYER_SIZE, 0)),
						new ImageIcon(loadImage(GRAY_PLAYER_DOWN_W_2).getScaledInstance(PLAYER_SIZE, PLAYER_SIZE, 0))
				};
				movingLeft = new ImageIcon[] {
						new ImageIcon(loadImage(GRAY_PLAYER_LEFT).getScaledInstance(PLAYER_SIZE, PLAYER_SIZE, 0)),
						new ImageIcon(loadImage(GRAY_PLAYER_LEFT_W_1).getScaledInstance(PLAYER_SIZE, PLAYER_SIZE, 0)),
						new ImageIcon(loadImage(GRAY_PLAYER_LEFT_W_2).getScaledInstance(PLAYER_SIZE, PLAYER_SIZE, 0)),
						new ImageIcon(loadImage(GRAY_PLAYER_LEFT_W_3).getScaledInstance(PLAYER_SIZE, PLAYER_SIZE, 0))
				};
				break;
			case GREEN:
				movingUp = new ImageIcon[] {
						new ImageIcon(loadImage(GREEN_PLAYER_UP).getScaledInstance(PLAYER_SIZE, PLAYER_SIZE, 0)),
						new ImageIcon(loadImage(GREEN_PLAYER_UP_W_1).getScaledInstance(PLAYER_SIZE, PLAYER_SIZE, 0)),
						new ImageIcon(loadImage(GREEN_PLAYER_UP).getScaledInstance(PLAYER_SIZE, PLAYER_SIZE, 0)),
						new ImageIcon(loadImage(GREEN_PLAYER_UP_W_2).getScaledInstance(PLAYER_SIZE, PLAYER_SIZE, 0))
				};
				movingRight = new ImageIcon[] {
						new ImageIcon(loadImage(GREEN_PLAYER_RIGHT).getScaledInstance(PLAYER_SIZE, PLAYER_SIZE, 0)),
						new ImageIcon(loadImage(GREEN_PLAYER_RIGHT_W_1).getScaledInstance(PLAYER_SIZE, PLAYER_SIZE, 0)),
						new ImageIcon(loadImage(GREEN_PLAYER_RIGHT_W_2).getScaledInstance(PLAYER_SIZE, PLAYER_SIZE, 0)),
						new ImageIcon(loadImage(GREEN_PLAYER_RIGHT_W_3).getScaledInstance(PLAYER_SIZE, PLAYER_SIZE, 0))
				};
				movingDown = new ImageIcon[] {
						new ImageIcon(loadImage(GREEN_PLAYER_DOWN).getScaledInstance(PLAYER_SIZE, PLAYER_SIZE, 0)),
						new ImageIcon(loadImage(GREEN_PLAYER_DOWN_W_1).getScaledInstance(PLAYER_SIZE, PLAYER_SIZE, 0)),
						new ImageIcon(loadImage(GREEN_PLAYER_DOWN).getScaledInstance(PLAYER_SIZE, PLAYER_SIZE, 0)),
						new ImageIcon(loadImage(GREEN_PLAYER_DOWN_W_2).getScaledInstance(PLAYER_SIZE, PLAYER_SIZE, 0))
				};
				movingLeft = new ImageIcon[] {
						new ImageIcon(loadImage(GREEN_PLAYER_LEFT).getScaledInstance(PLAYER_SIZE, PLAYER_SIZE, 0)),
						new ImageIcon(loadImage(GREEN_PLAYER_LEFT_W_1).getScaledInstance(PLAYER_SIZE, PLAYER_SIZE, 0)),
						new ImageIcon(loadImage(GREEN_PLAYER_LEFT_W_2).getScaledInstance(PLAYER_SIZE, PLAYER_SIZE, 0)),
						new ImageIcon(loadImage(GREEN_PLAYER_LEFT_W_3).getScaledInstance(PLAYER_SIZE, PLAYER_SIZE, 0))
				};
				break;
			case PURPLE:
				movingUp = new ImageIcon[] {
						new ImageIcon(loadImage(PURPLE_PLAYER_UP).getScaledInstance(PLAYER_SIZE, PLAYER_SIZE, 0)),
						new ImageIcon(loadImage(PURPLE_PLAYER_UP_W_1).getScaledInstance(PLAYER_SIZE, PLAYER_SIZE, 0)),
						new ImageIcon(loadImage(PURPLE_PLAYER_UP).getScaledInstance(PLAYER_SIZE, PLAYER_SIZE, 0)),
						new ImageIcon(loadImage(PURPLE_PLAYER_UP_W_2).getScaledInstance(PLAYER_SIZE, PLAYER_SIZE, 0))
				};
				movingRight = new ImageIcon[] {
						new ImageIcon(loadImage(PURPLE_PLAYER_RIGHT).getScaledInstance(PLAYER_SIZE, PLAYER_SIZE, 0)),
						new ImageIcon(loadImage(PURPLE_PLAYER_RIGHT_W_1).getScaledInstance(PLAYER_SIZE, PLAYER_SIZE, 0)),
						new ImageIcon(loadImage(PURPLE_PLAYER_RIGHT_W_2).getScaledInstance(PLAYER_SIZE, PLAYER_SIZE, 0)),
						new ImageIcon(loadImage(PURPLE_PLAYER_RIGHT_W_3).getScaledInstance(PLAYER_SIZE, PLAYER_SIZE, 0))
				};
				movingDown = new ImageIcon[] {
						new ImageIcon(loadImage(PURPLE_PLAYER_DOWN).getScaledInstance(PLAYER_SIZE, PLAYER_SIZE, 0)),
						new ImageIcon(loadImage(PURPLE_PLAYER_DOWN_W_1).getScaledInstance(PLAYER_SIZE, PLAYER_SIZE, 0)),
						new ImageIcon(loadImage(PURPLE_PLAYER_DOWN).getScaledInstance(PLAYER_SIZE, PLAYER_SIZE, 0)),
						new ImageIcon(loadImage(PURPLE_PLAYER_DOWN_W_2).getScaledInstance(PLAYER_SIZE, PLAYER_SIZE, 0))
				};
				movingLeft = new ImageIcon[] {
						new ImageIcon(loadImage(PURPLE_PLAYER_LEFT).getScaledInstance(PLAYER_SIZE, PLAYER_SIZE, 0)),
						new ImageIcon(loadImage(PURPLE_PLAYER_LEFT_W_1).getScaledInstance(PLAYER_SIZE, PLAYER_SIZE, 0)),
						new ImageIcon(loadImage(PURPLE_PLAYER_LEFT_W_2).getScaledInstance(PLAYER_SIZE, PLAYER_SIZE, 0)),
						new ImageIcon(loadImage(PURPLE_PLAYER_LEFT_W_3).getScaledInstance(PLAYER_SIZE, PLAYER_SIZE, 0))
				};
				break;
			case YELLOW:
				movingUp = new ImageIcon[] {
						new ImageIcon(loadImage(YELLOW_PLAYER_UP).getScaledInstance(PLAYER_SIZE, PLAYER_SIZE, 0)),
						new ImageIcon(loadImage(YELLOW_PLAYER_UP_W_1).getScaledInstance(PLAYER_SIZE, PLAYER_SIZE, 0)),
						new ImageIcon(loadImage(YELLOW_PLAYER_UP).getScaledInstance(PLAYER_SIZE, PLAYER_SIZE, 0)),
						new ImageIcon(loadImage(YELLOW_PLAYER_UP_W_2).getScaledInstance(PLAYER_SIZE, PLAYER_SIZE, 0))
				};
				movingRight = new ImageIcon[] {
						new ImageIcon(loadImage(YELLOW_PLAYER_RIGHT).getScaledInstance(PLAYER_SIZE, PLAYER_SIZE, 0)),
						new ImageIcon(loadImage(YELLOW_PLAYER_RIGHT_W_1).getScaledInstance(PLAYER_SIZE, PLAYER_SIZE, 0)),
						new ImageIcon(loadImage(YELLOW_PLAYER_RIGHT_W_2).getScaledInstance(PLAYER_SIZE, PLAYER_SIZE, 0)),
						new ImageIcon(loadImage(YELLOW_PLAYER_RIGHT_W_3).getScaledInstance(PLAYER_SIZE, PLAYER_SIZE, 0))
				};
				movingDown = new ImageIcon[] {
						new ImageIcon(loadImage(YELLOW_PLAYER_DOWN).getScaledInstance(PLAYER_SIZE, PLAYER_SIZE, 0)),
						new ImageIcon(loadImage(YELLOW_PLAYER_DOWN_W_1).getScaledInstance(PLAYER_SIZE, PLAYER_SIZE, 0)),
						new ImageIcon(loadImage(YELLOW_PLAYER_DOWN).getScaledInstance(PLAYER_SIZE, PLAYER_SIZE, 0)),
						new ImageIcon(loadImage(YELLOW_PLAYER_DOWN_W_2).getScaledInstance(PLAYER_SIZE, PLAYER_SIZE, 0))
				};
				movingLeft = new ImageIcon[] {
						new ImageIcon(loadImage(YELLOW_PLAYER_LEFT).getScaledInstance(PLAYER_SIZE, PLAYER_SIZE, 0)),
						new ImageIcon(loadImage(YELLOW_PLAYER_LEFT_W_1).getScaledInstance(PLAYER_SIZE, PLAYER_SIZE, 0)),
						new ImageIcon(loadImage(YELLOW_PLAYER_LEFT_W_2).getScaledInstance(PLAYER_SIZE, PLAYER_SIZE, 0)),
						new ImageIcon(loadImage(YELLOW_PLAYER_LEFT_W_3).getScaledInstance(PLAYER_SIZE, PLAYER_SIZE, 0))
				};
				break;
		}
	}

	public enum PlayerVersion {
		GRAY, GREEN, PURPLE, YELLOW
	}

	private enum Moving {
		MOVING_UP,
		MOVING_RIGHT,
		MOVING_DOWN,
		MOVING_LEFT,
		STAND;

		Moving getMoving(int dir) {
			Moving mov;
			switch (dir) {
				case 1:
					mov = MOVING_UP;
					break;
				case 2:
					mov = MOVING_RIGHT;
					break;
				case 3:
					mov = MOVING_DOWN;
					break;
				case 4:
					mov = MOVING_LEFT;
					break;
				default:
					mov = STAND;
			}
			return mov;
		}
	}

	private enum Direction {
		FACING_UP,
		FACING_RIGHT,
		FACING_DOWN,
		FACING_LEFT;

		Direction faceDirection(int dir) {
			Direction direction = FACING_DOWN;
			switch (dir) {
				case 1:
					direction = FACING_UP;
					break;
				case 2:
					direction = FACING_RIGHT;
					break;
				case 3:
					direction = FACING_DOWN;
					break;
				case 4:
					direction = FACING_LEFT;
					break;
			}
			return direction;
		}

	}
}
