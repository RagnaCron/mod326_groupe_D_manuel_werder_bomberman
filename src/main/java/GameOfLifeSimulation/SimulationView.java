package GameOfLifeSimulation;

import javax.swing.*;
import java.awt.*;

/**
 * URSPRUNG:
 * https://github.com/dsenften/ict-404/tree/master/src/main/java/simulation
 *
 * A graphical view that simulates fields.
 * The view shows for each position a colored rectangle,
 * that displays the given content, and that has a
 * given background color.
 */
@SuppressWarnings("WeakerAccess")
public class SimulationView extends JFrame {

    private int rowSize;
    private int columnSize;

    // Die Farbe für True
    private static final Color LIFE_COLOR = Color.BLACK;

    // Die Farbe für für False
    private static final Color DEAD_COLOR = Color.WHITE;

    //private JLabel stepLabel, population;
    private FieldView fieldView;

    /**
     * Creates a view with the given height and with.
     *
     * @param rowSize The height of the Simulation.
     * @param columnSize  The width of the Simulation.
     */
    public SimulationView(int rowSize, int columnSize) {
        this.rowSize = rowSize;
        this.columnSize = columnSize;

        setTitle("Simulation of Life and Death");

        setLocation(100, 50);

        fieldView = new FieldView(rowSize, columnSize);

        Container container = getContentPane();
        container.add(fieldView, BorderLayout.CENTER);

        pack();
        setVisible(true);
    }

    /**
     * @return Defines the color of the current field.
     */
    private Color getColor(boolean[][] world, int x, int y) {
        return world[x][y] ? LIFE_COLOR : DEAD_COLOR;
    }

    /**
     * Shows the current stat of the field.
     *
     * @param world The world.
     */
    public void showStatus(boolean[][] world) {
        if (!isVisible())
            setVisible(true);

        fieldView.prepareView();

        for (int row = 0; row < rowSize; row++) {
            for (int column = 0; column < columnSize; column++) {
                fieldView.drawField(column, row, getColor(world, row, column));
            }
        }
        fieldView.repaint();
    }

    /**
     * Deside, if the animation should go on.
     *
     * @return True when more then to fields are a life.
     */
    public boolean isActiv(boolean[][] world) {
        int counter = 0;
        for (int x = 0; x < rowSize; x++) {
            for (int y = 0; y < columnSize; y++) {
                if (world[x][y]) counter++;
            }
        }
        return counter > 1;
    }

    /**
     * Liefere eine grafische Ansicht eines rechteckigen Feldes.
     * Dies ist eine geschachtelte Klasse (eine Klasse, die
     * innerhalb einer anderen Klasse definiert ist), die eine
     * eigene grafische Komponente für die Benutzungsschnittstelle
     * definiert. Diese Komponente zeigt das Field an.
     * Dies ist fortgeschrittene GUI-Technik - Sie können sie
     * für Ihr Projekt ignorieren, wenn Sie wollen.
     */
    private class FieldView extends JPanel {
        private static final long serialVersionUID = 20060330L;
        private final int FACTOR = 6;

        private int fieldWidth, fieldHeight;
        private int xFactor, yFactor;

        Dimension size;
        private Graphics g;
        private Image image;

        /**
         * Erzeuge eine neue Komponente zur FieldView.
         */
        public FieldView(int height, int width) {
            fieldHeight = height;
            fieldWidth = width;
            size = new Dimension(0, 0);
        }

        /**
         * Der GUI-Verwaltung mitteilen, wie gross wir sein wollen.
         * Der Name der Methode ist durch die GUI-Verwaltung festgelegt.
         */
        public Dimension getPreferredSize() {
            return new Dimension(fieldWidth * FACTOR,
                    fieldHeight * FACTOR);
        }

        /**
         * Bereite eine neue Zeichenrunde vor. Da die Komponente
         * in der Grsse geändert werden kann, muss der Massstab neu
         * berechnet werden.
         */
        public void prepareView() {
            if (!size.equals(getSize())) {
                size = getSize();
                image = fieldView.createImage(size.width, size.height);
                g = image.getGraphics();

                xFactor = size.width / fieldWidth;
                if (xFactor < 1) {
                    xFactor = FACTOR;
                }
                yFactor = size.height / fieldHeight;
                if (yFactor < 1) {
                    yFactor = FACTOR;
                }
            }
        }

        /**
         * Zeichne an der gegebenen Position ein Rechteck mit
         * der gegebenen Farbe.
         */
        public void drawField(int x, int y, Color farbe) {
            g.setColor(farbe);
            g.fillRect(x * xFactor, y * yFactor, xFactor - 1, yFactor - 1);
        }

        /**
         * Die Komponente für die FieldView muss erneut angezeigt
         * werden. Kopiere das interne Image in die Anzeige.
         * Der Name der Methode ist durch die GUI-Verwaltung festgelegt.
         */
        public void paintComponent(Graphics g) {
            if (image != null) {
                Dimension actualSize = getSize();
                if (size.equals(actualSize)) {
                    g.drawImage(image, 0, 0, null);
                } else {
                    // Grösse des aktuellen Images anpassen.
                    g.drawImage(image, 0, 0, actualSize.width,
                            actualSize.height, null);
                }
            }
        }
    }
}
