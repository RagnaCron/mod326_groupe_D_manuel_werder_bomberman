package BomberGame.GameEntities.Player;

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

	public Player(Dimension size, Rectangle position, PlayerStartPosition startPosition) {
		super(PlayerStartPosition.PlayerVersion(startPosition), size, position);
		this.facingDirection = PlayerStartPosition.FacingDirection(startPosition);
	}

	@Override
	public boolean isCollidingWith(Rectangle rect) {
		return canCollide && getBounds().intersects(rect);
	}

	private void loadPlayer(PlayerVersion version) {
		switch (version) {
			case GRAY:
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
			case GREEN:
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
			case PURPLE:
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
			case YELLOW:
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
