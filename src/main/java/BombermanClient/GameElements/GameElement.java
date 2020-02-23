package BombermanClient.GameElements;


import javax.swing.*;
import java.net.URL;

public class GameElement extends JPanel {

	protected ImageIcon loadImage(URL path) {
		return new ImageIcon(path);
	}

}
