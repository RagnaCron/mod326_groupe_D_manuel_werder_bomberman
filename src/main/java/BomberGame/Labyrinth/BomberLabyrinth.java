package BomberGame.Labyrinth;

import BomberGame.Constants.BomberGameConstants;
import BomberGame.GameEntities.Bomb.Bomb;
import BomberGame.GameEntities.Player.Direction;
import BomberGame.GameEntities.Player.Player;
import BomberGame.GameEntities.Player.PlayerFactory.PlayerFactory;
import BomberGame.GameEntities.Player.PlayerStartPosition;
import BomberGame.GameEntities.Tile.*;
import BomberGame.GameEntities.Tile.TileFactory.TileFactory;

import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.util.ArrayList;
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

	private String playerName;
	private HashMap<String, Player> players = new HashMap<>(4);
	private HashMap<String, Bomb> bombs = new HashMap<>(20);

	private Timer timer = new Timer(16, event -> {
//			System.err.println(e.getActionCommand());
		repaint();
	});

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
		try {
			return board[row][column];
		} catch (Exception ignored) {
			return null;
		}
	}

	@Override
	protected void paintComponent(Graphics graphics) {
		super.paintComponent(graphics);
		updateGameState();
		paintGame(graphics);
	}


	private void updateGameState() {
		ArrayList<String> bombNames = new ArrayList<>(30);
		for (var bombName : bombs.keySet()) {
			var bomb = bombs.get(bombName);
			if (bomb.isExploded())
				bombNames.add(bombName);
			for (var pn : players.keySet()) {
				Player player = players.get(pn);
				if (bomb.isExploded()) {
					checkForDestruction(bomb, player);
				}
			}
			killPlayers();
		}
		removeExplodedBombs(bombNames);
	}

	@SuppressWarnings("ALL")
	private void checkForDestruction(Bomb bomb, Player player) {
//		Tile bombSitingTile = board[bomb.getPositionX()][bomb.getPositionY()];
		Tile verticalTileUPOne = get(bomb.getPositionX(), bomb.getPositionY() - 1);
		Tile verticalTileUPTwo = get(bomb.getPositionX(),bomb.getPositionY() - 2);
		Tile verticalTileDownOne = get(bomb.getPositionX(), bomb.getPositionY() + 1);
		Tile verticalTileDownTwo = get(bomb.getPositionX(), bomb.getPositionY() + 2);
		Tile horizontalTileRightOne = get(bomb.getPositionX() + 1, bomb.getPositionY());
		Tile horizontalTileRightTwo = get(bomb.getPositionX() + 2, bomb.getPositionY());
		Tile horizontalTileLeftOne = get(bomb.getPositionX() - 1, bomb.getPositionY());
		Tile horizontalTileLeftTwo = get(bomb.getPositionX() - 2, bomb.getPositionY());

		boolean verticalPlayerHitUPOne = bomb.getPositionY() - 1 == player.getPositionY();
		boolean verticalPlayerHitUPTwo = bomb.getPositionY() - 2 == player.getPositionY();
		boolean verticalPlayerHitDownOne = bomb.getPositionY() + 1 == player.getPositionY();
		boolean verticalPlayerHitDownTwo = bomb.getPositionY() + 2 == player.getPositionY();
		boolean horizontalPlayerHitRightOne = bomb.getPositionX() + 1 == player.getPositionX();
		boolean horizontalPlayerHitRightTwo = bomb.getPositionX() + 2 == player.getPositionX();
		boolean horizontalPlayerHitLeftOne = bomb.getPositionX() - 1 == player.getPositionX();
		boolean horizontalPlayerHitLeftTwo = bomb.getPositionX() - 2 == player.getPositionX();

		destroy(verticalTileUPOne, verticalTileUPTwo, player,
				verticalPlayerHitUPOne, verticalPlayerHitUPTwo);
		destroy(verticalTileDownOne, verticalTileDownTwo, player,
				verticalPlayerHitDownOne, verticalPlayerHitDownTwo);
		destroy(horizontalTileRightOne, horizontalTileRightTwo, player,
				horizontalPlayerHitRightOne, horizontalPlayerHitRightTwo);
		destroy(horizontalTileLeftOne, horizontalTileLeftTwo, player,
				horizontalPlayerHitLeftOne, horizontalPlayerHitLeftTwo);
	}

	private void removeExplodedBombs(ArrayList<String> bombNames) {
		bombNames.forEach(n -> bombs.remove(n));
	}

	private void killPlayers() {
		ArrayList<String> pn = new ArrayList<>(4);
		for (var n : players.keySet()){
			if (!players.get(n).isALife())
				pn.add(n);
		}
		pn.forEach(n -> players.remove(n));
	}

	@SuppressWarnings("ALL")
	private void destroy(Tile tile1, Tile tile2, Player player, boolean playerHit1, boolean playerHit2) {
		if (!tile1.isGrass() && tile1 != null) {
			if (tile1.isDestroyable()) {
				destroyTile(tile1);
				return;
			}
		} else {
			if (playerHit1) {
				player.setALife(false);
				return;
			}
			if (!tile2.isGrass() && tile2 != null) {
				if (tile2.isDestroyable()) {
					destroyTile(tile2);
				}
			} else {
				if (playerHit2)
					player.setALife(false);
			}
		}
	}

	private void destroyTile(Tile tile) {
		DestructibleTile dTile = (DestructibleTile) tile;
		set(dTile.getPositionX(), dTile.getPositionY(), ((DestructibleTile) tile).destroyTile());
	}

	private void paintGame(Graphics graphics) {
		for (int row = 0; row < GRID_SIZE; row++) {
			for (int col = 0; col < GRID_SIZE; col++) {
				int y = (row * LABYRINTH_TILE_SIZE);
				int x = (col * LABYRINTH_TILE_SIZE);
				Tile tile = board[row][col];
				graphics.drawImage(board[row][col].getImage(), x, y, this);
				paintBombs(graphics);
				paintPlayers(graphics, tile);
			}
		}
	}

	private void paintPlayers(Graphics graphics, Tile tile) {
		for (var player : players.values()) {
			if (tile.isCollidingWith(player.getBounds()))
				player.setBounds(player.getOldX(), player.getOldY(), player.getWidth(), player.getHeight());
			graphics.drawImage(player.getImage(), player.getX(), player.getY(), this);
		}
	}

	private void paintBombs(Graphics graphics) {
		for (var bomb : bombs.values())
			graphics.drawImage(bomb.getImage(), bomb.getX(), bomb.getY(), this);
	}

	public void startGame(String playerName) {
		this.playerName = playerName;
		this.addKeyListener(new GameKeyboardListener());
		populateNewBoard(DefaultBoard);
		timer.start();
		repaint();
	}

	public void setNewPlayer(PlayerStartPosition facingDirection) {
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

	public void dropBomb(String playerName) {
		if (players.containsKey(playerName)) {
			Player player = players.get(playerName);
			Bomb bomb = player.dropBomb(player.getX(), player.getY());
			bombs.put(playerName + Bomb.getBombID(), bomb);
			repaint();
		}
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


	private class GameKeyboardListener extends KeyAdapter {
		private long timeNow = System.currentTimeMillis();
		@Override
		public void keyPressed(KeyEvent e) {
			switch (e.getKeyCode()) {
				case KeyEvent.VK_W:
				case KeyEvent.VK_UP:
					movePlayer(playerName, Direction.FACING_UP);
					break;
				case KeyEvent.VK_D:
				case KeyEvent.VK_RIGHT:
					movePlayer(playerName, Direction.FACING_RIGHT);
					break;
				case KeyEvent.VK_S:
				case KeyEvent.VK_DOWN:
					movePlayer(playerName, Direction.FACING_DOWN);
					break;
				case KeyEvent.VK_A:
				case KeyEvent.VK_LEFT:
					movePlayer(playerName, Direction.FACING_LEFT);
					break;
				case KeyEvent.VK_SPACE:
					long timeNew = System.currentTimeMillis();
					long deltaTime = timeNew - timeNow;
					if (deltaTime > 1000) {
						timeNow = timeNew;
						dropBomb(playerName);
					}
					break;
				default:
					break;
			}
		}
	}

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
