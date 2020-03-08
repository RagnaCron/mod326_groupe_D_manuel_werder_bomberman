package BombermenClient.UserInterface.UserEvents;

import BombermenClient.UserInterface.BombermenJTextField;
import BombermenClientServerInterfaces.Messaging.Message;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.concurrent.ConcurrentLinkedQueue;

public final class BombermenSignInEventListener implements ActionListener {

	private ConcurrentLinkedQueue<Message> outputQueue;
	private BombermenJTextField textField;

	public BombermenSignInEventListener(ConcurrentLinkedQueue<Message> outputQueue, BombermenJTextField textField) {
		this.outputQueue = outputQueue;
		this.textField = textField;
	}

	@Override
	public void actionPerformed(ActionEvent event) {
		System.out.println("Ready to send message for login in....");
		outputQueue.add(new Message("player_login " + textField.getText().trim()));
	}
}
