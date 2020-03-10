package BomberGame.Constants;

import java.awt.*;

public interface BomberGameConstants {
	int GRID_SIZE = 16;
	int LABYRINTH_TILE_SIZE = 32;
	int FRAME_SIZE = 48;
	int SPACING = 1;
	int X_OFFSET = 0, Y_OFFSET = 128;

	int FRAME_WIDTH = X_OFFSET + (GRID_SIZE * FRAME_SIZE) + SPACING;
	int FRAME_HEIGHT = Y_OFFSET + (GRID_SIZE * FRAME_SIZE) + SPACING;
	Dimension BOMBER_FRAME_SIZE = new Dimension(FRAME_WIDTH, FRAME_HEIGHT);

	int SCREEN_WIDTH = (int) Toolkit.getDefaultToolkit().getScreenSize().getWidth();
	int SCREEN_HEIGHT = (int) Toolkit.getDefaultToolkit().getScreenSize().getHeight();
	Point INITIAL_LOCATION = new Point(SCREEN_WIDTH / 3, SCREEN_HEIGHT / 6);

	int LABYRINTH_WIDTH = GRID_SIZE * LABYRINTH_TILE_SIZE;
	int LABYRINTH_HEIGHT = GRID_SIZE * LABYRINTH_TILE_SIZE;
	Dimension LABYRINTH_SIZE = new Dimension(LABYRINTH_WIDTH, LABYRINTH_HEIGHT);
	Rectangle LABYRINTH_POSITION = new Rectangle(
			(FRAME_WIDTH - LABYRINTH_WIDTH) / 2,
			(FRAME_HEIGHT - LABYRINTH_HEIGHT - Y_OFFSET) / 2,
			LABYRINTH_WIDTH,
			LABYRINTH_HEIGHT
	);

	Rectangle TEXT_INPUT_POSITION = new Rectangle(
			(FRAME_WIDTH) / 6,
			LABYRINTH_TILE_SIZE,
			LABYRINTH_WIDTH / 2 + LABYRINTH_TILE_SIZE * 2,
			LABYRINTH_TILE_SIZE
	);

	Rectangle SING_IN_BUTTON_POSITION = new Rectangle(
			FRAME_WIDTH / 2 + Y_OFFSET,
			LABYRINTH_TILE_SIZE,
			LABYRINTH_WIDTH / 4,
			LABYRINTH_TILE_SIZE
	);

	Rectangle START_GAME_BUTTON_POSITION = new Rectangle(
			FRAME_WIDTH / 2 + Y_OFFSET,
			LABYRINTH_TILE_SIZE * 2 + GRID_SIZE,
			LABYRINTH_WIDTH / 4,
			LABYRINTH_TILE_SIZE
	);

	Rectangle LOGGING_TEXTAREA_POSITION = new Rectangle(
			FRAME_WIDTH / 6,
			FRAME_HEIGHT / 2 + LABYRINTH_HEIGHT / 2 - LABYRINTH_TILE_SIZE,
			LABYRINTH_WIDTH,
			Y_OFFSET + LABYRINTH_TILE_SIZE * 2
	);

	Dimension TILE_DIMENSION = new Dimension(LABYRINTH_TILE_SIZE, LABYRINTH_TILE_SIZE);
	String INDESTRUCTIBLE_TILE_1 = "src/main/resources/GameArt/Tiles/tile_indestructible_1.png";
	String INDESTRUCTIBLE_TILE_2 = "src/main/resources/GameArt/Tiles/tile_indestructible_2.png";
	String GRASS_TILE = "src/main/resources/GameArt/Tiles/tile_grass.png";
	String DESTROYABLE_TILE = "src/main/resources/GameArt/Tiles/tile_destroyable.png";

