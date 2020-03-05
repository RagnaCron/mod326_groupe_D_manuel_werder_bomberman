package ExamplesAndFoundations.minesweeper;

public interface Constants {

    int GRID_SIZE = 20;
    int SQUARE_SIZE = 40;
    int SPACING = 1;
    int X_OFFSET = 0, Y_OFFSET = 22;

    int WIDTH = X_OFFSET + (GRID_SIZE * SQUARE_SIZE) + SPACING;
    int HEIGHT = Y_OFFSET + (GRID_SIZE * SQUARE_SIZE) + SPACING;
}
