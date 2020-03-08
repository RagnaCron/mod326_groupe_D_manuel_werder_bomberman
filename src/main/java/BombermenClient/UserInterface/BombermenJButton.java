package BombermenClient.UserInterface;

import BombermenClient.Bombermen.GameConstants;

import javax.swing.*;

public class BombermenJButton extends JButton implements GameConstants {

	public BombermenJButton() {
		super("Sign In");
//		this.outputQueue = outputQueue;
		setBounds(SING_IN_BUTTON_POSITION);
//		addActionListener(new BombermenSignInEventListener(outputQueue, textField));
	}
}