	int PLAYER_MOVING_VALUE = 3;
	int PLAYER_SIZE = 24;
	Dimension PLAYER_DIMENSION = new Dimension(PLAYER_SIZE, PLAYER_SIZE);
	Rectangle LEFT_UPPER_CORNER_POSITION = new Rectangle(
			(FRAME_WIDTH - LABYRINTH_WIDTH) / 6 - 8,
			(FRAME_HEIGHT - LABYRINTH_HEIGHT - Y_OFFSET) / 6 - 8,
			PLAYER_SIZE,
			PLAYER_SIZE
	);
	Rectangle RIGHT_UPPER_CORNER_POSITION = new Rectangle(
			LABYRINTH_WIDTH - 2 * LABYRINTH_TILE_SIZE + 4,
			(FRAME_HEIGHT - LABYRINTH_HEIGHT - Y_OFFSET) / 6 - 8,
			PLAYER_SIZE,
			PLAYER_SIZE
	);
	Rectangle RIGHT_BOTTOM_CORNER_POSITION = new Rectangle(
			LABYRINTH_WIDTH - 2 * LABYRINTH_TILE_SIZE + 4,
			LABYRINTH_HEIGHT - (2 * LABYRINTH_TILE_SIZE) + 2,
			PLAYER_SIZE,
			PLAYER_SIZE
	);
	Rectangle LEFT_BOTTOM_CORNER_POSITION = new Rectangle(
			(FRAME_WIDTH - LABYRINTH_WIDTH) / 6 - 8,
			LABYRINTH_HEIGHT - (2 * LABYRINTH_TILE_SIZE) + 2,
			PLAYER_SIZE,
			PLAYER_SIZE
	);

	String GRAY_PLAYER_UP =         "src/main/resources/GameArt/GrayPlayer/player_gr_up.png";
	String GRAY_PLAYER_UP_W_1 =     "src/main/resources/GameArt/GrayPlayer/player_gr_up_w_1.png";
	String GRAY_PLAYER_UP_W_2 =     "src/main/resources/GameArt/GrayPlayer/player_gr_up_w_2.png";
	String GRAY_PLAYER_RIGHT =      "src/main/resources/GameArt/GrayPlayer/player_gr_right.png";
	String GRAY_PLAYER_RIGHT_W_1 =  "src/main/resources/GameArt/GrayPlayer/player_gr_right_w_1.png";
	String GRAY_PLAYER_RIGHT_W_2 =  "src/main/resources/GameArt/GrayPlayer/player_gr_right_w_2.png";
	String GRAY_PLAYER_RIGHT_W_3 =  "src/main/resources/GameArt/GrayPlayer/player_gr_right_w_3.png";
	String GRAY_PLAYER_DOWN =       "src/main/resources/GameArt/GrayPlayer/player_gr_down.png";
	String GRAY_PLAYER_DOWN_W_1 =   "src/main/resources/GameArt/GrayPlayer/player_gr_down_w_1.png";
	String GRAY_PLAYER_DOWN_W_2 =   "src/main/resources/GameArt/GrayPlayer/player_gr_down_w_2.png";
	String GRAY_PLAYER_LEFT =       "src/main/resources/GameArt/GrayPlayer/player_gr_left.png";
	String GRAY_PLAYER_LEFT_W_1 =   "src/main/resources/GameArt/GrayPlayer/player_gr_left_w_1.png";
	String GRAY_PLAYER_LEFT_W_2 =   "src/main/resources/GameArt/GrayPlayer/player_gr_left_w_2.png";
	String GRAY_PLAYER_LEFT_W_3 =   "src/main/resources/GameArt/GrayPlayer/player_gr_left_w_3.png";

	String GREEN_PLAYER_UP =         "src/main/resources/GameArt/GreenPlayer/player_g_up.png";
	String GREEN_PLAYER_UP_W_1 =     "src/main/resources/GameArt/GreenPlayer/player_g_up_w_1.png";
	String GREEN_PLAYER_UP_W_2 =     "src/main/resources/GameArt/GreenPlayer/player_g_up_w_2.png";
	String GREEN_PLAYER_RIGHT =      "src/main/resources/GameArt/GreenPlayer/player_g_right.png";
	String GREEN_PLAYER_RIGHT_W_1 =  "src/main/resources/GameArt/GreenPlayer/player_g_right_w_1.png";
	String GREEN_PLAYER_RIGHT_W_2 =  "src/main/resources/GameArt/GreenPlayer/player_g_right_w_2.png";
	String GREEN_PLAYER_RIGHT_W_3 =  "src/main/resources/GameArt/GreenPlayer/player_g_right_w_3.png";
	String GREEN_PLAYER_DOWN =       "src/main/resources/GameArt/GreenPlayer/player_g_down.png";
	String GREEN_PLAYER_DOWN_W_1 =   "src/main/resources/GameArt/GreenPlayer/player_g_down_w_1.png";
	String GREEN_PLAYER_DOWN_W_2 =   "src/main/resources/GameArt/GreenPlayer/player_g_down_w_2.png";
	String GREEN_PLAYER_LEFT =       "src/main/resources/GameArt/GreenPlayer/player_g_left.png";
	String GREEN_PLAYER_LEFT_W_1 =   "src/main/resources/GameArt/GreenPlayer/player_g_left_w_1.png";
	String GREEN_PLAYER_LEFT_W_2 =   "src/main/resources/GameArt/GreenPlayer/player_g_left_w_2.png";
	String GREEN_PLAYER_LEFT_W_3 =   "src/main/resources/GameArt/GreenPlayer/player_g_left_w_3.png";

