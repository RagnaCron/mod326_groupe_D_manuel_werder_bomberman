package BomberMen.BomberGame.GameEntities;

import BomberMen.BomberGame.Constants.BomberGameConstants;
import lombok.Getter;

import javax.swing.*;
import java.awt.*;

public abstract class Entity extends JLabel implements BomberGameConstants, ImageLoader {

	@Getter
	protected Image image;

	protected Entity(String pathToImage, Dimension size, Rectangle position){
		super();
		setPreferredSize(size);
		setBounds(position);
		image = loadImage(pathToImage, size.width, size.height);
	}

	public void setImage(Image image) {
		this.image = image;
		repaint(getBounds());
	}

	public int getPositionX() {
		return getX() / LABYRINTH_TILE_SIZE;
	}

	public int getPositionY() {
		return getY() / LABYRINTH_TILE_SIZE;
	}
}
