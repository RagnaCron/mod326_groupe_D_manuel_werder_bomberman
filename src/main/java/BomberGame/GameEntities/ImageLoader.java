package BomberGame.GameEntities;

import javax.swing.*;
import java.awt.*;

public interface ImageLoader {
	default Image loadImage(String path, int width, int height) {
		return new ImageIcon(path).getImage().getScaledInstance(width, height, 0);
	}
}
