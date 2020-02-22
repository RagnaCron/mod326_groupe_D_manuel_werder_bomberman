package GameOfLifeSimulation;

import java.util.Random;

@SuppressWarnings("all")
public class GameOfLife {

    private int DIM1;
    private int DIM2;

    public GameOfLife(int rows, int columns) {
        DIM1 = rows;
        DIM2 = columns;
    }

    public GameOfLife() {
        new GameOfLife(12, 12);
    }

    boolean[][] initRandomPopulatedWorld() {
        Random rand = new Random();
        boolean[][] world = new boolean[DIM1][DIM2];
        for (int x = 0; x < DIM1; x++)
            for (int y = 0; y < DIM2; y++)
                world[x][y] = rand.nextBoolean();
        return world;
    }

    public boolean[][] initDeadWorld() {
    	return new boolean[DIM1][DIM2];
	}

    public void printOurWorld(boolean[][] world) {
        for (int x = 0; x < DIM1 ; x++) {
            for (int y = 0; y < DIM2; y++)
                System.out.print((world[x][y]? 1 : 0) + "  ");
            System.out.println();
        }
    }

    // Wendet die 4 Regeln an und gibt die Folgegeneration wieder zurück
    // 1. Jede lebendige Zelle, die weniger als zwei lebendige Nachbarn hat, stirbt an Einsamkeit.
    // 2. Jede lebendige Zelle mit mehr als drei lebendigen Nachbarn stirbt an Überbevölkerung.
    // 3. Jede lebendige Zelle mit zwei oder drei Nachbarn fühlt sich wohl und lebt weiter.
    // 4. Jede tote Zelle mit genau drei lebendigen Nachbarn wird wieder zum Leben erweckt.
    boolean[][] submitToTheRules(boolean[][] world) {
        boolean[][] newGen = world;
        for (int x = 0; x < DIM1; x++) {
            for (int y = 0; y < DIM2; y++ ) {
                if (world[x][y]) {
                    // Regel 3 wird implizit getestet durch regel 1 und 2.
                    if (ruleOneDeathByIsolation(world, x, y) || ruleTwoDeathByOverpopulation(world, x, y))
                        newGen[x][y] = false;
                } else {
                        newGen[x][y] = ruleFourReviveThroughNeighbors(world, x, y);
                }
            }
        }
        return newGen;
    }

    // 1. Jede lebendige Zelle, die weniger als zwei lebendige Nachbarn hat, stirbt an Einsamkeit.
    public boolean ruleOneDeathByIsolation(boolean[][] world, int x, int y) {
        return (countNeighbors(world, x, y) < 2);
    }

    // 2. Jede lebendige Zelle mit mehr als drei lebendigen Nachbarn stirbt an Überbevölkerung.
    public boolean ruleTwoDeathByOverpopulation(boolean[][] world, int x, int y) {
        return (countNeighbors(world, x, y) > 3);
    }

    // 4. Jede tote Zelle mit genau drei lebendigen Nachbarn wird wieder zum Leben erweckt.
    public boolean ruleFourReviveThroughNeighbors(boolean[][] world, int x, int y) {
        return countNeighbors(world, x, y) == 3;
    }

    // Liefert Anzahl Nachbarn einer Zelle
    private int countNeighbors(boolean[][] world, int x, int y) {
        int returnValue = 0;
        for (int i = x - (x == 0 ? 0 : 1); i <= x + (x == (DIM1 - 1) ? 0 : 1); ++i)
            for (int j = y - (y == 0 ? 0 : 1); j <= y + (y == (DIM2 - 1) ? 0 : 1); ++j)
                if (world[i][j])
                    returnValue += 1;
        // einen Nachbarn zuviel mitgezählt?
        if (world[x][y])
            returnValue -= 1;
        return returnValue;
    }
}
