package BombermenClient.UserInterface;

import BombermenClient.Bombermen.GameConstants;
import BombermenClientServerInterfaces.Messaging.Message;

import javax.swing.*;
import java.util.concurrent.ConcurrentLinkedQueue;

public class BombermenJButton extends JButton implements GameConstants {

//	private ConcurrentLinkedQueue<Message> outputQueue;

	public BombermenJButton(ConcurrentLinkedQueue<Message> outputQueue, BombermenJTextField textField) {
		super("Sign In");
//		this.outputQueue = outputQueue;
		setBounds(SING_IN_BUTTON_POSITION);
//		addActionListener(new BombermenSignInEventListener(outputQueue, textField));
	}

	public BombermenJButton() {
		super("Sign In");
//		this.outputQueue = outputQueue;
		setBounds(SING_IN_BUTTON_POSITION);
//		addActionListener(new BombermenSignInEventListener(outputQueue, textField));
	}
}
