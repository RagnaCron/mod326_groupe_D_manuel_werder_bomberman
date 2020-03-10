package BomberGame.Labyrinth;

import BomberGame.Constants.BomberGameConstants;
import BomberGame.GameEntities.Player.Direction;
import BomberGame.GameEntities.Player.Player;
import BomberGame.GameEntities.Player.PlayerFactory.PlayerFactory;
import BomberGame.GameEntities.Player.PlayerStartPosition;
import BomberGame.GameEntities.Tile.*;
import BomberGame.GameEntities.Tile.TileFactory.TileFactory;

import javax.swing.*;
import java.awt.*;
import java.util.HashMap;

public final class BomberLabyrinth extends JPanel implements BomberGameConstants {

	private Tile[][] board = new Tile[GRID_SIZE][GRID_SIZE];
	private TileFactory tileFactory = TileFactory.factory(builder -> {
		builder.add(TileType.GRASS_TILE, GrassTile::new);
		builder.add(TileType.DESTRUCTIBLE_TILE, DestructibleTile::new);
		builder.add(TileType.INDESTRUCTIBLE_TILE_ONE, IndestructibleTile::new);
		builder.add(TileType.INDESTRUCTIBLE_TILE_TWO, IndestructibleTile::new);
	});
	private PlayerFactory playerFactory = PlayerFactory.factory(playerBuilder -> {
		playerBuilder.add(PlayerStartPosition.LEFT_UPPER_CORNER, Player::new);
		playerBuilder.add(PlayerStartPosition.RIGHT_UPPER_CORNER, Player::new);
		playerBuilder.add(PlayerStartPosition.LEFT_BOTTOM_CORNER, Player::new);
		playerBuilder.add(PlayerStartPosition.RIGHT_BOTTOM_CORNER, Player::new);
	});

	private HashMap<String, Player> players = new HashMap<>();

	public BomberLabyrinth(Dimension size, Rectangle position) {
		super();
		setPreferredSize(size);
		setBounds(position);
		populateNewBoard(InitialGrassBoard);
	}

	public void set(int row, int column, Tile tile) {
		board[row][column] = tile;
	}

	public Tile get(int row, int column) {
		return board[row][column];
	}

	@Override
	protected void paintComponent(Graphics g) {
		super.paintComponent(g);
		paintBoard(g);
		paintPlayers(g);
	}

	private void paintBoard(Graphics g) {
		for (int row = 0; row < GRID_SIZE; row++) {
			for (int col = 0; col < GRID_SIZE; col++) {
				int y = (row * LABYRINTH_TILE_SIZE);
				int x= (col * LABYRINTH_TILE_SIZE);
				g.drawImage(board[row][col].getImage(), x, y, this);
			}
		}
	}

	private void paintPlayers(Graphics g) {
		if (players.size() > 0)
			for (var player : players.values()) {
				g.drawImage(player.getImage(), player.getX(), player.getY(), this);
			}
	}

	public void startGame() {
		populateNewBoard(DefaultBoard);
		repaint(getBounds());
	}

	public void setNewPlayer(String playerName, PlayerStartPosition facingDirection) {
		if (facingDirection == PlayerStartPosition.LEFT_UPPER_CORNER)
			players.put(playerName, playerFactory.create(facingDirection, PLAYER_DIMENSION, LEFT_UPPER_CORNER_POSITION, facingDirection));
		else if (facingDirection == PlayerStartPosition.RIGHT_UPPER_CORNER)
			players.put(playerName, playerFactory.create(facingDirection, PLAYER_DIMENSION, RIGHT_UPPER_CORNER_POSITION, facingDirection));
		else if (facingDirection == PlayerStartPosition.LEFT_BOTTOM_CORNER)
			players.put(playerName, playerFactory.create(facingDirection, PLAYER_DIMENSION, LEFT_BOTTOM_CORNER_POSITION, facingDirection));
		else if (facingDirection == PlayerStartPosition.RIGHT_BOTTOM_CORNER)
			players.put(playerName, playerFactory.create(facingDirection, PLAYER_DIMENSION, RIGHT_BOTTOM_CORNER_POSITION, facingDirection));
	}

	public void movePlayer(String playerName, Direction direction) {
		if (players.containsKey(playerName)) {
			Player player = players.get(playerName);
			player.move(direction);
			repaint();
		}
	}
	
	public int getPlayerCount() {
		return players.size();
	}

