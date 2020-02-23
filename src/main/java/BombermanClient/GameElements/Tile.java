package BombermanClient.GameElements;

import lombok.Getter;

import java.awt.*;
import java.io.IOException;
import java.util.Arrays;

@Getter
public class Tile extends GameElement {

	private Image image;

	public Tile(String imagePath, Dimension size, Rectangle position) {
		super();
		setSize(size);
		setBounds(position);
		try {
			image = loadImage(imagePath)
					.getScaledInstance(getWidth(), getHeight(), 0);
		} catch (IOException e) {
			System.out.println(Arrays.toString(e.getStackTrace()));
		}
	}

	public boolean isCollidingWith(Rectangle rect){
		return this.getBounds().intersects(rect);
	}

	@Override
	protected void paintComponent(Graphics g) {
		super.paintComponent(g);
		g.drawImage(image, getX(), getY(), this);
	}
}
