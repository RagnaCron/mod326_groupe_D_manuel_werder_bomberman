package BombermenClient.UserInterface.UserEvents;

import BombermenClient.UserInterface.BombermenJTextField;
import BombermenClientServerInterfaces.Messaging.CommandCode;
import BombermenClientServerInterfaces.Messaging.Message;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.concurrent.ConcurrentLinkedQueue;

public final class UserSignInEventListener implements ActionListener {

	private ConcurrentLinkedQueue<Message> outputQueue;
	private BombermenJTextField textField;

	public UserSignInEventListener(ConcurrentLinkedQueue<Message> outputQueue, BombermenJTextField textField) {
		this.outputQueue = outputQueue;
		this.textField = textField;
	}

	@Override
	public void actionPerformed(ActionEvent event) {
		if (event.getActionCommand().equals("input")) {
			outputQueue.add(new Message(CommandCode.PLAYER_LOGIN, textField.getText().trim()));
		}
	}
}