	private void populateNewBoard(String[][] labyrinthFile) {
		for (int row = 0; row < GRID_SIZE; row++) {
			for (int col = 0; col < GRID_SIZE; col++) {
				int y = (row * LABYRINTH_TILE_SIZE);
				int x = (col * LABYRINTH_TILE_SIZE);
				String tile = labyrinthFile[row][col];
				switch (tile) {
					case "1":
						set(row, col, tileFactory.create(
								TileType.INDESTRUCTIBLE_TILE_ONE,
								INDESTRUCTIBLE_TILE_1,
								TILE_DIMENSION,
								new Rectangle(x, y, LABYRINTH_TILE_SIZE, LABYRINTH_TILE_SIZE)));
						break;
					case "2":
						set(row, col, tileFactory.create(
								TileType.INDESTRUCTIBLE_TILE_TWO,
								INDESTRUCTIBLE_TILE_2,
								TILE_DIMENSION,
								new Rectangle(x, y, LABYRINTH_TILE_SIZE, LABYRINTH_TILE_SIZE)));
						break;
					case "3":
						set(row, col, tileFactory.create(
								TileType.DESTRUCTIBLE_TILE,
								DESTROYABLE_TILE,
								TILE_DIMENSION,
								new Rectangle(x, y, LABYRINTH_TILE_SIZE, LABYRINTH_TILE_SIZE)));
						break;
					default:
						set(row, col, tileFactory.create(
								TileType.GRASS_TILE,
								GRASS_TILE,
								TILE_DIMENSION,
								new Rectangle(x, y, LABYRINTH_TILE_SIZE, LABYRINTH_TILE_SIZE)));
						break;
				}
			}
		}
	}

	private final static String[][] DefaultBoard = {
			{"1","2","2","2","2","2","2","2","2","2","2","2","2","2","2","1"},
			{"1","0","0","0","0","0","0","3","3","0","0","0","0","0","0","1"},
			{"1","0","2","0","2","0","2","3","3","2","0","2","0","2","0","1"},
			{"1","0","0","0","0","0","0","3","3","0","0","0","0","0","0","1"},
			{"1","0","2","0","2","0","2","3","3","2","0","2","0","2","0","1"},
			{"1","0","0","0","0","0","0","3","3","0","0","0","0","0","0","1"},
			{"1","0","2","0","2","0","2","3","3","2","0","2","0","2","0","1"},
			{"1","3","3","3","3","3","3","3","3","3","3","3","3","3","3","1"},
			{"1","3","3","3","3","3","3","3","3","3","3","3","3","3","3","1"},
			{"1","0","2","0","2","0","2","3","3","2","0","2","0","2","0","1"},
			{"1","0","0","0","0","0","0","3","3","0","0","0","0","0","0","1"},
			{"1","0","2","0","2","0","2","3","3","2","0","2","0","2","0","1"},
			{"1","0","0","0","0","0","0","3","3","0","0","0","0","0","0","1"},
			{"1","0","2","0","2","0","2","3","3","2","0","2","0","2","0","1"},
			{"1","0","0","0","0","0","0","3","3","0","0","0","0","0","0","1"},
			{"2","2","2","2","2","2","2","2","2","2","2","2","2","2","2","2"},
	};

	private final static String[][] InitialGrassBoard = {
			{"0","0","0","0","0","0","0","0","0","0","0","0","0","0","0","0"},
			{"0","0","0","0","0","0","0","0","0","0","0","0","0","0","0","0"},
			{"0","0","0","0","0","0","0","0","0","0","0","0","0","0","0","0"},
			{"0","0","0","0","0","0","0","0","0","0","0","0","0","0","0","0"},
			{"0","0","0","0","0","0","0","0","0","0","0","0","0","0","0","0"},
			{"0","0","0","0","0","0","0","0","0","0","0","0","0","0","0","0"},
			{"0","0","0","0","0","0","0","0","0","0","0","0","0","0","0","0"},
			{"0","0","0","0","0","0","0","0","0","0","0","0","0","0","0","0"},
			{"0","0","0","0","0","0","0","0","0","0","0","0","0","0","0","0"},
			{"0","0","0","0","0","0","0","0","0","0","0","0","0","0","0","0"},
			{"0","0","0","0","0","0","0","0","0","0","0","0","0","0","0","0"},
			{"0","0","0","0","0","0","0","0","0","0","0","0","0","0","0","0"},
			{"0","0","0","0","0","0","0","0","0","0","0","0","0","0","0","0"},
			{"0","0","0","0","0","0","0","0","0","0","0","0","0","0","0","0"},
			{"0","0","0","0","0","0","0","0","0","0","0","0","0","0","0","0"},
			{"0","0","0","0","0","0","0","0","0","0","0","0","0","0","0","0"},
	};

//	@Override
//	public void keyTyped(KeyEvent e) {
//
//	}
//
//	@Override
//	public void keyPressed(KeyEvent e) {
//		switch (e.getKeyCode()) {
//			case KeyEvent.VK_ESCAPE:
//				System.out.println("Exiting Bombermen Game...");
//				System.exit(0);
//				break;
//			case KeyEvent.VK_W:
//			case KeyEvent.VK_UP:
//				System.out.println("Go up...");
//				break;
//			case KeyEvent.VK_D:
//			case KeyEvent.VK_RIGHT:
//				System.out.println("Go right...");
//				break;
//			case KeyEvent.VK_S:
//			case KeyEvent.VK_DOWN:
//				System.out.println("Go down...");
//				break;
//			case KeyEvent.VK_A:
//			case KeyEvent.VK_LEFT:
//				System.out.println("Go left...");
//				break;
//			case KeyEvent.VK_SPACE:
//				System.out.println("Drop bomb...");
//				break;
//			default:
//				break;
//		}
//	}
//
//	@Override
//	public void keyReleased(KeyEvent e) {
//
//	}
}
