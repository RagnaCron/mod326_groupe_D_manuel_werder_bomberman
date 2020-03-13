package Old.BombermenClient.UserInterface;

import Old.BombermenClient.Bombermen.GameConstants;
import Old.BombermenClientServerInterfaces.Messaging.Message;

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
