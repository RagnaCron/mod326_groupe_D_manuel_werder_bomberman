package BombermenClient.GameElements;


import BombermenClient.Bombermen.GameConstants;

import javax.swing.*;
import java.awt.*;


public abstract class GameElement extends JPanel implements GameConstants {
	protected Image loadImage(String path){
		return new ImageIcon(path).getImage();
	}
}
