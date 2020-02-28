package BombermanClient.GameElements.Tiles;

import BombermanClient.GameElements.GameElement;
import lombok.Getter;

import java.awt.*;

@Getter
public abstract class Tile extends GameElement {

	private Image image;

	public Tile(String imagePath, Dimension size, Rectangle position) {
		super();
		setSize(size);
		setBounds(position);
		image = loadImage(imagePath)
				.getScaledInstance(getWidth(), getHeight(), 0);
	}

	@Override
	protected void paintComponent(Graphics g) {
		super.paintComponent(g);
		g.drawImage(image, getX(), getY(), this);
	}
}
