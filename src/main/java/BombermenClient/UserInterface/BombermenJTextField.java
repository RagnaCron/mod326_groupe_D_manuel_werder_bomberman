package BombermenClient.UserInterface;

import BombermenClient.Bombermen.GameConstants;
import BombermenClientServerInterfaces.Messaging.Message;

import javax.swing.*;
import java.util.concurrent.ConcurrentLinkedQueue;

public final class BombermenJTextField extends JTextField implements GameConstants {

//	private ConcurrentLinkedQueue<Message> outputQueue;

	public BombermenJTextField(ConcurrentLinkedQueue<Message> outputQueue) {
		super();
//		this.outputQueue = outputQueue;
		setBounds(TEXT_INPUT_POSITION);
		setActionCommand("input");
//		addKeyListener(BombermenKeyboardListener.CreateBombermenKeyboardListener(outputQueue));
	}

}
