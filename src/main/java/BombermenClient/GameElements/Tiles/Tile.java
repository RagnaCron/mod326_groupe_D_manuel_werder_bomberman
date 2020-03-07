package BombermenClient.GameElements.Tiles;

import BombermenClient.GameElements.GameElement;
import lombok.Getter;

import java.awt.*;

@Getter
public abstract class Tile extends GameElement {
// Todo: refactor tile to hold 2 images. background grass and front tile if set is another tile...
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
