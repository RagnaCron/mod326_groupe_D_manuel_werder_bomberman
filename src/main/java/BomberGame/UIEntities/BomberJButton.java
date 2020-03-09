package BomberGame.UIEntities;

import BomberGame.Constants.BomberGameConstants;

import javax.swing.*;
import java.awt.*;

public final class BomberJButton extends JButton implements BomberGameConstants {

	public BomberJButton(String name, Rectangle position) {
		super(name);
		setBounds(position);
	}

}
