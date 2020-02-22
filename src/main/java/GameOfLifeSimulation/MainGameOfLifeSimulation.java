package GameOfLifeSimulation;

public class MainGameOfLifeSimulation {

	public static void main(String[] args) {

		int size = 100;

		GameOfLife game = new GameOfLife(size,size);
		SimulationView view = new SimulationView(size, size);

		boolean[][] welt = game.initRandomPopulatedWorld();
		int counter = 1;

		while (view.isActiv(welt)) {
			view.showStatus(welt);
			welt = game.submitToTheRules(welt);
			try {
				Thread.sleep(200);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
			if( counter == 100) System.exit(0);
			counter++;
		}
	}

}