	String PURPLE_PLAYER_UP =         "src/main/resources/GameArt/PurplePlayer/player_p_up.png";
	String PURPLE_PLAYER_UP_W_1 =     "src/main/resources/GameArt/PurplePlayer/player_p_up_w_1.png";
	String PURPLE_PLAYER_UP_W_2 =     "src/main/resources/GameArt/PurplePlayer/player_p_up_w_2.png";
	String PURPLE_PLAYER_RIGHT =      "src/main/resources/GameArt/PurplePlayer/player_p_right.png";
	String PURPLE_PLAYER_RIGHT_W_1 =  "src/main/resources/GameArt/PurplePlayer/player_p_right_w_1.png";
	String PURPLE_PLAYER_RIGHT_W_2 =  "src/main/resources/GameArt/PurplePlayer/player_p_right_w_2.png";
	String PURPLE_PLAYER_RIGHT_W_3 =  "src/main/resources/GameArt/PurplePlayer/player_p_right_w_3.png";
	String PURPLE_PLAYER_DOWN =       "src/main/resources/GameArt/PurplePlayer/player_p_down.png";
	String PURPLE_PLAYER_DOWN_W_1 =   "src/main/resources/GameArt/PurplePlayer/player_p_down_w_1.png";
	String PURPLE_PLAYER_DOWN_W_2 =   "src/main/resources/GameArt/PurplePlayer/player_p_down_w_2.png";
	String PURPLE_PLAYER_LEFT =       "src/main/resources/GameArt/PurplePlayer/player_p_left.png";
	String PURPLE_PLAYER_LEFT_W_1 =   "src/main/resources/GameArt/PurplePlayer/player_p_left_w_1.png";
	String PURPLE_PLAYER_LEFT_W_2 =   "src/main/resources/GameArt/PurplePlayer/player_p_left_w_2.png";
	String PURPLE_PLAYER_LEFT_W_3 =   "src/main/resources/GameArt/PurplePlayer/player_p_left_w_3.png";

	String YELLOW_PLAYER_UP =         "src/main/resources/GameArt/YellowPlayer/player_y_up.png";
	String YELLOW_PLAYER_UP_W_1 =     "src/main/resources/GameArt/YellowPlayer/player_y_up_w_1.png";
	String YELLOW_PLAYER_UP_W_2 =     "src/main/resources/GameArt/YellowPlayer/player_y_up_w_2.png";
	String YELLOW_PLAYER_RIGHT =      "src/main/resources/GameArt/YellowPlayer/player_y_right.png";
	String YELLOW_PLAYER_RIGHT_W_1 =  "src/main/resources/GameArt/YellowPlayer/player_y_right_w_1.png";
	String YELLOW_PLAYER_RIGHT_W_2 =  "src/main/resources/GameArt/YellowPlayer/player_y_right_w_2.png";
	String YELLOW_PLAYER_RIGHT_W_3 =  "src/main/resources/GameArt/YellowPlayer/player_y_right_w_3.png";
	String YELLOW_PLAYER_DOWN =       "src/main/resources/GameArt/YellowPlayer/player_y_down.png";
	String YELLOW_PLAYER_DOWN_W_1 =   "src/main/resources/GameArt/YellowPlayer/player_y_down_w_1.png";
	String YELLOW_PLAYER_DOWN_W_2 =   "src/main/resources/GameArt/YellowPlayer/player_y_down_w_2.png";
	String YELLOW_PLAYER_LEFT =       "src/main/resources/GameArt/YellowPlayer/player_y_left.png";
	String YELLOW_PLAYER_LEFT_W_1 =   "src/main/resources/GameArt/YellowPlayer/player_y_left_w_1.png";
	String YELLOW_PLAYER_LEFT_W_2 =   "src/main/resources/GameArt/YellowPlayer/player_y_left_w_2.png";
	String YELLOW_PLAYER_LEFT_W_3 =   "src/main/resources/GameArt/YellowPlayer/player_y_left_w_3.png";
}
