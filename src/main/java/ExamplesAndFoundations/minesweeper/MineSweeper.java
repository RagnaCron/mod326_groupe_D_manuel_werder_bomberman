package ExamplesAndFoundations.minesweeper;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseEvent;
import java.util.Random;

@SuppressWarnings("all")
public class MineSweeper {

    // Will be used for redrawing the GUI component.
    private static JFrame mineSweeper;

    // Current position of the mouse pointer
    private int mousePositionX, mousePositionY;

    // Arrays to be used with the game
    private int[][] mines;
    private int[][] neighbours;
    private boolean[][] revealed;

    public MineSweeper() {
        initArrays();
        initUI();
    }

    private void initArrays() {

        /*
         * Damit die Berechnung der einzelnen Positionen einfacher wird
         * ist unser Array jeweils um eine Position in alle Richtungen
         * grösser.
         */
        mines = new int[Constants.GRID_SIZE + 2][Constants.GRID_SIZE + 2];
        neighbours = new int[Constants.GRID_SIZE + 2][Constants.GRID_SIZE + 2];
        revealed = new boolean[Constants.GRID_SIZE + 2][Constants.GRID_SIZE + 2];

        // Random number generator
        Random random = new Random();

        // Verteilen der Minen
        for (int row = 1; row < Constants.GRID_SIZE; row++) {
            for (int col = 1; col < Constants.GRID_SIZE; col++) {

                // Approximately 20% of all squares are mines
                if (random.nextInt(100) < 20) {
                    mines[row][col] = 1;
                } else {
                    mines[row][col] = 0;
                }
            }
        }

        // Zählen der Nachbarn
        for (int row = 1; row < Constants.GRID_SIZE; row++) {
            for (int col = 1; col < Constants.GRID_SIZE; col++) {
                neighbours[row][col] = mines[row - 1][col - 1] +
                        mines[row - 1][col] +
                        mines[row - 1][col + 1] +
                        mines[row][col - 1] +
                        mines[row][col + 1] +
                        mines[row + 1][col - 1] +
                        mines[row + 1][col] +
                        mines[row + 1][col + 1];
            }
        }
    }

    public static void main(String[] args) {
        // Default value, if no valid argiment is given
        int size = 8;
        try {
            size = Integer.parseInt(args[0]);
        } catch (Exception ignored) {
        }

        new MineSweeper();
    }

    /**
     * Initialisierung des GUIs mit all seinen sichtbaren
     * Komponenten.
     */
    private void initUI() {
        mineSweeper = new JFrame("MineSweeper");
        mineSweeper.setPreferredSize(new Dimension(Constants.WIDTH, Constants.HEIGHT));
        mineSweeper.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        mineSweeper.setResizable(false);

        mineSweeper.setContentPane(
                new Board(mineSweeper.getWidth(), mineSweeper.getHeight()));

        mineSweeper.addMouseListener(new MouseAdapter());
        mineSweeper.addMouseMotionListener(new MouseAdapter());

        mineSweeper.pack();
        mineSweeper.setVisible(true);
    }

    /**
     * Definition unseres Boards mit allen Feldern. Die Zellen werden
     * entsprechend eingefärbt (und dargestellt).
     */
    private class Board extends JPanel {
        private final int width, height;

        Board(final int width, final int height) {
            this.width = width;
            this.height = height;
        }

        @Override
        protected void paintComponent(Graphics graphics) {
            graphics.setColor(Color.DARK_GRAY);
            graphics.fillRect(0, 0, width, height);


            for (int row = 0; row < Constants.GRID_SIZE; row++) {
                for (int col = 0; col < Constants.GRID_SIZE; col++) {

                    int y = (row * Constants.SQUARE_SIZE) + Constants.SPACING;
                    int x = (col * Constants.SQUARE_SIZE) + Constants.SPACING;

                    // Initial color of all squares
                    graphics.setColor(Color.LIGHT_GRAY);

                    // Show mines
                    if (mines[row][col] == 1) {
                        graphics.setColor(Color.YELLOW);
                    }

                    // Show the square as we move around
                    if (row == mousePositionY && col == mousePositionX) {
                        graphics.setColor(Color.RED);
                    }

                    graphics.fillRect(x, y,
                            Constants.SQUARE_SIZE - Constants.SPACING,
                            Constants.SQUARE_SIZE - Constants.SPACING);

                    // Show number of mines
                    if (revealed[row][col]) {
                        graphics.setColor(Color.BLACK);
                        graphics.setFont(new Font("Arial", Font.BOLD, 20));
                        graphics.drawString("" + neighbours[row][col],
                                x + Constants.SQUARE_SIZE / 2 - 5,
                                y + Constants.SQUARE_SIZE / 2 + 5);
                    }
                }
            }
        }
    }

    /**
     * Callback-Methoden für jede Mausbewegung auf unserem Board.
     */
    private class MouseAdapter extends java.awt.event.MouseAdapter {

        @Override
        public void mouseClicked(MouseEvent e) {
            int col = (int) ((e.getPoint().getX() - Constants.X_OFFSET) / Constants.SQUARE_SIZE);
            int row = (int) ((e.getPoint().getY() - Constants.Y_OFFSET) / Constants.SQUARE_SIZE);

            System.out.println("Clicked: [" + row + "," + col + "]");

            if (mines[row][col] == 1) {
                System.out.println("Game over!");
                System.exit(0);
            }

            revealed[row][col] = true;
            mineSweeper.repaint();
        }

        @Override
        public void mouseMoved(MouseEvent e) {
            mousePositionX = (int) ((e.getPoint().getX() - Constants.X_OFFSET) / Constants.SQUARE_SIZE);
            mousePositionY = (int) ((e.getPoint().getY() - Constants.Y_OFFSET) / Constants.SQUARE_SIZE);

            System.out.println("Position: [" + mousePositionX + "," + mousePositionY + "]");
            mineSweeper.repaint();
        }
    }

}
