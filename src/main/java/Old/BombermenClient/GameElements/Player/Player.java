package Old.BombermenClient.GameElements.Player;

import Old.BombermenClient.GameElements.Collide;
import Old.BombermenClient.GameElements.GameElement;

import java.awt.*;

import static java.lang.Thread.sleep;

public final class Player extends GameElement implements PlayerConstants, Collide {

	private Direction facingDirection;
	private Moving moving = null;
	private final PlayerVersion version;

	private Image[] movingUp;
	private Image[] movingRight;
	private Image[] movingDown;
	private Image[] movingLeft;

	private Thread animation;

	public Player(PlayerVersion version, Direction facingDirection, Dimension size, Rectangle position) {
		super();
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

//	@Override
//	protected void paintComponent(Graphics g) {
//		super.paintComponent(g);
//		Graphics2D graphics2D = (Graphics2D) g;
//		graphics2D.drawImage(image, getX(), getY(), this);
//	}

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

	private void display(Direction direction, Image image, Rectangle rect) {
		animation = new Thread(() -> {
//			image = (image);
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

	private void loadPlayer(PlayerVersion version) {
		switch (version) {
			case GRAY:
				movingUp = new Image[] {
						loadImage(GRAY_PLAYER_UP).getScaledInstance(PLAYER_SIZE, PLAYER_SIZE, 0),
						loadImage(GRAY_PLAYER_UP_W_1).getScaledInstance(PLAYER_SIZE, PLAYER_SIZE, 0),
						loadImage(GRAY_PLAYER_UP).getScaledInstance(PLAYER_SIZE, PLAYER_SIZE, 0),
						loadImage(GRAY_PLAYER_UP_W_2).getScaledInstance(PLAYER_SIZE, PLAYER_SIZE, 0)
				};
				movingRight = new Image[] {
						loadImage(GRAY_PLAYER_RIGHT).getScaledInstance(PLAYER_SIZE, PLAYER_SIZE, 0),
						loadImage(GRAY_PLAYER_RIGHT_W_1).getScaledInstance(PLAYER_SIZE, PLAYER_SIZE, 0),
						loadImage(GRAY_PLAYER_RIGHT_W_2).getScaledInstance(PLAYER_SIZE, PLAYER_SIZE, 0),
						loadImage(GRAY_PLAYER_RIGHT_W_3).getScaledInstance(PLAYER_SIZE, PLAYER_SIZE, 0)
				};
				movingDown = new Image[] {
						loadImage(GRAY_PLAYER_DOWN).getScaledInstance(PLAYER_SIZE, PLAYER_SIZE, 0),
						loadImage(GRAY_PLAYER_DOWN_W_1).getScaledInstance(PLAYER_SIZE, PLAYER_SIZE, 0),
						loadImage(GRAY_PLAYER_DOWN).getScaledInstance(PLAYER_SIZE, PLAYER_SIZE, 0),
						loadImage(GRAY_PLAYER_DOWN_W_2).getScaledInstance(PLAYER_SIZE, PLAYER_SIZE, 0)
				};
				movingLeft = new Image[] {
						loadImage(GRAY_PLAYER_LEFT).getScaledInstance(PLAYER_SIZE, PLAYER_SIZE, 0),
						loadImage(GRAY_PLAYER_LEFT_W_1).getScaledInstance(PLAYER_SIZE, PLAYER_SIZE, 0),
						loadImage(GRAY_PLAYER_LEFT_W_2).getScaledInstance(PLAYER_SIZE, PLAYER_SIZE, 0),
						loadImage(GRAY_PLAYER_LEFT_W_3).getScaledInstance(PLAYER_SIZE, PLAYER_SIZE, 0)
				};
				break;
			case GREEN:
				movingUp = new Image[] {
						loadImage(GREEN_PLAYER_UP).getScaledInstance(PLAYER_SIZE, PLAYER_SIZE, 0),
						loadImage(GREEN_PLAYER_UP_W_1).getScaledInstance(PLAYER_SIZE, PLAYER_SIZE, 0),
						loadImage(GREEN_PLAYER_UP).getScaledInstance(PLAYER_SIZE, PLAYER_SIZE, 0),
						loadImage(GREEN_PLAYER_UP_W_2).getScaledInstance(PLAYER_SIZE, PLAYER_SIZE, 0)
				};
				movingRight = new Image[] {
						loadImage(GREEN_PLAYER_RIGHT).getScaledInstance(PLAYER_SIZE, PLAYER_SIZE, 0),
						loadImage(GREEN_PLAYER_RIGHT_W_1).getScaledInstance(PLAYER_SIZE, PLAYER_SIZE, 0),
						loadImage(GREEN_PLAYER_RIGHT_W_2).getScaledInstance(PLAYER_SIZE, PLAYER_SIZE, 0),
						loadImage(GREEN_PLAYER_RIGHT_W_3).getScaledInstance(PLAYER_SIZE, PLAYER_SIZE, 0)
				};
				movingDown = new Image[] {
						loadImage(GREEN_PLAYER_DOWN).getScaledInstance(PLAYER_SIZE, PLAYER_SIZE, 0),
						loadImage(GREEN_PLAYER_DOWN_W_1).getScaledInstance(PLAYER_SIZE, PLAYER_SIZE, 0),
						loadImage(GREEN_PLAYER_DOWN).getScaledInstance(PLAYER_SIZE, PLAYER_SIZE, 0),
						loadImage(GREEN_PLAYER_DOWN_W_2).getScaledInstance(PLAYER_SIZE, PLAYER_SIZE, 0)
				};
				movingLeft = new Image[] {
						loadImage(GREEN_PLAYER_LEFT).getScaledInstance(PLAYER_SIZE, PLAYER_SIZE, 0),
						loadImage(GREEN_PLAYER_LEFT_W_1).getScaledInstance(PLAYER_SIZE, PLAYER_SIZE, 0),
						loadImage(GREEN_PLAYER_LEFT_W_2).getScaledInstance(PLAYER_SIZE, PLAYER_SIZE, 0),
						loadImage(GREEN_PLAYER_LEFT_W_3).getScaledInstance(PLAYER_SIZE, PLAYER_SIZE, 0)
				};
				break;
			case PURPLE:
				movingUp = new Image[] {
						loadImage(PURPLE_PLAYER_UP).getScaledInstance(PLAYER_SIZE, PLAYER_SIZE, 0),
						loadImage(PURPLE_PLAYER_UP_W_1).getScaledInstance(PLAYER_SIZE, PLAYER_SIZE, 0),
						loadImage(PURPLE_PLAYER_UP).getScaledInstance(PLAYER_SIZE, PLAYER_SIZE, 0),
						loadImage(PURPLE_PLAYER_UP_W_2).getScaledInstance(PLAYER_SIZE, PLAYER_SIZE, 0)
				};
				movingRight = new Image[] {
						loadImage(PURPLE_PLAYER_RIGHT).getScaledInstance(PLAYER_SIZE, PLAYER_SIZE, 0),
						loadImage(PURPLE_PLAYER_RIGHT_W_1).getScaledInstance(PLAYER_SIZE, PLAYER_SIZE, 0),
						loadImage(PURPLE_PLAYER_RIGHT_W_2).getScaledInstance(PLAYER_SIZE, PLAYER_SIZE, 0),
						loadImage(PURPLE_PLAYER_RIGHT_W_3).getScaledInstance(PLAYER_SIZE, PLAYER_SIZE, 0)
				};
				movingDown = new Image[] {
						loadImage(PURPLE_PLAYER_DOWN).getScaledInstance(PLAYER_SIZE, PLAYER_SIZE, 0),
						loadImage(PURPLE_PLAYER_DOWN_W_1).getScaledInstance(PLAYER_SIZE, PLAYER_SIZE, 0),
						loadImage(PURPLE_PLAYER_DOWN).getScaledInstance(PLAYER_SIZE, PLAYER_SIZE, 0),
						loadImage(PURPLE_PLAYER_DOWN_W_2).getScaledInstance(PLAYER_SIZE, PLAYER_SIZE, 0)
				};
				movingLeft = new Image[] {
						loadImage(PURPLE_PLAYER_LEFT).getScaledInstance(PLAYER_SIZE, PLAYER_SIZE, 0),
						loadImage(PURPLE_PLAYER_LEFT_W_1).getScaledInstance(PLAYER_SIZE, PLAYER_SIZE, 0),
						loadImage(PURPLE_PLAYER_LEFT_W_2).getScaledInstance(PLAYER_SIZE, PLAYER_SIZE, 0),
						loadImage(PURPLE_PLAYER_LEFT_W_3).getScaledInstance(PLAYER_SIZE, PLAYER_SIZE, 0)
				};
				break;
			case YELLOW:
				movingUp = new Image[] {
						loadImage(YELLOW_PLAYER_UP).getScaledInstance(PLAYER_SIZE, PLAYER_SIZE, 0),
						loadImage(YELLOW_PLAYER_UP_W_1).getScaledInstance(PLAYER_SIZE, PLAYER_SIZE, 0),
						loadImage(YELLOW_PLAYER_UP).getScaledInstance(PLAYER_SIZE, PLAYER_SIZE, 0),
						loadImage(YELLOW_PLAYER_UP_W_2).getScaledInstance(PLAYER_SIZE, PLAYER_SIZE, 0)
				};
				movingRight = new Image[] {
						loadImage(YELLOW_PLAYER_RIGHT).getScaledInstance(PLAYER_SIZE, PLAYER_SIZE, 0),
						loadImage(YELLOW_PLAYER_RIGHT_W_1).getScaledInstance(PLAYER_SIZE, PLAYER_SIZE, 0),
						loadImage(YELLOW_PLAYER_RIGHT_W_2).getScaledInstance(PLAYER_SIZE, PLAYER_SIZE, 0),
						loadImage(YELLOW_PLAYER_RIGHT_W_3).getScaledInstance(PLAYER_SIZE, PLAYER_SIZE, 0)
				};
				movingDown = new Image[] {
						loadImage(YELLOW_PLAYER_DOWN).getScaledInstance(PLAYER_SIZE, PLAYER_SIZE, 0),
						loadImage(YELLOW_PLAYER_DOWN_W_1).getScaledInstance(PLAYER_SIZE, PLAYER_SIZE, 0),
						loadImage(YELLOW_PLAYER_DOWN).getScaledInstance(PLAYER_SIZE, PLAYER_SIZE, 0),
						loadImage(YELLOW_PLAYER_DOWN_W_2).getScaledInstance(PLAYER_SIZE, PLAYER_SIZE, 0)
				};
				movingLeft = new Image[] {
						loadImage(YELLOW_PLAYER_LEFT).getScaledInstance(PLAYER_SIZE, PLAYER_SIZE, 0),
						loadImage(YELLOW_PLAYER_LEFT_W_1).getScaledInstance(PLAYER_SIZE, PLAYER_SIZE, 0),
						loadImage(YELLOW_PLAYER_LEFT_W_2).getScaledInstance(PLAYER_SIZE, PLAYER_SIZE, 0),
						loadImage(YELLOW_PLAYER_LEFT_W_3).getScaledInstance(PLAYER_SIZE, PLAYER_SIZE, 0)
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

	public enum Direction {
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
