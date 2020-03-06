package BombermenClient.UserInterface.UserEvents;

import BombermenClientServerInterfaces.Messaging.CommandCode;
import BombermenClientServerInterfaces.Messaging.Message;

import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.util.concurrent.ConcurrentLinkedQueue;

import static BombermenClient.Bombermen.Bombermen.GoodbyePlayer;

public final class BombermenWindowListener extends WindowAdapter {
	private ConcurrentLinkedQueue<Message> outputQueue;

	public BombermenWindowListener(ConcurrentLinkedQueue<Message> outputQueue) {
		this.outputQueue = outputQueue;
	}

	@Override
	public void windowClosing(WindowEvent e) {
		outputQueue.add(new Message(CommandCode.PLAYER_EXIT, "player_exit playerID socketID saveGamePoints".split(" ")));
		GoodbyePlayer();
	}
}
