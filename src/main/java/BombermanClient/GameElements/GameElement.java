package BombermanClient.GameElements;


import BombermanClient.GameConstants;

import javax.swing.*;
import java.awt.*;


public abstract class GameElement extends JPanel implements GameConstants {
	protected Image loadImage(String path){
		return new ImageIcon(path).getImage();
	}
}
