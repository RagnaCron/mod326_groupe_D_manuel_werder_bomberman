package BombermenClient;

import BombermenClient.Bombermen.Bombermen;

public class Main {
	public static void main(String[] args) {
		javax.swing.SwingUtilities.invokeLater(Bombermen::new);
	}
}
