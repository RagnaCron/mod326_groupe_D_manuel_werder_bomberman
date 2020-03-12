package BomberMen.BomberGame.GameEntities.Player;


import BomberMen.BomberGame.Constants.BomberGameConstants;

public enum PlayerStartPosition{
	RIGHT_UPPER_CORNER,
	LEFT_UPPER_CORNER,
	LEFT_BOTTOM_CORNER,
	RIGHT_BOTTOM_CORNER;

	public static Direction FacingDirection(PlayerStartPosition startPosition) {
		switch (startPosition) {
			case RIGHT_UPPER_CORNER:
				return Direction.FACING_DOWN;
			case LEFT_UPPER_CORNER:
				return Direction.FACING_RIGHT;
			case LEFT_BOTTOM_CORNER:
				return Direction.FACING_UP;
			default:
				return Direction.FACING_LEFT;
		}
	}

	public static String PlayerVersion(PlayerStartPosition startPosition) {
		switch (startPosition) {
			case RIGHT_UPPER_CORNER:
				return BomberGameConstants.GREEN_PLAYER_DOWN;
			case LEFT_UPPER_CORNER:
				return BomberGameConstants.GRAY_PLAYER_RIGHT;
			case LEFT_BOTTOM_CORNER:
				return BomberGameConstants.YELLOW_PLAYER_UP;
			default:
				return BomberGameConstants.PURPLE_PLAYER_LEFT;
		}
	}
}
