package BombermanClient.GameElements;


import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.io.File;
import java.io.IOException;
import java.net.URL;

public class GameElement extends JPanel {

	protected Image loadImage(String path) throws IOException {
		return ImageIO.read(new File(path));
	}

}
