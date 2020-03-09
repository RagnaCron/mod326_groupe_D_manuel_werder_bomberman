package BomberGame.GameEntities;

import BomberGame.Constants.BomberGameConstants;
import lombok.Getter;

import javax.swing.*;
import java.awt.*;

public abstract class Entity extends JLabel implements BomberGameConstants, ImageLoader {

	@Getter
	private Image image;

	protected Entity(String pathToIcon, Dimension size, Rectangle position){
		super();
		setPreferredSize(size);
		setBounds(position);
		image = loadImage(pathToIcon, size.width, size.height);
	}

}
