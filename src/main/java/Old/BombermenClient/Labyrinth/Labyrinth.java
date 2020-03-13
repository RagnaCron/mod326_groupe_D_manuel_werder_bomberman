package Old.BombermenClient.Labyrinth;

import Old.BombermenClient.Bombermen.GameConstants;
import Old.BombermenClient.GameElements.Player.Player;
import Old.BombermenClient.GameElements.Player.Player.Direction;
import Old.BombermenClient.GameElements.Player.Player.PlayerVersion;
import Old.BombermenClientServerInterfaces.Messaging.CustomJSONArray;
import Old.BombermenClientServerInterfaces.Messaging.Message;

import javax.swing.*;
import java.awt.*;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;

public final class Labyrinth extends JPanel implements GameConstants {

	private Board labyrinth;

	private Player grayPlayer = new Player(PlayerVersion.GRAY, Direction.FACING_RIGHT, Player.RIGHT_UPPER_CORNER_POSITION);
//	private Player greenPlayer = new Player();
//	private Player purplePlayer = new Player();
//	private Player yellowPlayer = new Player();

	public Labyrinth(final int width, final int height) {
		Dimension size = new Dimension(width, height);
		setSize(size);
		setPreferredSize(size);
		setMaximumSize(size);
		setMaximumSize(size);

//		addKeyListener(new BombermenGameKeyboardInput());


		labyrinth = Board.CreateInitialGrassBoard();
	}
	public Labyrinth(CustomJSONArray array) {
		Dimension size = new Dimension(LABYRINTH_WIDTH, LABYRINTH_HEIGHT);
		setSize(size);
		setPreferredSize(size);
		setMaximumSize(size);
		setMaximumSize(size);
		loadPlayingBoard(array);
	}

    @Override
	protected void paintComponent(Graphics g) {
		super.paintComponent(g);
		paintBoard(g);
//		g.drawImage(grayPlayer.getImage(), grayPlayer.getX(), grayPlayer.getY(), this);
	}

	@Override
	public void update(Graphics g) {
		super.update(g);
		paintBoard(g);
	}

	private void paintBoard(Graphics g) {
		for (int row = 0; row < GRID_SIZE; row++) {
			for (int col = 0; col < GRID_SIZE; col++) {
				int y = (row * LABYRINTH_TILE_SIZE);
				int x= (col * LABYRINTH_TILE_SIZE);
				g.drawImage(labyrinth.get(row, col).getImage(), x, y, this);
			}
		}
	}

	public void loadPlayingBoard(CustomJSONArray array) {
		System.err.println("Started to load new board");
//		labyrinth.populateNewBoard(array);
		labyrinth.setGenericBoard(array);
	}

	public void loadPlayers(Message message) {

	}

	public static class ChangeLister implements PropertyChangeListener {

		private CustomJSONArray array;

		public ChangeLister(CustomJSONArray array) {
			this.array = array;
		}

		@Override
		public void propertyChange(PropertyChangeEvent evt) {
			System.err.println(evt.getNewValue() == "DONE");
		}
	}
}
