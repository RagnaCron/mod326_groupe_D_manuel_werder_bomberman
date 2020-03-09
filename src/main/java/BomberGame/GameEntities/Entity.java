package BomberGame.GameEntities;

import BomberGame.Constants.BomberGameConstants;
import lombok.Getter;

import javax.swing.*;
import java.awt.*;

public abstract class Entity extends JLabel implements BomberGameConstants, ImageLoader, Collide {

	@Getter
	protected Image image;
	protected boolean canCollide = false;

	protected Entity(String pathToImage, Dimension size, Rectangle position){
		super();
		setPreferredSize(size);
		setBounds(position);
		image = loadImage(pathToImage, size.width, size.height);
	}

	@Override
	public boolean isCollidingWith(Rectangle rect) {
		return canCollide && getBounds().intersects(rect);
	}

	public void setImage(Image image) {
		this.image = image;
		repaint(getBounds());
	}
}
