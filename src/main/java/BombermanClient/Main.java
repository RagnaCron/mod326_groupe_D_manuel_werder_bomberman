package BombermanClient;

import BombermanClient.Bomberman.Bomberman;

public class Main {
	public static void main(String[] args) {
		javax.swing.SwingUtilities.invokeLater(Bomberman::new);
	}
}
