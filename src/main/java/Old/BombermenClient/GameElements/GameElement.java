package Old.BombermenClient.GameElements;


import lombok.Getter;

import javax.swing.*;
import java.awt.*;


public abstract class GameElement extends JPanel {
	@Getter
	protected Image image;
	protected Image loadImage(String path) {
		return new ImageIcon(path).getImage();
	}
}
